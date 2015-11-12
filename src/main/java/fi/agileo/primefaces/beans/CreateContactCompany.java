package fi.agileo.primefaces.beans;

import java.util.List;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;

import fi.agileo.spring.service.ContactCompanyService;

public class CreateContactCompany {
	private ContactCompanyService contactCompanyService;
	private ContactCompany contactCompany;
	private List<Contact> contactsToSetForContactCompany;
	private List<Contact> contactWithoutContactCompany;

	public String toCreateContactCompany() {
		return "";
	}
	
	public String doCreateContactCompany() {
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

	public List<Contact> getContactsToSetForContactCompany() {
		return contactsToSetForContactCompany;	
	}

	public void setContactsToSetForContactCompany(List<Contact> contactsToSetForContactCompany) {
		this.contactsToSetForContactCompany = contactsToSetForContactCompany;
	}

	public List<Contact> getContactWithoutContactCompany() {
		return contactWithoutContactCompany;
	}

	public void setContactWithoutContactCompany(List<Contact> contactWithoutContactCompany) {
		this.contactWithoutContactCompany = contactWithoutContactCompany;
	}
	
}
