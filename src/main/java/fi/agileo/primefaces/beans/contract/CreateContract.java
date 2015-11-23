package fi.agileo.primefaces.beans.contract;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.ContactCompany;

import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContractService;


@ManagedBean
@SessionScoped
public class CreateContract {
	private String x = "hello";
	
	@ManagedProperty("#{contractService}")
	private ContractService contractService;

	@ManagedProperty("#{contactCompanyService}")
	private ContactCompanyService contactCompanyService; 

	private List<ContactCompany> allContactCompanies;
	private ContactCompany selectedContactCompany = new ContactCompany();

	private boolean contactCompanySelected = false; 
	
	public ContactCompanyService getContactCompanyService() {
		return contactCompanyService;
	}

	public void setContactCompanyService(ContactCompanyService contactCompanyService) {
		this.contactCompanyService = contactCompanyService;
	}

	public ContractService getContractService() {
		System.out.println("getContractService");
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		System.out.println("setContractService");
		this.contractService = contractService;
	}
	
	public List<ContactCompany> getContactCompanies() {
		System.out.println("getContactCompanies");
		this.allContactCompanies = contactCompanyService.getAllContactCompanies();
		return this.allContactCompanies;
	}	
	
	public void setSelectedContactCompany(ContactCompany cc) {
		this.selectedContactCompany = cc;
		System.out.println("Set Selected contactCompany " + cc);
	}
	
	public ContactCompany getSelectedContactCompany() {
		System.out.println("Get Selected contactCompany " + selectedContactCompany);
		return this.selectedContactCompany;
	}

	public String selectContactCompany() {
		System.out.println("selectContactCompany");
		contactCompanySelected = true;
		return "";
	}

	public boolean isContactCompanySelected() {
		System.out.println("isContactCompanySelected, returns " +
			contactCompanySelected);
		return this.contactCompanySelected;
	}
	
}