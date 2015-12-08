package fi.agileo.primefaces.beans.contactcompany;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUserView;
import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContactPersonService;

@ManagedBean
@SessionScoped
public class CreateContactCompanyView {
	@ManagedProperty("#{contactCompanyService}")
	private ContactCompanyService contactCompanyService;
	
	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;
	
	private ContactCompany contactCompany = null;
	private List<ContactPerson> contactPersonsToSetForContactCompany;
	private List<ContactPerson> contactPersonsWithoutContactCompany;

	public String toCreateContactCompany() {
		System.out.println("toCreateContactCompany");
		initializeFields();
		return "/contactcompany/contactcompany_create";
	}
	
	public void initializeFields() {
		contactCompany = new ContactCompany();
	}
	
	public String doCreateContactCompany() {
		return "";
	}
	
	public ContactPersonService getContactPersonService() {
		return this.contactPersonService;
	}
	
	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
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

	public List<ContactPerson> getContactPersonsToSetForContactCompany() {
		return contactPersonsToSetForContactCompany;	
	}

	public void setContactPersonsToSetForContactCompany(List<ContactPerson> contactPersonsToSetForContactCompany) {
		this.contactPersonsToSetForContactCompany = contactPersonsToSetForContactCompany;
	}

	public List<ContactPerson> getContactPersonsWithoutContactCompany() {
		contactPersonsWithoutContactCompany = contactPersonService.getContactPersonsWithoutCompany();
		return contactPersonsWithoutContactCompany;
	}

	public void setContactsWithoutContactCompany(List<ContactPerson> contactPersonsWithoutContactCompany) {
		this.contactPersonsWithoutContactCompany = contactPersonsWithoutContactCompany;
	}
	
	public String saveContactCompany() {		
		contactCompanyService.createContactCompanyAndContactsToIt(contactCompany, contactPersonsToSetForContactCompany);
		return null;
	}
	
}
