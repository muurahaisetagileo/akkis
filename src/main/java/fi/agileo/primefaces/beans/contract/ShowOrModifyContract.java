package fi.agileo.primefaces.beans.contract;

// import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/* import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany; */
import fi.agileo.akkis.jpa.Contract;
/* import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User; */
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class ShowOrModifyContract {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	private Contract contract;
/*	private User user;
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	private List<Deal> deals;*/

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
		System.out.println("ShowOrModifyContract, setContractService");
	}
	
	
	public String toShowContract() {
//		this.contract = contractService.getContractPropertiesForView(contract);
		/* fetch contract information into ContractInformation object 
		   and set its properties to members of this managed bean and 
		   return the view page name */
		System.out.println("toShowContract");
		return "/contract/showormodifycontract";
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		System.out.println("ShowOrModifyContract, setContract, contract " + contract);
		this.contract = contract;
	}
	
/*	public User getUser() {
		return user;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Deal> getDeals() {
		return deals;
	}*/
}
