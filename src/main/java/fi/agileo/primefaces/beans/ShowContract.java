package fi.agileo.primefaces.beans;

import java.util.List;

import javax.faces.bean.RequestScoped;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContractService;

@RequestScoped
public class ShowContract {
	private ContractService contractService;
	
	private Contract contract;
	private User user;
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	private List<Deal> deals;

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	
	public String viewContract() {
		/* fetch contract information into ContractInformation object 
		   and set its properties to members of this managed bean and 
		   return the view page name */
		
		return "";
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public User getUser() {
		return user;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Deal> getDeals() {
		return deals;
	}
}
