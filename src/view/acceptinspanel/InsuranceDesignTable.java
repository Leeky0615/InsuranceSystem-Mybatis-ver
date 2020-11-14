package view.acceptinspanel;

import java.util.Vector;

import constants.ViewConstants.EApprovalStatus;
import control.insuranceDevelopment.InsuranceDesign;
import control.insuranceDevelopment.InsuranceDesignListImpl;
import view.defaultClass.Default_InsuranceDesignTable;

public class InsuranceDesignTable extends Default_InsuranceDesignTable {
	private static final long serialVersionUID = 1L;
	
	private InsuranceDesignListImpl insuranceDesignList;

	public InsuranceDesignTable(InsuranceDesignListImpl insuranceDesignList, boolean acceptState) {
		super();
		this.insuranceDesignList = insuranceDesignList;
		this.refresh(acceptState);
	}

	public void refresh(boolean acceptState) {
		this.rowData.clear();
		for (InsuranceDesign insuranceDesign : this.insuranceDesignList.getInsuranceDesignList()) {
			Vector<Object> insuranceDesigns = new Vector<Object>();
			// ���ο�û ���� ������ �����ִ� ���̺�
			if (acceptState) {
				if (insuranceDesign.getApprovalStatus().equals(EApprovalStatus.ApprovalIns) || insuranceDesign.getApprovalStatus().equals(EApprovalStatus.DisApprovalIns)) {
					insuranceDesigns.add(insuranceDesign.getInsuranceDesignId());
					insuranceDesigns.add(insuranceDesign.getWriter());
					insuranceDesigns.add(insuranceDesign.getInsurance().getInsuranceName());
					insuranceDesigns.add(insuranceDesign.getInsurance().getInsuranceType().getText());
					insuranceDesigns.add(insuranceDesign.getMadeDate());
					insuranceDesigns.add(insuranceDesign.getApprovalStatus().getText());
					this.rowData.add(insuranceDesigns);
				}
			// ���� ����� ���� ������ �����ִ� ���̺�
			}else {
				System.out.println(insuranceDesign.getApprovalStatus());
				if (insuranceDesign.getApprovalStatus().equals(EApprovalStatus.RequestInsDesign)) {
					insuranceDesigns.add(insuranceDesign.getInsuranceDesignId());
					insuranceDesigns.add(insuranceDesign.getWriter());
					insuranceDesigns.add(insuranceDesign.getInsurance().getInsuranceName());
					insuranceDesigns.add(insuranceDesign.getInsurance().getInsuranceType().getText());
					insuranceDesigns.add(insuranceDesign.getMadeDate());
					insuranceDesigns.add(insuranceDesign.getApprovalStatus().getText());
					this.rowData.add(insuranceDesigns);
				}
			}
		}
		this.updateUI();
	}
}
