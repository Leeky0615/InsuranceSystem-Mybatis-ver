package view.insregistrationpanel.searchInsPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constants.ControlConstants.EInsuranceType;
import constants.ViewConstants.EMainFrame;
import constants.ViewConstants.EViewFrame;
import control.insurance.InsuranceListImpl;
import control.insuranceRegistration.InsuranceRegistrationImpl;
import view.insdevelopmentpanel.InsForDevelopmentPanel;

public class SearchInsurancePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private InsuranceListImpl insuranceList;
	private InsuranceRegistrationImpl insuranceRegistration;
	
	private String menuName;
	
	private JComboBox<String> insType_t;
	private InsuranceTable insuranceTable;
	private JButton detailCheckBtn;

	private ActionHandler actionHandler;
	private MouseHandler mousehandler;

	public SearchInsurancePanel(InsuranceRegistrationImpl insuranceRegistration, InsuranceListImpl insuranceList,String menuName) {
		this.insuranceRegistration = insuranceRegistration;
		this.insuranceList = insuranceList;
		
		this.menuName = menuName;
		
		setLayout(new BorderLayout());
		this.actionHandler = new ActionHandler();
		this.mousehandler = new MouseHandler();

		this.createSelectPanel();
		this.createTable();
	}
	
	
	public void createSelectPanel() {
		this.removeAll();
		this.setLayout(new BorderLayout());
		JPanel selectPanel = new JPanel();
		selectPanel.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1),"���� ��ȸ"));
		selectPanel.setLayout(new GridLayout(0, 3, 20, 0));
		
		this.detailCheckBtn = new JButton("���� �� ��ȸ");
		this.detailCheckBtn.setFont(EViewFrame.eFont.getFont());
		this.detailCheckBtn.addActionListener(this.actionHandler);
		selectPanel.add(this.detailCheckBtn);
		
		this.add(selectPanel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("���� ����");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(EViewFrame.eFont.getFont());
		selectPanel.add(label);
		
		this.insType_t = new JComboBox<String>();
		this.insType_t.addItem("��ü");
		for(EInsuranceType insuranceType : EInsuranceType.values()) {insType_t.addItem(insuranceType.getText());}
		this.insType_t.setFont(EViewFrame.eFont.getFont());
		this.insType_t.addActionListener(this.actionHandler);
		selectPanel.add(this.insType_t);
		this.updateUI();
	}
	public void createTable() {
		this.insuranceTable = new InsuranceTable(this.insuranceList, insType_t.getSelectedItem().toString());
		this.insuranceTable.addMouseListener(this.mousehandler);
		JScrollPane scroll = new JScrollPane();
   		scroll.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1),"���� ����Ʈ"));
   		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
   		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
   		scroll.setViewportView(this.insuranceTable);
   		scroll.setPreferredSize(new Dimension(EMainFrame.eWidth.getValue(),50));
   		this.add(scroll, BorderLayout.CENTER);
		this.updateUI();
	}
	
	public void buttonClick(Object source) {
		if (source.equals(this.detailCheckBtn)) {
			if (this.insuranceTable.getRow() == null) {
				JOptionPane.showMessageDialog(null, "�����ǰ�� ������ �ּ���.", "�����ǰ ����", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				this.removeAll();
				this.setLayout(new GridLayout(1,1));
				if (menuName.equals("Registration")) {
					// ���� ���� ȭ�鿡�� ���� �������� Ŭ�������� ������ �г�
					InsForRegistPanel insuranceForRegistPanel = new InsForRegistPanel(this,this.insuranceRegistration, this.insuranceTable.getRow());
					this.add(insuranceForRegistPanel);
				}else {
					// ���� ���� ȭ�鿡�� ���� �������� Ŭ�������� ������ �г�
					InsForDevelopmentPanel insuranceForDevelopmentPanel = new InsForDevelopmentPanel(this, this.insuranceRegistration, this.insuranceTable.getRow());
					this.add(insuranceForDevelopmentPanel);
				}
			}
		}else {
			this.insuranceTable.refresh(insType_t.getSelectedItem().toString());
			for(EInsuranceType insuranceType : EInsuranceType.values()) {
				if (insuranceType.getText().equals(insType_t.getSelectedItem().toString())) {
					this.insuranceTable.refresh(insuranceType.getText());
				}
			}
		}
		updateUI();
	}
	
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonClick(e.getSource());
		}
	}
	
	public void selectRow() {
		if (this.insuranceTable.getRow() == null) {
			JOptionPane.showMessageDialog(null, "�����ǰ�� ������ �ּ���.", "�����ǰ ����", JOptionPane.WARNING_MESSAGE);
			return;
		}else{this.detailCheckBtn.doClick();}
	}
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {selectRow();}
		}
	}
}