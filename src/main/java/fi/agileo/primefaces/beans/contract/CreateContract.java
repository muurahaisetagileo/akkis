package fi.agileo.primefaces.beans.contract;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class CreateContract {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	private Contract contract;
	private User user;
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	
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

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
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
		this.contractService.createContract(currentUser, contract);
		return null;
	}
}
