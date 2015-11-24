package fi.agileo.primefaces.beans.contract;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class CreateContractWhenCompanyPreSelected {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	private ContactCompany contactCompany = new ContactCompany();

	public ContractService getContractService() {
		return contractService;
	}
	
	public String toCreateContract() {
		System.out.println("toCreateContract, contactCompany " + contactCompany);
		return "/contract/create_contract_when_company_selected";
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
		System.out.println("CreateContractWhenCompany selected, setContact company " + this.contactCompany);
	}

	
	
	
}
