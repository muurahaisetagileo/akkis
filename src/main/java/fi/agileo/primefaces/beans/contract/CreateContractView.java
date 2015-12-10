package fi.agileo.primefaces.beans.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUserView;
import fi.agileo.spring.service.ContactPersonService;
import fi.agileo.spring.service.ContractService;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class CreateContractView {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;
	
	private ContactCompany contactCompany = new ContactCompany();

	private Contract contract = new Contract();
	
	private List<ContactPerson> selectedContactPersons;
	
	private Long selectedTechnicationUser;
	private User technicianUser;
	private List<User> technicianUsers;
	private Map<Long, User> mapUserIdsToTechnicalUsers;
	
	private User currentUser;
	
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
	
	
	public String toCreateContract() {
		System.out.println("toCreateContract, contactCompany " + contactCompany);
		LoginUserView lu = (LoginUserView)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUserView");
		currentUser = lu.getUser();
		contract = new Contract();
		return "/contract/contract_create";
	}
	
	public List<ContactPerson> getContactPersons() {
		return (List<ContactPerson>)(contactPersonService.getContactPersonsOfCompany(currentUser, contactCompany));
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
	
	
	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
	

	public List<ContactPerson> getSelectedContactPersons() {
		return selectedContactPersons;
	}

	public void setSelectedContactPersons(List<ContactPerson> selectedContactPersons) {
		this.selectedContactPersons = selectedContactPersons;
	}
	
	public String saveContract() {
		System.out.println("saveContract");
		LoginUserView lu = (LoginUserView)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUserView");
		System.out.println("TechnicianUser " + technicianUser);
		contractService.createContract(
				this.getContract(),
				lu.getUser(), 
				technicianUser,
				this.getContactCompany(), 
				this.getSelectedContactPersons());		
		return "";
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

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

}
