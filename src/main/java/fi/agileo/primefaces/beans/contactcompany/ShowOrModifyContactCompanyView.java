package fi.agileo.primefaces.beans.contactcompany;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUserView;
import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContactPersonService;

@ManagedBean
@SessionScoped
public class ShowOrModifyContactCompanyView {
	@ManagedProperty("#{contactCompanyService}")
	private ContactCompanyService contactCompanyService;

	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;
	
	private ContactCompany contactCompany;
	private List<Contract> contracts;
	private boolean modifyBasics = false;
	
	private User currentUser;
	
	public String toShowContactCompany() {
		System.out.println("toShowContactCompany !!!");
		System.out.println("contactCompany " + contactCompany);
		modifyBasics = false;
		LoginUserView lu = (LoginUserView)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUserView");
		currentUser = lu.getUser();
		if (contactCompany == null)
			return "/contactcompany/contactcompany_list";
		return "/contactcompany/contactcompany_showormodify";
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
		System.out.println("setContactCompany " + contactCompany);
	}

	
	public List<ContactPerson> getContactPersons() {
		return contactPersonService.getContactPersonsOfCompany(
			currentUser, contactCompany);
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public boolean isModifyBasics() {
		return modifyBasics;
	}

	public void setModifyBasics(boolean modifyBasics) {
		this.modifyBasics = modifyBasics;
	}

	public String toModifyBasics() {
		return null;
	}
	
	public Date getNowTime() {
		return new Date();
	}
	
	public String saveModifiedContactCompany() {
		contactCompanyService.modifyBasicContactCompanyProperties(this.getContactCompany());
		return null;
	}

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}
}
