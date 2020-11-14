package constants;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ViewConstants {
	public static final long serialVersionUID = 1L;
	
	public ViewConstants() {}
	public enum ETableStatus{insuranceRegistration, checkCustomer}
	public enum ELogin{customer, salesman, underwriter, developer,assessment}
	public enum EViewFrame{
		eFont(new Font("���� ���", Font.PLAIN, 15), null),
		eImg(null, new ImageIcon("img/insurance.png")),
		eLoginImg(null, new ImageIcon("img/login.png")),
		eLoginBtnImg(null, new ImageIcon("img/loginBtn.png"));
		private Font font;
		private ImageIcon ImageIcon;
		private EViewFrame(Font font,ImageIcon ImageIcon) {
			this.font = font;
			this.ImageIcon = ImageIcon;
		}
		public Font getFont() {return this.font;}
		public ImageIcon getImageIcon() {return this.ImageIcon;}
	}
	public enum EMainFrame {
		eWidth(580),
		eHeight(800);
		
		private int value;		
		private EMainFrame(int value) {this.value = value;}	
		public int getValue() {return this.value;}
	}
	public enum EPanels{
		acceptInsPanel("AcceptInsPanel"),
		insDevelopmentPanel("InsDevelopmentPanel"),
		insRegistrationPanel("InsRegistrationPanel"),
		checkCustomerPanel("CheckCustomerPanel"),
		insCoverPanel("InsCoverPanel");
		
		private String panel;
		private EPanels(String panel) {this.panel = panel;}
		public String getPanel() {return this.panel;}
	}
	public enum EButton {
		acceptInsBtn(new JButton("���� ����"),"AcceptInsPanel"),
		insDevelopmentBtn(new JButton("���� ����"),"InsDevelopmentPanel"),
		insRegistrationBtn(new JButton("���� ����"),"InsRegistrationPanel"),
		checkCustomerBtn(new JButton("�� Ȯ��"),"CheckCustomerPanel"),
		insCoverBtn(new JButton("���� ó��"),"InsCoverPanel");
		
		private JButton btn;
		private String panelName;
		private EButton(JButton btn,String panelName) {
			this.btn = btn;
			this.panelName = panelName;
		}		
		public JButton getButton() {return this.btn;}
		public String getPanelName() {return this.panelName;}
	}
	
	public enum EAccidentReceiptHead{
		receiptId("��������� ��ȣ"),  receiptName("��������� �̸�"),  customerName("�� �̸�"),
		type("���� ����"), date("��� ��¥"), paymentStatus("���� ����");
		private String text;
		private EAccidentReceiptHead(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	public enum EAccidentReceipt{
		AccidentReceiptId("��������� ��ȣ"), AccidentReceiptName("��������� �̸�"),  CustomerName("�� �̸�"),
		CustomerId("�� �ֹε�Ϲ�ȣ"), InsuranceType("���� ����"), PaymentMethod("���� ���"), 
		AccidentDate("��� ��¥"), HospitalName("������"), DeathStatus("��� ����"),
		AccidentPlace("��� ���"), DiagnosisName("������"), DamageScale("���� �Ը�"),
		AccidentCircumstances("��� ��Ȳ"), Money("���޾�");
		private String text;
		private EAccidentReceipt(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	public enum EInsuranceDesignHead{
		insuranceDesignId("�����ȣ"),writer("�ۼ���"),
		insuranceName("�����̸�"),insuranceType("���� ����"),
		madedate("�ۼ� ��¥"),  approveStatus("���� ����");
		private String text;
		private EInsuranceDesignHead(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	public enum EInsuranceDesign{
		insuranceDesignId("�����ȣ"), writer("�ۼ���"),
		insuranceName("�����̸�"),madedate("�ۼ� ��¥"),
	    guarantee("�����"), payment("���޾�"),
	    period("���� �Ⱓ"),insuranceType("���� ����"),
	    description("���� ����");
		private String text;
		private EInsuranceDesign(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EBasicInsurance{
		id("���� ��ȣ"), name("���� �̸�"), guarantee("�����"), payment("���Ծ�"),
		period("���� �Ⱓ"),type("���� ����"), description("���� ����");
		private String text;
		private EBasicInsurance(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum ECustomerHead{
		name("�̸�"), customerId("�ֹε�Ϲ�ȣ"),  gender("����"),
		age("����"), phoneNum("��ȭ��ȣ");
		private String text;
		private ECustomerHead(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	public enum ECustomer{
		name("�̸�"), customerId("�ֹε�Ϲ�ȣ"),  gender("����"),
		age("����"), phoneNum("��ȭ��ȣ"), job("����"),
		illHistory("����"), propety("���");
		private String text;
		private ECustomer(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	public enum ESalesPersonHead{
		name("�̸�"),  office("�繫�� �ּ�"), phoneNum("��ȭ��ȣ");
		private String text;
		private ESalesPersonHead(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	public enum EInsuranceHead{
		id("��ȣ"), name("�̸�"),  guarantee("�����"), payment("���Ծ�"),
		period("�Ⱓ"), type("���� ����");
		private String text;
		private EInsuranceHead(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EInsuranceRequest{
		id("��ǰ ��ȣ"), name("��ǰ �̸�"), paymentType("���� ���"),
		date("������");
		private String text;
		private EInsuranceRequest(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EcarInsurance{
		type("����"), num("���� ��ȣ"),  history("��� �̷�"), age("����");
		private String text;
		private EcarInsurance(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EFireInsurance{
		type("�ǹ� ����"), area("�ǹ� ����"), price("�ǹ� ��ġ"), age("���� ��¥");
		private String text;
		private EFireInsurance(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EApprovalStatus{
		RequestInsDesign("���� ��û"), ApprovalIns("���� �㰡"), DisApprovalIns("���� �ź�");
		private String text;
		private EApprovalStatus(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	
}
