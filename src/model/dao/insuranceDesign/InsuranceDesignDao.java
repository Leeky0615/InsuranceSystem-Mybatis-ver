package model.dao.insuranceDesign;

import java.util.ArrayList;

import model.dto.InsuranceDesign;

public interface InsuranceDesignDao {

	public ArrayList<InsuranceDesign> select();
	public void update(InsuranceDesign insuranceDesign);
	public void delete(int insuranceDesignId);
}
