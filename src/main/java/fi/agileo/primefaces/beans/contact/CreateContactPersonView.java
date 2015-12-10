package fi.agileo.primefaces.beans.contact;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.primefaces.beans.user.LoginUserView;
import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContactPersonService;

@ManagedBean
@SessionScoped
public class CreateContactPersonView {

	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;

	private ContactPerson contactPerson = null;

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public String toCreateContactPerson() {
		System.out.println("toCreateContactPerson");
		initializeFields();
		return "/contactperson/contactperson_create";
	}
	
	public void initializeFields() {
		contactPerson = new ContactPerson();
	}
	
	
	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String saveContactPerson() {
		LoginUserView lu = (LoginUserView)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUserView");
		User currentUser = lu.getUser();
		System.out.println(currentUser.getUsername());
		this.contactPersonService.createContactPerson(currentUser, contactPerson);
		return null;
	}
}
