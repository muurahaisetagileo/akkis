package fi.agileo.primefaces.beans.contact;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContactService;

@ManagedBean
@SessionScoped
public class CreateContact {

	@ManagedProperty("#{contactService}")
	private ContactService contactService;

	private Contact contact = null;

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public String toCreateContact() {
		System.out.println("toCreateContact");
		initializeFields();
		return "/contact/create_contact";
	}
	
	public void initializeFields() {
		contact = new Contact();
	}
	
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	
	

	public String saveContact() {
		LoginUser lu = (LoginUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUser");
		User currentUser = lu.getUser();
		System.out.println(currentUser.getUsername());
		this.contactService.createContact(currentUser, contact);
		return null;
	}
}
