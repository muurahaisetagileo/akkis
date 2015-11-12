package fi.agileo.primefaces.beans;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class EditContract {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;

	private User user;
	private Contract contract;
	private ContactCompany contactCompany;
	private List<Contact> currentContacts;
	private List<Contact> contactsToBeAdded;
	private List<Contact> notAddedContactCompanyContacts;
	
	public String toEditContract() {
		return "";
	}
	
	public String saveModifiedContract() {
		return "";
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
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

	public List<Contact> getCurrentContacts() {
		return currentContacts;
	}

	public List<Contact> getNotAddedContactCompanyContacts() {
		return notAddedContactCompanyContacts;
	}

	public List<Contact> getContactsToBeAdded() {
		return contactsToBeAdded;
	}

	public void setContactsToBeAdded(List<Contact> contactsToBeAdded) {
		this.contactsToBeAdded = contactsToBeAdded;
	}
}
