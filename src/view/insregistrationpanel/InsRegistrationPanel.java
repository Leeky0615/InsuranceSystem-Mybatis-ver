package view.insregistrationpanel;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;

import constants.ViewConstants.ELogin;
import control.customer.CustomerListImpl;
import control.insurance.InsuranceListImpl;
import control.insuranceRegistration.InsuranceRegistrationImpl;
import control.salesPerson.SalesPersonListImpl;
import main.Menu;
import view.defaultClass.DefaultPanel;
import view.insregistrationpanel.acceptRegistPanel.AcceptRegistrationPanel;
import view.insregistrationpanel.requestPanel.RequestPanel;
import view.insregistrationpanel.searchInsPanel.SearchInsurancePanel;
import view.mainFrame.MainFrame;

public class InsRegistrationPanel extends DefaultPanel {
	private static final long serialVersionUID = 1L;
	private SalesPersonListImpl salesPersonList;
	private InsuranceListImpl insuranceList;
	private CustomerListImpl customerListImpl;
	private InsuranceRegistrationImpl insuranceRegistration;
	
	private JTabbedPane jTabbedPane;
	private RequestPanel requestpanel;
	private SearchInsurancePanel searchInsurancePanel;
	private AcceptRegistrationPanel acceptRegistrationPanel;
	
	public InsRegistrationPanel(Menu menu) {
		super(menu);
		this.insuranceRegistration = (InsuranceRegistrationImpl) this.menu.getInsuranceRegistration();
		this.salesPersonList = (SalesPersonListImpl) this.menu.getSalesPersonList();
		this.insuranceList = (InsuranceListImpl) this.menu.getInsuranceList();
		this.customerListImpl = (CustomerListImpl) this.menu.getCustomerList();
		this.setLayout(new BorderLayout());
		
		this.createPanel();
	}

	public void createPanel() {
		this.jTabbedPane = new JTabbedPane();
		if (MainFrame.user == ELogin.underwriter) {
			this.acceptRegistrationPanel = new AcceptRegistrationPanel(this.insuranceRegistration,this.customerListImpl);
			this.jTabbedPane.add("���� ����",this.acceptRegistrationPanel);
		}else {
			this.requestpanel = new RequestPanel(this.salesPersonList);
			this.jTabbedPane.add("��� ��û",this.requestpanel);
			this.searchInsurancePanel = new SearchInsurancePanel(this.insuranceRegistration, this.insuranceList,"Registration");
			this.jTabbedPane.add("���� ����",this.searchInsurancePanel);
		}
		
		this.add(jTabbedPane, BorderLayout.CENTER);		
	}
}
