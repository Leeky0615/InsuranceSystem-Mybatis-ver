package control.insuranceRegistration;

import java.util.Vector;

import constants.ControlConstants.EBuildingType;
import constants.ControlConstants.ECarType;
import constants.ControlConstants.EInsuranceType;
import constants.ControlConstants.EPaymentMethod;
import control.customer.Customer;
import control.customer.CustomerListImpl;
import control.insurance.CancerInsurance;
import control.insurance.CarInsurance;
import control.insurance.FireInsurance;
import control.insurance.Insurance;
import control.insurance.InsuranceListImpl;
import control.salesPerson.SalesPersonListImpl;
import model.insuranceRegistration.InsuranceRegistrationDao;
import model.insuranceRegistration.InsuranceRegistrationDaoImpl;

public class InsuranceRegistrationImpl implements InsuranceRegistration {

	private Customer customer;
	private InsuranceListImpl insuranceList;
	private CustomerListImpl customerList;
	private SalesPersonListImpl salesPersonList;
	private InsuranceRegistrationDao insuranceRegistrationDao;
	
	public InsuranceRegistrationImpl(){
		this.insuranceRegistrationDao = new InsuranceRegistrationDaoImpl();
	}
	public void associate(InsuranceListImpl insuranceList, CustomerListImpl customerList, SalesPersonListImpl salesPersonList) {
		this.insuranceList = insuranceList;
		this.customerList = customerList;
		this.salesPersonList = salesPersonList;
	}
	
	public SalesPersonListImpl getSalesPersonList() {return salesPersonList;}
	public void setSalesPersonList(SalesPersonListImpl salesPersonList) {this.salesPersonList = salesPersonList;}
	public Customer getCustomer() {return customer;}
	public void setCustomer(Customer customer) {this.customer = customer;}
	public Insurance searchInsurance(int insuranceId) {return this.insuranceList.searchById(insuranceId);}
	public void approve(Customer customer){
		customer.setRegistrationStatus(false);
		this.insuranceRegistrationDao.update(customer);
	}
	public void disApprove(EInsuranceType eInsuranceType, Customer customer) {
		customer.setRegistrationStatus(false);
		this.insuranceRegistrationDao.update(customer);
		this.insuranceRegistrationDao.delete(eInsuranceType,customer.getId());
	}
	public void request(){
		customer.setRegistrationStatus(true);
		this.insuranceRegistrationDao.update(customer);
	}
	
	public boolean writeCustomerInfomation(String customerName, String customerSID){
		for (Customer customer : this.customerList.getCustomerList()) {
			if (customer.getName().equals(customerName) && customer.getCustomerSID().equals(customerSID)) {
				this.setCustomer(customer);
				return true;
			}
		}
		return false;
	}
	public void writeInsuranceInformation(Insurance insurance, EInsuranceType type, Vector<Object> infos){
		if (type == EInsuranceType.CANCER) {
			insurance = new CancerInsurance();
			((CancerInsurance) insurance).setPaymentMethod((EPaymentMethod) infos.get(0));
			((CancerInsurance) insurance).setPaymentDate((int) infos.get(1));
			this.insuranceRegistrationDao.insert((CancerInsurance) insurance);
		}else if(type == EInsuranceType.CAR) {
			insurance = new CarInsurance();
			((CarInsurance) insurance).setPaymentMethod((EPaymentMethod) infos.get(0));
			((CarInsurance) insurance).setPaymentDate((int) infos.get(1));
			((CarInsurance) insurance).setCarNum((int) infos.get(2));
			((CarInsurance) insurance).setAge((int) infos.get(3));
			((CarInsurance) insurance).setCarType((ECarType) infos.get(4));
			((CarInsurance) insurance).setCarAccidentHistory((boolean) infos.get(5));
			((CarInsurance) insurance).setDriver(this.customer.getName());
			this.insuranceRegistrationDao.insert((CarInsurance) insurance);
		}else if(type == EInsuranceType.FIRE) {
			insurance = new FireInsurance();
			((FireInsurance) insurance).setPaymentMethod((EPaymentMethod) infos.get(0));
			((FireInsurance) insurance).setPaymentDate((int) infos.get(1));
			((FireInsurance) insurance).setArea((int) infos.get(2));
			((FireInsurance) insurance).setAge((int) infos.get(3));
			((FireInsurance) insurance).setBuildingType((EBuildingType) infos.get(4));
			((FireInsurance) insurance).setUnitPrice((int) infos.get(5));
			((FireInsurance) insurance).setContractor(this.customer.getName());
			this.insuranceRegistrationDao.insert((FireInsurance) insurance);
		}
	}
	public Insurance getReadyInsurance(int id) {
		Insurance insurance = this.insuranceRegistrationDao.selectByCancerInsurance(id);
		if (insurance.getInsuranceId() != 0) {return insurance;}
		Insurance insurance1 = this.insuranceRegistrationDao.selectByCarInsurance(id);
		if (insurance1.getInsuranceId() != 0) {return insurance1;}
		Insurance insurance2 = this.insuranceRegistrationDao.selectByFireInsurance(id);
		if (insurance2.getInsuranceId() != 0) {return insurance2;}
		return null;
	}
}