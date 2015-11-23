package fi.agileo.primefaces.beans.contract;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import fi.agileo.akkis.jpa.ContactCompany;

import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContractService;


@ManagedBean
@SessionScoped
public class CreateContract {
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

    @PostConstruct
    public void init() {
    	this.allContactCompanies = contactCompanyService.getAllContactCompanies();    	
    }
	
	public ContractService getContractService() {
		System.out.println("getContractService");
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		System.out.println("setContractService");
		this.contractService = contractService;
	}
	
	/* Problem: this gets called too often */
	public List<ContactCompany> getContactCompanies() {
		System.out.println("getContactCompanies");
		return this.allContactCompanies;
	}	
	
	/* this gets too easily null value */
	public void setSelectedContactCompany(ContactCompany selectedContactCompany) {
		this.selectedContactCompany = selectedContactCompany;
		if (selectedContactCompany != null) {
			System.out.println("Setting selected contact company not null");
			contactCompanySelected = true;
		}
		System.out.println("Setx Selected contactCompany ");
	}
	
	public ContactCompany getSelectedContactCompany() {
		System.out.println("Getx Selected contactCompany");
		return this.selectedContactCompany;
	}

	public String selectContactCompany() {
		System.out.println("selectContactCompanyx");
/*		System.out.println("Selected contact company " + 
				this.selectedContactCompany);*/
		contactCompanySelected = true;
		return "/contract/create_contract";
	}

	public boolean isContactCompanySelected() {
		System.out.println("isContactCompanySelected");
/*		System.out.println("isContactCompanySelected, returns " +
			contactCompanySelected);*/
		return this.contactCompanySelected;
	}
	
}