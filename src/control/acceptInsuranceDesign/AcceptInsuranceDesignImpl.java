package control.acceptInsuranceDesign;

import constants.ViewConstants.EApprovalStatus;
import control.insurance.InsuranceListImpl;
import control.insuranceDevelopment.InsuranceDesign;
import control.insuranceDevelopment.InsuranceDesignListImpl;
import model.insuranceDesign.InsuranceDesignDao;
import model.insuranceDesign.InsuranceDesignDaoImpl;

public class AcceptInsuranceDesignImpl implements AcceptInsuranceDesign {
	private InsuranceDesignListImpl insuranceDesignList;
	private InsuranceListImpl insuranceList;
	private InsuranceDesignDao insuranceDesignDao;
	
	public AcceptInsuranceDesignImpl(){}
	public void associate(InsuranceDesignListImpl insuranceDesignList, InsuranceListImpl insuranceList) {
		this.insuranceDesignDao = new InsuranceDesignDaoImpl();
		this.insuranceDesignList = insuranceDesignList;
		this.insuranceList = insuranceList;
	}
	public void approve(int insuranceDesignId){
		for(InsuranceDesign insuranceDesign : this.insuranceDesignList.getInsuranceDesignList()) {
			if (insuranceDesign.getInsuranceDesignId() == insuranceDesignId) {
				int insuranceId = insuranceDesignId+(insuranceDesign.getInsurance().getInsuranceType().ordinal()+1)*1000;
				insuranceDesign.getInsurance().setInsuranceId(insuranceId);
				this.insuranceList.add(insuranceDesign.getInsurance());
				insuranceDesign.setApprovalStatus(EApprovalStatus.ApprovalIns);
				this.insuranceDesignDao.update(insuranceDesign);
			}
		}
	}
	public void disapprove(int insuranceDesignId){
		for(InsuranceDesign insuranceDesign : this.insuranceDesignList.getInsuranceDesignList()) {
			if (insuranceDesign.getInsuranceDesignId() == insuranceDesignId) {
				int insuranceId = insuranceDesignId+(insuranceDesign.getInsurance().getInsuranceType().ordinal()+1)*1000;
				insuranceDesign.getInsurance().setInsuranceId(insuranceId);
				insuranceDesign.setApprovalStatus(EApprovalStatus.DisApprovalIns);
				this.insuranceDesignDao.update(insuranceDesign);
				this.insuranceDesignDao.delete(insuranceDesignId);
				this.insuranceDesignList.searchById(insuranceDesignId).setApprovalStatus(EApprovalStatus.DisApprovalIns);
			}
		}
	}
}