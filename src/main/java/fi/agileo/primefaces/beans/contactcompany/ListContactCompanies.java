package fi.agileo.primefaces.beans.contactcompany;

import java.util.ArrayList;
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
import fi.agileo.spring.service.ContactService;

@ManagedBean(name="ListContactCompanies")
@SessionScoped
public class ListContactCompanies {
	@ManagedProperty("#{contactCompanyService}")
	private ContactCompanyService contactCompanyService;
	private List<ContactCompany> contactCompanies;
	
	private ContactCompany contactCompany = new ContactCompany();
	private ContactCompany selectedContactCompany = new ContactCompany();

	public ContactCompany getSelectedContactCompany() {
		return selectedContactCompany;
	}

	public void setSelectedContactCompany(ContactCompany selectedContactCompany) {
		this.selectedContactCompany = selectedContactCompany;
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
	
	public void setContactCompanies(List<ContactCompany>contactCompanies) {
		this.contactCompanies = contactCompanies;
	}
	
	public List<ContactCompany> getContactCompanies () {
		return this.contactCompanyService.getAllContactCompanies();
	}
	
	public String showSelectedContactCompany() {
		return "showcontactcompany";
	}
	
}
