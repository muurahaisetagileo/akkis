package fi.agileo.primefaces.beans.contract;

import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class CreateContract {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;

	@ManagedProperty("#{contactCompanyService}")
	private ContactCompanyService contactCompanyService;
	
	private List<ContactCompany> allContactCompanies;
	private HashMap<Long,ContactCompany> allContactCompaniesById;
	private Contract contract;
	private User user;
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	private boolean contactCompanySelected;
	
	public String doCreateContract() {
		/* Create got properties to database through service layer */
		return "";
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}
						   // contactCompanies
	public List<ContactCompany> getContactCompanies() {
		System.out.println("getContactCompanies");
		this.allContactCompanies = contactCompanyService.getAllContactCompanies();
		return this.allContactCompanies;
	}
	
	public void setSelectedContactCompany(ContactCompany cc) {
		this.contactCompany = cc;
	}
	
	public void setSelectedContactCompanyId(long contactCompanyId) {
		Long l = new Long(contactCompanyId);
		this.contactCompany = allContactCompaniesById.get(l);
		contactCompanySelected = true;
	}
	
	public List<Contact> getContactcompanyContacts() {
		return this.contactCompany.getCompanyContacts();
	}
	
	public String selectContactCompany() {
		return "";
	}
	
	public boolean isContactCompanySelected() {
		return contactCompanySelected;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	// Save contract to be updated
	public String saveContract() {
		LoginUser lu = (LoginUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUser");
		User currentUser = lu.getUser();
		System.out.println(currentUser.getUsername());
		this.contractService.createContract(contract, currentUser, contactCompany, contacts);
		return null;
	}
}
