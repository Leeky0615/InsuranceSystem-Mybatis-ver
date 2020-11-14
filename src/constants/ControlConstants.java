package constants;

import control.insurance.CancerInsurance;
import control.insurance.CarInsurance;
import control.insurance.FireInsurance;
import control.insurance.Insurance;

public class ControlConstants {
	public static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static String DB_URL = "jdbc:mysql://localhost:3306/insurance?serverTimezone=UTC";
	public static String USER_NAME = "root";
	public static String PASSWORD = "1234";
	public ControlConstants() {}
	
	// Accident Receipt & ContractCondition
	public interface EInsurance{
		public String getText();
	}
	public enum EInsuranceType{
		CAR("�ڵ��� ����", new CarInsurance()),
		FIRE("ȭ�� ����", new FireInsurance()),
		CANCER("�� ����",new CancerInsurance());
		private String text;
		private Insurance insurance;
		private EInsuranceType(String text,Insurance insurance) {
			this.text = text;
			this.insurance = insurance;
			}
		public String getText() {return this.text;}
		public Insurance getInsurance() {return this.insurance;}
	}
	public enum EPaymentMethod{
		CASH("���� ����"),  CARD("ī�� ����"),  ACCOUNT("���� ��ü");
		private String text;
		private EPaymentMethod(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	
	// Customer
	public enum EJob{
		SOLDIER("����"),  POLICE("����"),  FIREMAN("�ҹ��"),
		DRIVER("�������"),NO_JOB("����");
		private String text;
		private EJob(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EIllHistory{
		NOTHING("����"),  CANCER("��"),  BLOODPRESURE("����"),
		GLYCOSURIA("�索"),ACCIDENT("���");
		private String text;
		private EIllHistory(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum ECarType{
		SMALL("������"),  MID("������"),  LARGE("������"),
		MOTOR("�������");
		private String text;
		private ECarType(String text) {this.text = text;}
		public String getText() {return this.text;}
	}
	public enum EBuildingType{
		APARTMENT("����Ʈ"),  HOUSE("����"),  FACTORY("����"),
		WAREHOUSE("â��"), BUILDING("����");
		private String text;
		private EBuildingType(String text) {this.text = text;}
		public String getText() {return this.text;}
	}

}
