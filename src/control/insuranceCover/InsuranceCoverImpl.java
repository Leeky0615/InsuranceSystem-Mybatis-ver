package control.insuranceCover;

import java.util.Vector;

import constants.ControlConstants.EInsuranceType;
import constants.ControlConstants.EPaymentMethod;
import constants.ViewConstants.EAccidentReceipt;
import control.accidentRecipt.AccidentReceipt;
import control.accidentRecipt.AccidentReceiptListImpl;
import model.accidentReceipt.AccidentReceiptDao;
import model.accidentReceipt.AccidentReceiptDaoImpl;

public class InsuranceCoverImpl implements InsuranceCover {
	private AccidentReceiptListImpl accidentReceiptList;
	private AccidentReceiptDao accidentReceiptDao;
	public InsuranceCoverImpl(){
		this.accidentReceiptDao = new AccidentReceiptDaoImpl();
	}
	public void associate(AccidentReceiptListImpl accidentReceiptList) {
		this.accidentReceiptList = accidentReceiptList;
	}
	
	public void acceptAccident(int accidentReceiptId){
		AccidentReceipt accidentReceipt = this.accidentReceiptList.searchById(accidentReceiptId);
		accidentReceipt.setPaymentStatus(true);
		this.accidentReceiptDao.update(true,accidentReceiptId);
		System.out.println(accidentReceipt.isPaymentStatus());
	}
	
	public void writeAccientReceipt(Vector<String> accidentReceiptContents){
		AccidentReceipt accidentReceipt = new AccidentReceipt();
		for(EAccidentReceipt eAccidentReceipt : EAccidentReceipt.values()) {
			String value = accidentReceiptContents.get(eAccidentReceipt.ordinal());
			switch (eAccidentReceipt.getText()) {
			case "��������� ��ȣ": accidentReceipt.setAccidentReceiptId(Integer.parseInt(value));break;
			case "��������� �̸�": accidentReceipt.setAccidentReceiptName(value);break;
			case "�� �̸�": accidentReceipt.setCustomerName(value);break;
			case "�� �ֹε�Ϲ�ȣ":accidentReceipt.setCustomerId(value);break;
			case "���� ����": 
				switch (value) {
				case "�ڵ��� ����":accidentReceipt.setInsuranceType(EInsuranceType.CAR);break;
				case "�� ����":accidentReceipt.setInsuranceType(EInsuranceType.CANCER);break;
				case "ȭ�� ����":accidentReceipt.setInsuranceType(EInsuranceType.FIRE);break;
				default:break;
				}
				break;
			case "���� ���": 
				switch (value) {
				case "���� ����":accidentReceipt.setPaymentMethod(EPaymentMethod.CASH);	break;
				case "ī�� ����":accidentReceipt.setPaymentMethod(EPaymentMethod.CARD);	break;
				case "���� ��ü":accidentReceipt.setPaymentMethod(EPaymentMethod.ACCOUNT);break;
				default:break;
				}
				break;
			case "��� ��¥": accidentReceipt.setAccidentDate(value);break;
			case "������": accidentReceipt.setHospitalName(value);break;
			case "��� ����": 
				if (value.equals("�ƴϿ�")) {accidentReceipt.setDeathStatus(false);}
				else {accidentReceipt.setDeathStatus(true);}
				break;
			case "��� ���": accidentReceipt.setAccidentPlace(value);break;
			case "������": accidentReceipt.setDiagnosisName(value);break;
			case "���� �Ը�": accidentReceipt.setDamageScale(value);break;
			case "��� ��Ȳ": accidentReceipt.setAccidentCircumstances(value);break;
			case "���޾�": accidentReceipt.setMoney(Integer.parseInt(value));break;
			default: break;
			}
		}
		this.accidentReceiptDao.insert(accidentReceipt);
		this.accidentReceiptList.add(accidentReceipt);
	}
}