package view.checkcustomerpanel;

import java.util.Vector;

import constants.ViewConstants.ETableStatus;
import model.dto.Customer;
import model.service.customer.CustomerListImpl;
import view.defaultClass.Default_CustomerTable;

public class CustomerTable extends Default_CustomerTable{
	private static final long serialVersionUID = 1L;
	#private CustomerListImpl customerList;
	
	public CustomerTable(#CustomerListImpl customerList, ETableStatus insuranceregistration) {
		super();
		this.status = insuranceregistration;
		#this.customerList = customerList;
		this.refresh();
	}
	
	public void refresh() {
		this.rowData.clear();
		for (Customer customer : this.customerList.getCustomerList()) {
			Vector<Object> customers = new Vector<Object>();
			customers.add(customer.getName());
			customers.add(customer.getCustomerSID());
			customers.add(customer.isGender());
			customers.add(customer.getAge()+"세");
			customers.add(customer.getPhoneNum());
			if (this.status == ETableStatus.insuranceRegistration) {
				if (customer.isRegistrationStatus()) {this.rowData.add(customers);}
			}else if(this.status == ETableStatus.checkCustomer){
				this.rowData.add(customers);
			}
		}
		this.updateUI();
	}
}
