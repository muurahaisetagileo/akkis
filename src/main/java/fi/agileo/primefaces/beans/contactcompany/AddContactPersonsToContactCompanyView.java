package fi.agileo.primefaces.beans.contactcompany;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContactPersonService;

@ManagedBean
@SessionScoped
public class AddContactPersonsToContactCompanyView {
	@ManagedProperty("#{contactCompanyService}")	
	private ContactCompanyService contactCompanyService;

	@ManagedProperty("#{contactPersonService}")	
	private ContactPersonService contactPersonService;
	
	private ContactCompany contactCompany;
	
	private List<ContactPerson> contactPersonsToBeAdded;
	
	public String toAddContactPersons() {
		return "/contactcompany/contactcompany_addcontactpersons";
	}
	
	public List<ContactPerson> getContactPersonsWithoutCompany() {
		return contactPersonService.getContactPersonsWithoutCompany();
	}

	public ContactCompanyService getContactCompanyService() {
		return contactCompanyService;
	}

	public void setContactCompanyService(ContactCompanyService contactCompanyService) {
		this.contactCompanyService = contactCompanyService;
	}

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}
	
	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
	}


	public List<ContactPerson> getContactPersonsToBeAdded() {
		return contactPersonsToBeAdded;
	}


	public void setContactPersonsToBeAdded(List<ContactPerson> contactPersonsToBeAdded) {
		this.contactPersonsToBeAdded = contactPersonsToBeAdded;
	}
	
	public String toAddContactPersonsToContactCompany() {
		contactCompanyService.addContactsToContactCompany(
			contactCompany, contactPersonsToBeAdded);
		return null;
	}
	
}
