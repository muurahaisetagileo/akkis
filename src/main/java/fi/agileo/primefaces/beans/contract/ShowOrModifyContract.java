package fi.agileo.primefaces.beans.contract;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.Contact;

import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;
/* import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User; */
import fi.agileo.spring.service.ContractService;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class ShowOrModifyContract {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	private Contract contract;
	private boolean modifyBasics = false;
	private List<Contact> contactsToBeAdded = new ArrayList<Contact>();

	private Long selectedTechnicationUser;
	private User technicianUser;
	private List<User> technicianUsers;
	private Map<Long, User> mapUserIdsToTechnicalUsers;
	
	
	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String toShowContract() {
		if (this.contract == null)
			return "/contactcompany/showormodifycontactcompany";
		return "/contract/showormodifycontract";
	}

	public List<SelectItem> getTechnicationUsers() {
		
		List<String> technicianRoles = new ArrayList<String>();
		List<SelectItem> technicianUserOptions = new ArrayList<SelectItem>();
		technicianRoles.add("TECHNICIAN");
		technicianUsers = userService.getUserByRolesInNameOrder(technicianRoles);
		mapUserIdsToTechnicalUsers = new HashMap<Long, User>();
		
		for(User technician: technicianUsers) {
			technicianUserOptions.add(
					new SelectItem(new Long(technician.getUserId()), 
						technician.getLastName() + " " + 
						technician.getFirstNames()));
			mapUserIdsToTechnicalUsers.put(new Long(technician.getUserId()), 
					technician);
		}
		if (technicianUsers.size() > 0)
			selectedTechnicationUser = technicianUsers.get(0).getUserId();  
		return technicianUserOptions;
	}
	
	public SelectItem[] getStatusOptions() {
		SelectItem[] options = new SelectItem[4];
		options[0] = new SelectItem("Draft", "Draft");
		options[1] = new SelectItem("Signed", "Signed");
		options[2] = new SelectItem("Expired", "Expired");
		options[3] = new SelectItem("canceled", "Canceled");
		return options;
	}
	
	public SelectItem[] getPricePeriodTypeOptions() {
		SelectItem[] options = new SelectItem[3];
		options[0] = new SelectItem(new Integer(0), "Only once");
		options[1] = new SelectItem(new Integer(1), "Per month");
		options[2] = new SelectItem(new Integer(2), "Per year");
		return options;
	}
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
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
	
	public String getTechnicationUserName() {
		if (contract.getTechnicianUser() != null)
			return contract.getTechnicianUser().getLastName() + 
				   " " + 
				   contract.getTechnicianUser().getFirstNames();
		return "";
	}
	
	public String getPricePeriodText() {
		if (contract != null) {
			switch(contract.getPricePeriodType()) {
				case 0 : return "Only once";
				case 1 : return "Per month";
				case 2 : return "Per year";
			}
		}
		return "";
	}
	
	public List<Contact> getContactsInContactCompanyButNotInContract() {
		List<Contact> notChosenCompanyContacts = new ArrayList<Contact>();
		notChosenCompanyContacts.addAll(contract.getContactCompany().getCompanyContacts());
		notChosenCompanyContacts.removeAll(contract.getContacts());
		return notChosenCompanyContacts;
	}
	
	public String saveModifiedContract() {
		contract.setTechnicianUser(technicianUser);
		contractService.modifyContractBasicInfo(contract);
		return null;
	}

	public List<Contact> getContactsToBeAdded() {
		return contactsToBeAdded;
	}

	public void setContactsToBeAdded(List<Contact> contactsToBeAdded) {
		this.contactsToBeAdded = contactsToBeAdded;
	}
	
	public String toAddContactsToContract() {
		contractService.addContactsToContract(contract, contactsToBeAdded);
		return null;
	}
	
	public Long getSelectedTechnicationUser() {
		return this.selectedTechnicationUser;
		
	}

	public void setSelectedTechnicationUser(Long selectedTechnicationUser) {
		this.selectedTechnicationUser = selectedTechnicationUser;
		this.technicianUser = mapUserIdsToTechnicalUsers.get(selectedTechnicationUser);
	}

	public List<User> getTechnicianUsers() {
		return technicianUsers;
	}

	public void setTechnicianUsers(List<User> technicianUsers) {
		this.technicianUsers = technicianUsers;
	}
}
