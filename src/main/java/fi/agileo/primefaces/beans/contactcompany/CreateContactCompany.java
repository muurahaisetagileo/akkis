package fi.agileo.primefaces.beans.contactcompany;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.spring.service.ContactCompanyService;

@ManagedBean
@SessionScoped
public class CreateContactCompany {
	@ManagedProperty("#{contactCompanyService}")
	private ContactCompanyService contactCompanyService;
	
	private ContactCompany contactCompany = new ContactCompany();
	private List<Contact> contactsToSetForContactCompany;
	private List<Contact> contactsWithoutContactCompany;

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

	public List<Contact> getContactsWithoutContactCompany() {
		return contactsWithoutContactCompany;
	}

	public void setContactsWithoutContactCompany(List<Contact> contactsWithoutContactCompany) {
		this.contactsWithoutContactCompany = contactsWithoutContactCompany;
	}
	
	public String saveContactCompany() {		
		contactCompanyService.createContactCompanyAndContactsToIt(contactCompany, contactsToSetForContactCompany);
		return null;
	}
	
}
