package control.checkCustomerInfo;

import java.util.Vector;

import constants.ControlConstants.EIllHistory;
import constants.ControlConstants.EJob;
import constants.ViewConstants.ECustomer;
import control.customer.Customer;
import control.customer.CustomerListImpl;
import model.customer.CustomerDao;
import model.customer.CustomerDaoImpl;

public class CheckCustomerInfoImpl implements CheckCustomerInfo {

	private CustomerListImpl customerList;
	private CustomerDao customerDao;
	public CheckCustomerInfoImpl(){
		this.customerDao = new CustomerDaoImpl();
	}
	
	public void associate(CustomerListImpl customerList) {this.customerList = customerList;}
	public Customer searchCustomerbyId(String customerId){return this.customerList.searchById(customerId);}

	public void addNewCustomerInformation(Vector<String> customerContents){
		Customer customer = new Customer();
		for(ECustomer eCustomer : ECustomer.values()) {
			String value = customerContents.get(eCustomer.ordinal());
			switch (eCustomer.getText()) {
			case "�̸�": customer.setName(value);break;
			case "�ֹε�Ϲ�ȣ": customer.setCustomerId(value);break;
			case "����": 
				if (value.equals("����")) {customer.setGender(false);}
				else {customer.setGender(true);}
				break;
			case "����":customer.setAge(Integer.parseInt(value));break;
			case "��ȭ��ȣ": customer.setPhoneNum(value);break;
			case "����": 
				switch (value) {
				case "����":customer.setJob(EJob.SOLDIER);break;
				case "����":customer.setJob(EJob.POLICE);break;
				case "�ҹ��":customer.setJob(EJob.FIREMAN);break;
				case "�������":customer.setJob(EJob.DRIVER);break;
				case "����":customer.setJob(EJob.NO_JOB);break;
				default:break;
				}
				break;
			case "����": 
				switch (value) {
				case "����":customer.setillHistory(EIllHistory.NOTHING);break;
				case "��":customer.setillHistory(EIllHistory.CANCER);break;
				case "����":customer.setillHistory(EIllHistory.BLOODPRESURE);break;
				case "�索":customer.setillHistory(EIllHistory.GLYCOSURIA);break;
				case "���":customer.setillHistory(EIllHistory.ACCIDENT);break;
				default:break;
				}
				break;
			case "���": customer.setProperty(Integer.parseInt(value));break;
			case "���� ���": 
			default: break;
			}
		}
		this.customerList.add(customer);
		this.customerDao.insert(customer);
	}
}