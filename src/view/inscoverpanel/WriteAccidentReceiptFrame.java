package view.inscoverpanel;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constants.ControlConstants.EInsuranceType;
import constants.ControlConstants.EPaymentMethod;
import constants.ViewConstants.EViewFrame;
import control.insuranceCover.InsuranceCoverImpl;
import java.awt.Font;

public class WriteAccidentReceiptFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private InsuranceCoverImpl insuranceCover;
	
	private JButton saveBtn, cancelBtn;
	private ActionHandler actionHandler;

	private JTextField receiptId_t;
	private JTextField receiptName_t;
	private JTextField CustomerName_t;
	private JTextField customerId_t1;
	private JTextField customerId_t2;
	private JComboBox<String> insType_t;
	private JComboBox<String> payType_t;
	private JTextField date_t;
	private JTextField hospital_t;
	private JComboBox<String> death_t;
	private JTextField place_t;
	private JTextField diagnos_t;
	private JTextField damage_t;
	private JTextField circumstace_t;
	private JTextField money_t;

	
	public WriteAccidentReceiptFrame(InsuranceCoverImpl insuranceCover) {
		this.insuranceCover = insuranceCover;
		
		this.actionHandler = new ActionHandler();
		setSize(550, 430);
		setTitle("��������� �ۼ��ϱ�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel p = new JPanel();
		p.setBounds(0, 0, 492, 461);
		p.setBorder(new TitledBorder(new LineBorder(Color.lightGray,1),"��������� �ۼ��ϱ�"));
		p.setLayout(null);
		
		// ��� ������ ��ȣ
		JLabel receiptId_l = new JLabel("��������� ��ȣ");
		receiptId_l.setBounds(10, 22, 118, 25);
		receiptId_l.setFont(EViewFrame.eFont.getFont());
		p.add(receiptId_l);
		
		receiptId_t = new JTextField();
		p.add(receiptId_t);
		receiptId_t.setBounds(134, 22, 121, 25);
		
		// ��������� �̸�
		JLabel receiptName_l = new JLabel("��������� �̸�");
		receiptName_l.setBounds(261, 22, 109, 25);
		receiptName_l.setFont(EViewFrame.eFont.getFont());
		p.add(receiptName_l);
				
		receiptName_t = new JTextField();
		receiptName_t.setBounds(379, 22, 148, 25);
		p.add(receiptName_t);
		
		// �� �̸�
		JLabel CustomerName_l = new JLabel("�� �̸�");
		CustomerName_l.setBounds(10, 51, 104, 25);
		CustomerName_l.setFont(EViewFrame.eFont.getFont());
		p.add(CustomerName_l);
		
		CustomerName_t = new JTextField();
		CustomerName_t.setBounds(134, 51, 121, 25);
		p.add(CustomerName_t);

		// �� �ֹε�Ϲ�ȣ
		JLabel customerId_l = new JLabel("�ֹε�Ϲ�ȣ");
		customerId_l.setBounds(261, 51, 109, 25);
		customerId_l.setFont(EViewFrame.eFont.getFont());
		p.add(customerId_l);
		
		customerId_t2 = new JTextField();
		customerId_t1 = new JTextField();
		customerId_t1.setBounds(379, 51, 68, 25);
		customerId_t2.setBounds(459, 51, 68, 25);
		p.add(customerId_t1);
		p.add(customerId_t2);
		
		JLabel hyphen = new JLabel("-");
		hyphen.setBounds(450, 57, 18, 15);
		p.add(hyphen);
		
		// ���� ����
		JLabel insType_l = new JLabel("���� ����");
		insType_l.setBounds(10, 80, 104, 25);
		insType_l.setFont(EViewFrame.eFont.getFont());
		p.add(insType_l);
		
		insType_t = new JComboBox<String>();
		for(EInsuranceType insuranceType : EInsuranceType.values()) {insType_t.addItem(insuranceType.getText());}
		insType_t.setSelectedIndex(0);
		insType_t.setBounds(134, 80, 121, 25);
		p.add(insType_t);

		// ���� ���
		JLabel payType_l = new JLabel("���� ���");
		payType_l.setBounds(261, 80, 100, 25);
		payType_l.setFont(new Font("���� ���", Font.PLAIN, 15));
		p.add(payType_l);
		
		payType_t = new JComboBox<String>();
		for(EPaymentMethod paymentMethod : EPaymentMethod.values()) {payType_t.addItem(paymentMethod.getText());}
		payType_t.setSelectedIndex(0);
		payType_t.setBounds(379, 80, 148, 25);
		p.add(payType_t);

		// ���¥
		JLabel date_l = new JLabel("��� ��¥");
		date_l.setBounds(10, 108, 104, 25);
		date_l.setFont(EViewFrame.eFont.getFont());
		p.add(date_l);
		
		date_t = new JTextField();
		date_t.setBounds(134, 109, 121, 25);
		p.add(date_t);		
		
		// ������
		JLabel hospital_l = new JLabel("������");
		hospital_l.setBounds(10, 137, 104, 25);
		hospital_l.setFont(EViewFrame.eFont.getFont());
		p.add(hospital_l);
		
		hospital_t = new JTextField();
		hospital_t.setBounds(134, 137, 121, 25);
		p.add(hospital_t);
		
		// �������
		JLabel death_l = new JLabel("��� ����");
		death_l.setBounds(10, 166, 104, 25);
		death_l.setFont(EViewFrame.eFont.getFont());
		p.add(death_l);
		
		death_t = new JComboBox<String>();
		death_t.addItem("�ƴϿ�");
		death_t.addItem("��");
		death_t.setSelectedIndex(0);
		death_t.setBounds(134, 166, 121, 25);
		p.add(death_t);
		
		// ������
		JLabel place_l = new JLabel("��� ���");
		place_l.setBounds(261, 108, 100, 25);
		place_l.setFont(EViewFrame.eFont.getFont());
		p.add(place_l);
		
		place_t = new JTextField();
		place_t.setBounds(379, 108, 148, 25);
		p.add(place_t);
		
		// ������
		JLabel diagnos_l = new JLabel("������");
		diagnos_l.setBounds(261, 137, 100, 25);
		diagnos_l.setFont(EViewFrame.eFont.getFont());
		p.add(diagnos_l);
		
		diagnos_t = new JTextField();
		diagnos_t.setBounds(379, 137, 148, 25);
		p.add(diagnos_t);
		
		
		// ���رԸ�
		JLabel damage_l = new JLabel("���� �Ը�");
		damage_l.setBounds(261, 166, 100, 25);
		damage_l.setFont(EViewFrame.eFont.getFont());
		p.add(damage_l);
		
		damage_t = new JTextField();
		damage_t.setBounds(379, 166, 148, 25);
		p.add(damage_t);
		
		// ����Ȳ
		JLabel circumstace_l = new JLabel("��� ��Ȳ");
		circumstace_l.setBounds(10, 243, 104, 25);
		circumstace_l.setFont(EViewFrame.eFont.getFont());
		p.add(circumstace_l);
		
		circumstace_t = new JTextField();
		circumstace_t.setBounds(134, 243, 393, 84);
		p.add(circumstace_t);
		
		// ���޾�
		JLabel money_l = new JLabel("���� ����");
		money_l.setBounds(10, 195, 104, 25);
		money_l.setFont(EViewFrame.eFont.getFont());
		p.add(money_l);
		
		money_t = new JTextField();
		money_t.setBounds(134, 195, 121, 25);
		p.add(money_t);
		
		// ��ư
		this.saveBtn = new JButton("����");
		this.saveBtn.setFont(EViewFrame.eFont.getFont());
		this.saveBtn.setBounds(157, 337, 80, 30);
		this.saveBtn.addActionListener(this.actionHandler);
		p.add(saveBtn);
		
		this.cancelBtn = new JButton("���");
		this.cancelBtn.setFont(EViewFrame.eFont.getFont());
		this.cancelBtn.setBounds(272, 337, 80, 30);
		this.cancelBtn.addActionListener(this.actionHandler);
		p.add(cancelBtn);
		
		getContentPane().add(p);
		
		
	}
	
	public int getSelectedNum(JComboBox<String> combobox) {return combobox.getSelectedIndex();}
	
	public void buttonclick(Object source) {
		if (source.equals(this.saveBtn)) {
			Vector<String> accidentReceiptContents = new Vector<String>();
			accidentReceiptContents.add(this.receiptId_t.getText());
			accidentReceiptContents.add(this.receiptName_t.getText());
			accidentReceiptContents.add(this.CustomerName_t.getText());
			accidentReceiptContents.add(this.customerId_t1.getText()+"-"+this.customerId_t2.getText());
			accidentReceiptContents.add(this.insType_t.getSelectedItem().toString());
			accidentReceiptContents.add(this.payType_t.getSelectedItem().toString());
			accidentReceiptContents.add(this.date_t.getText());
			accidentReceiptContents.add(this.hospital_t.getText());
			accidentReceiptContents.add(this.death_t.getSelectedItem().toString());
			accidentReceiptContents.add(this.place_t.getText());
			accidentReceiptContents.add(this.diagnos_t.getText());
			accidentReceiptContents.add(this.damage_t.getText());
			accidentReceiptContents.add(this.circumstace_t.getText());
			accidentReceiptContents.add(this.money_t.getText());
			this.insuranceCover.writeAccientReceipt(accidentReceiptContents);
			JOptionPane.showMessageDialog(this, "��� ������ ����� �Ϸ�Ǿ����ϴ�.");
			this.dispose();
		}else if(source.equals(this.cancelBtn)) {
			this.dispose();
		}
	}
	private class ActionHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			buttonclick(e.getSource());
		}
	}
}
