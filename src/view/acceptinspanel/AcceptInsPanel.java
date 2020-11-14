package view.acceptinspanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constants.ViewConstants.EMainFrame;
import constants.ViewConstants.EViewFrame;
import control.acceptInsuranceDesign.AcceptInsuranceDesignImpl;
import control.insuranceDevelopment.InsuranceDesignListImpl;
import main.Menu;
import view.defaultClass.DefaultPanel;

public class AcceptInsPanel extends DefaultPanel {
	private static final long serialVersionUID = 1L;
	private InsuranceDesignListImpl insuranceDesignList;
	private AcceptInsuranceDesignImpl AcceptInsuranceDesign;
	
	private InsuranceDesignTable insuranceDesignTable;
	private JButton detailCheckBtn,acceptedInsBtn;
	
	private ActionHandler actionHandler;
	private MouseHandler mousehandler;


	public AcceptInsPanel(Menu menu) {
		super(menu);
		this.insuranceDesignList = (InsuranceDesignListImpl)this.menu.getInsuranceDesignList();
		this.AcceptInsuranceDesign= (AcceptInsuranceDesignImpl)this.menu.getAcceptInsuranceDesign();
		this.mousehandler = new MouseHandler();

		this.createPanel();
	}
	
	public void createPanel() {
		this.removeAll();
		this.setLayout(null);
		// ���̺� ����
		this.insuranceDesignTable = new InsuranceDesignTable((InsuranceDesignListImpl) this.insuranceDesignList, false);
		this.insuranceDesignTable.addMouseListener(mousehandler);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 10, 576, 364);
		scroll.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1),"���� ���輭 ���� ��� ����"));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll.setViewportView(this.insuranceDesignTable);
		scroll.setPreferredSize(new Dimension(EMainFrame.eWidth.getValue(),50));
		this.add(scroll);
		
		// ��ư �г� ����
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 384, 576, 116);
		panel.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1)));
		this.actionHandler = new ActionHandler();
		// �����Ȯ���ϱ� ��ư
		this.detailCheckBtn = new JButton("���� �� Ȯ���ϱ�");
		this.detailCheckBtn.setFont(EViewFrame.eFont.getFont());
		this.detailCheckBtn.setBounds(12, 10, 552, 42);
		this.detailCheckBtn.addActionListener(this.actionHandler);
		// ���ε� ���輳�輭 ����Ʈ Ȯ���ϱ� ��ư
		this.acceptedInsBtn = new JButton("���ε� ���輳�輭 ����Ʈ Ȯ���ϱ�");
		this.acceptedInsBtn.setFont(EViewFrame.eFont.getFont());
		this.acceptedInsBtn.setBounds(12, 62, 552, 42);
		this.acceptedInsBtn.addActionListener(this.actionHandler);
		panel.add(this.detailCheckBtn);
		panel.add(this.acceptedInsBtn);
		this.add(panel);
		
		scroll.updateUI();
		panel.updateUI();
	}
	
	// ��ư ActionHandler
	public void buttonClick(Object source) {
		if (source.equals(this.detailCheckBtn)) {
			// ����ȸ�ϱ� ��ư Ŭ����
			if (this.insuranceDesignTable.getRow() == null) {
				// Row�� �������� ���� ���
				JOptionPane.showMessageDialog(null, "������ ������ �������ּ���.", "������ ���� ����", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				// ���輳�輭 �г��� ������
				this.removeAll();
				InsuranceDesignPanel insuranceDesignPanel = new InsuranceDesignPanel(this,this.AcceptInsuranceDesign,this.insuranceDesignList,this.insuranceDesignTable.getRow());
				this.setLayout(new GridLayout(1,1));
				this.add(insuranceDesignPanel);
			}
		}else if(source.equals(this.acceptedInsBtn)){
			// ���ε� ���輳�輭 ������ Ŭ���ϴ� ���, ���ε� ���輳�輭 �г��� ������
			this.removeAll();
			AcceptedInsPanel acceptedInsPanel = new AcceptedInsPanel(this,this.insuranceDesignList);
			this.setLayout(new GridLayout(1,1));
			this.add(acceptedInsPanel);
		}
		updateUI();
	}
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonClick(e.getSource());
		}
	}
	
	// ���̺��� ����Ŭ������ ��  ��ư�� ���� �г��� ������.
	public void selectRow() {
		if (this.insuranceDesignTable.getRow() == null) {
			JOptionPane.showMessageDialog(null, "���輳�輭�� ������ �ּ���.", "���輳�輭 ����", JOptionPane.WARNING_MESSAGE);
			return;
		}else{this.detailCheckBtn.doClick();}
	}
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {selectRow();}
		}
	}
}
