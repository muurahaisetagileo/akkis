package fi.agileo.primefaces.beans.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.spring.service.ContactService;

@ManagedBean(name="ListContacts")
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
		
		// Mokkitietoja testausta varten
		
		/*
		
		Contact c = new Contact();
		c.setContactId(1);
		c.setName("Kalle");
		c.setAddress("ad1");
		
		Contact c2 = new Contact();
		c2.setContactId(2);
		c2.setName("Ile");
		c2.setAddress("ad2");
		
		Contact c3 = new Contact();
		c3.setContactId(3);
		c3.setName("Essi");
		c3.setAddress("ad3");
		
		ArrayList<Contact> l = new ArrayList<Contact>();
		l.add(c);
		l.add(c2);
		l.add(c3);
		
		return l;
		*/
		
		return contactService.seekAllContacts();
		
		//return null;
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
