package fi.agileo.primefaces.beans;

import java.util.List;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.spring.service.ContactCompanyService;

public class ShowContactCompany {
	private ContactCompanyService contactCompanyService;
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	private List<Contract> contracts;
	
	public String toShowContactCompany() {
		return "";
	}

	public ContactCompanyService getContactCompanyService() {
		return contactCompanyService;
	}

	public void setContactCompanyService(ContactCompanyService contactCompanyService) {
		this.contactCompanyService = contactCompanyService;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Contract> getContracts() {
		return contracts;
	}
}
