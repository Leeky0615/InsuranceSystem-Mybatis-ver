package view.insdevelopmentpanel;

import java.awt.BorderLayout;

import javax.swing.JTabbedPane;

import control.insurance.InsuranceListImpl;
import control.insuranceDevelopment.InsuranceDevelopmentImpl;
import control.insuranceRegistration.InsuranceRegistrationImpl;
import main.Menu;
import view.defaultClass.DefaultPanel;
import view.insregistrationpanel.searchInsPanel.SearchInsurancePanel;

public class InsDevelopmentPanel extends DefaultPanel{
	private static final long serialVersionUID = 1L;
	private InsuranceRegistrationImpl insuranceRegistration;
	private InsuranceDevelopmentImpl insuranceDevelopment;
	private InsuranceListImpl insuranceList;

	private JTabbedPane jTabbedPane;
	private WriteInsurancePanel writeInsurancePanel;
	private SearchInsurancePanel searchInsurancePanel;

	public InsDevelopmentPanel(Menu menu) {
		super(menu);
		this.insuranceRegistration = (InsuranceRegistrationImpl) this.menu.getInsuranceRegistration();		
		this.insuranceDevelopment = (InsuranceDevelopmentImpl) this.menu.getInsuranceDevelopment();
		this.insuranceList = (InsuranceListImpl) this.menu.getInsuranceList();
		createPanel();
	}
	public void createPanel() {
		this.setLayout(new BorderLayout());
		this.jTabbedPane = new JTabbedPane();

		this.writeInsurancePanel = new WriteInsurancePanel(this.insuranceDevelopment);
		this.jTabbedPane.add("���輳�輭 �ۼ��ϱ�",this.writeInsurancePanel);
		this.searchInsurancePanel = new SearchInsurancePanel(this.insuranceRegistration,this.insuranceList,"Development");
		this.jTabbedPane.add("���� ����ȸ",this.searchInsurancePanel);
		this.add(jTabbedPane, BorderLayout.CENTER);		
		updateUI();
	}

}