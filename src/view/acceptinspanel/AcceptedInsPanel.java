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
import control.insuranceDevelopment.InsuranceDesignListImpl;

public class AcceptedInsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private InsuranceDesignListImpl insuranceDesignList;
	private AcceptInsPanel acceptInsPanel;

	private InsuranceDesignTable insuranceDesignTable;
	private JButton detailCheckBtn,backBtn;
	private ActionHandler actionHandler;
	private MouseHandler mousehandler;

	public AcceptedInsPanel(AcceptInsPanel acceptInsPanel, InsuranceDesignListImpl insuranceDesignList) {
		this.acceptInsPanel = acceptInsPanel;
		this.insuranceDesignList = insuranceDesignList;
		this.mousehandler = new MouseHandler();

		this.createDefaultPanel();
	}
	public void createDefaultPanel() {
		this.removeAll();
		this.setLayout(null);
		
		// ���輳�輭 ����Ʈ ���̺�
		this.insuranceDesignTable = new InsuranceDesignTable((InsuranceDesignListImpl) this.insuranceDesignList, true);
		this.insuranceDesignTable.addMouseListener(mousehandler);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 10, 576, 364);
		scroll.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1),"���ε� ���輳�輭 ����Ʈ"));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll.setViewportView(this.insuranceDesignTable);
		scroll.setPreferredSize(new Dimension(EMainFrame.eWidth.getValue(),50));
		this.add(scroll);
		
		// ��ư�� ��� �г� ����
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 384, 576, 116);
		panel.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1)));
		this.actionHandler = new ActionHandler();
		
		this.detailCheckBtn = new JButton("���� �� Ȯ���ϱ�");
		this.detailCheckBtn.setFont(EViewFrame.eFont.getFont());
		this.detailCheckBtn.setBounds(12, 10, 552, 42);
		this.detailCheckBtn.addActionListener(this.actionHandler);
		
		this.backBtn = new JButton("�ڷΰ���");
		this.backBtn.setFont(EViewFrame.eFont.getFont());
		this.backBtn.setBounds(12, 62, 552, 42);
		this.backBtn.addActionListener(this.actionHandler);
		
		panel.add(this.detailCheckBtn);
		panel.add(this.backBtn);
		this.add(panel);
		updateUI();
	}
	
	// ��ư Ŭ���� ����Ǵ� �޼ҵ�
	public void buttonClick(Object source) {
		if (source.equals(this.backBtn)) {
			// �ڷΰ��� ��ưŬ���� ���� �г��� �ʱ�ȭ�ϰ� ���� �г��� ����
			this.removeAll();
			this.acceptInsPanel.createPanel();
			this.insuranceDesignTable.setVisible(false);
			this.backBtn.setVisible(false);
		}else if(source.equals(this.detailCheckBtn)){
			// ����ȸ ��ưŬ���� ����ȸ�г��� ����
			this.removeAll();
			InsuranceDesignPanel insuranceDesignPanel = new InsuranceDesignPanel(this,this.insuranceDesignList,this.insuranceDesignTable.getRow());
			this.setLayout(new GridLayout(1,1));
			this.add(insuranceDesignPanel);
		}
		updateUI();
		revalidate();
	}
	
	// ��ưŬ���� ���Ǵ� Handler
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
