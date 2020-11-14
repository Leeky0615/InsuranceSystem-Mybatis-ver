package view.insregistrationpanel.acceptRegistPanel;

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
import constants.ViewConstants.ETableStatus;
import constants.ViewConstants.EViewFrame;
import control.customer.CustomerListImpl;
import control.insuranceRegistration.InsuranceRegistrationImpl;
import view.checkcustomerpanel.CustomerTable;

public class AcceptRegistrationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private InsuranceRegistrationImpl insuranceRegistration;
	private CustomerListImpl customerList;

	private ActionHandler actionHandler;
	private MouseHandler mousehandler;
	
	private CustomerTable customerTable;
	private JButton detailCheckBtn;
	private JButton refresh;

	public AcceptRegistrationPanel(InsuranceRegistrationImpl insuranceRegistration, CustomerListImpl customerList) {
		this.insuranceRegistration = insuranceRegistration;
		this.customerList = customerList;
		this.mousehandler = new MouseHandler();

		this.createPanel();
	}
	
	public void createPanel() {
		this.removeAll();
		this.setLayout(null);
		// ���̺� ����
		this.customerTable = new CustomerTable(this.customerList, ETableStatus.insuranceRegistration);
		this.customerTable.addMouseListener(this.mousehandler);
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 10, 576, 315);
		scroll.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1),"�� ����Ʈ"));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll.setViewportView(this.customerTable);
		scroll.setPreferredSize(new Dimension(EMainFrame.eWidth.getValue(),50));
		this.add(scroll);
		
		// ��ư �г� ����
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 335, 576, 113);
		panel.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1)));
		this.actionHandler = new ActionHandler();
		// �����Ȯ���ϱ� ��ư
		this.detailCheckBtn = new JButton("�� Ȯ���ϱ�");
		this.detailCheckBtn.setFont(EViewFrame.eFont.getFont());
		this.detailCheckBtn.setBounds(12, 10, 552, 42);
		this.detailCheckBtn.addActionListener(this.actionHandler);
		panel.add(this.detailCheckBtn);
		this.refresh = new JButton("���ΰ�ħ");
		this.refresh.setFont(EViewFrame.eFont.getFont());
		this.refresh.setBounds(12, 62, 552, 42);
		this.refresh.addActionListener(this.actionHandler);
		panel.add(this.refresh);
		
		this.add(panel);
		
		scroll.updateUI();
		panel.updateUI();
	}
	
	// ��ư ActionHandler
	public void buttonClick(Object source) {
		if (source.equals(this.detailCheckBtn)) {
			// ����ȸ�ϱ� ��ư Ŭ����
			if (this.customerTable.getRow() == null) {
				// Row�� �������� ���� ���
				JOptionPane.showMessageDialog(null, "������ ���� �������ּ���.", "�� ����", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				this.removeAll();
				RequestAcceptCustomerPanel requestAcceptCustomerPanel = new RequestAcceptCustomerPanel(this, this.insuranceRegistration,this.customerList);
				requestAcceptCustomerPanel.setSelectedRow(this.customerTable.getRow());
				requestAcceptCustomerPanel.createCustomerInfoPanel();
				requestAcceptCustomerPanel.createInsuranceInfoPanel();
				requestAcceptCustomerPanel.createButton();
				////////////////////////�� ����//////////////////////////
				this.setLayout(new GridLayout(1,1));
				this.add(requestAcceptCustomerPanel);
			}
		}else if (source.equals(this.refresh)) {
			this.removeAll();
			this.createPanel();
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
		if (this.customerTable.getRow() == null) {
			JOptionPane.showMessageDialog(null, "���� ������ �ּ���.", "�� ����", JOptionPane.WARNING_MESSAGE);
			return;
		}else{this.detailCheckBtn.doClick();}
	}
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {selectRow();}
		}
	}
}
