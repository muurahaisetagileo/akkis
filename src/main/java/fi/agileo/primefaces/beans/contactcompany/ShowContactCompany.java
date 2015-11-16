package fi.agileo.primefaces.beans.contactcompany;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.spring.service.ContactCompanyService;

@ManagedBean
@SessionScoped
public class ShowContactCompany {
	@ManagedProperty("#{contactCompanyService}")
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
