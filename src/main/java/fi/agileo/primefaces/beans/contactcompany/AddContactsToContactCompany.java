package fi.agileo.primefaces.beans.contactcompany;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContactService;

@ManagedBean
@SessionScoped
public class AddContactsToContactCompany {
	@ManagedProperty("#{contactCompanyService}")	
	private ContactCompanyService contactCompanyService;

	@ManagedProperty("#{contactService}")	
	private ContactService contactService;
	
	private ContactCompany contactCompany;
	
	private List<Contact> contactsToBeAdded;
	
	public String toAddContacts() {
		return "/contactcompany/addcontactstocontactcompany";
	}
	
	
	public List<Contact> getContactsWithoutCompany() {
		return contactService.getContactsWithoutCompany();
	}

	public ContactCompanyService getContactCompanyService() {
		return contactCompanyService;
	}

	public void setContactCompanyService(ContactCompanyService contactCompanyService) {
		this.contactCompanyService = contactCompanyService;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}
	
	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
	}


	public List<Contact> getContactsToBeAdded() {
		return contactsToBeAdded;
	}


	public void setContactsToBeAdded(List<Contact> contactsToBeAdded) {
		this.contactsToBeAdded = contactsToBeAdded;
	}
	
	public String toAddContactsToContactCompany() {
		contactCompanyService.addContactsToContactCompany(
			contactCompany, contactsToBeAdded);
		return null;
	}
	
}
