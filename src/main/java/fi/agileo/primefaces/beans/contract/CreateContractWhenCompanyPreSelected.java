package fi.agileo.primefaces.beans.contract;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class CreateContractWhenCompanyPreSelected {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	private ContactCompany contactCompany = new ContactCompany();

	private Contract contract = new Contract();
	
	private List<Contact> selectedContacts;
	
	public ContractService getContractService() {
		return contractService;
	}
	
	public String toCreateContract() {
		System.out.println("toCreateContract, contactCompany " + contactCompany);
		return "/contract/create_contract_when_company_selected";
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
		System.out.println("CreateContractWhenCompany selected, setContact company " + this.contactCompany);
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	

	public List<Contact> getSelectedContacts() {
		return selectedContacts;
	}

	public void setSelectedContacts(List<Contact> selectedContacts) {
		this.selectedContacts = selectedContacts;
	}
	
	public String saveContract() {
		System.out.println("saveContract");
		LoginUser lu = (LoginUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUser");
		contractService.createContract(
				this.getContract(),
				lu.getUser(), 
				this.getContactCompany(), 
				this.getSelectedContacts());		
		return "";
	}
}
