package fi.agileo.primefaces.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.spring.service.ContactService;

@ManagedBean
@SessionScoped
public class ListContacts {

	@ManagedProperty("#{contactService}")
	private ContactService contactService;

	private Contact contact = new Contact();

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Contact> getContactList() {
		return null;
		/*
		// Calling Business Service
		contactService.createContact(contact);
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("The contact " + 
					this.contact.getName() +
					" is saved successfully"));
		return "";
		*/
	}
}
