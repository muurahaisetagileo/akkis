package fi.agileo.primefaces.beans.contact;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUserView;
import fi.agileo.spring.service.ContactPersonService;

@ManagedBean
@SessionScoped
public class ListContactPersonsView {

	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;

	private List<ContactPerson> searchedContactPersons = new ArrayList<ContactPerson>();
	private List<ContactPerson> selectedContactPersons = new ArrayList<ContactPerson>();

	private String firstNameSearch;
	private String lastNameSearch;
	private String companyNameSearch;
	
	private String searchSalesmanFirstNames;
	private String searchSalesmanLastName;
	
	private String countrySearch;
	private String[] seekedContactPersonStates;
	
	private User currentUser;
	
	public String toContactPersonListForLeads() {
		System.out.println("toContactPersonListForLeads");
		initializeFields();
		return "/contactperson/contactperson_list_for_leads";
	}
	
	public String toContactListForShowOrModify() {
		System.out.println("toContactListForShowOrModify");
		initializeFields();
		return "/contactperson/contactperson_list_for_showormodify";
	}
	
	public void initializeFields() {
		searchSalesmanLastName = "";
		searchSalesmanFirstNames = "";
		seekedContactPersonStates = new String[3];
		seekedContactPersonStates[0] = "Contact";
		seekedContactPersonStates[1] = "Lead";
		seekedContactPersonStates[2] = "Customer";
		
		LoginUserView lu = (LoginUserView)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginUserView");
		currentUser = lu.getUser();
	}
	
	public SelectItem[] getContactPersonStateOptions() {
		SelectItem[] contactPersonStateOptions = new SelectItem[3];
		contactPersonStateOptions[0] = new SelectItem("Contact", "Contact");
		contactPersonStateOptions[1] = new SelectItem("Lead", "Lead");
		contactPersonStateOptions[2] = new SelectItem("Customer", "Customer");
		return contactPersonStateOptions;
	}
	
	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public List<ContactPerson> getContactPersonList() {
		return this.searchedContactPersons;
	}
	
	public List<ContactPerson> getSelectedContactPersons() {
		return this.selectedContactPersons;
	}
	
	public void setSelectedContactPersons(List<ContactPerson> selectedContactPersons) {
		System.out.println("setSelectedContactPersons");
		for(ContactPerson scp : selectedContactPersons)
			System.out.println(scp);
		this.selectedContactPersons = selectedContactPersons;
	}
	
	public String getSearchSalesmanFirstNames() {
		return this.searchSalesmanFirstNames;
	}
	
	public void setSearchSalesmanFirstNames(String searchSalesmanFirstNames) {
		this.searchSalesmanFirstNames = searchSalesmanFirstNames;
	}
	
	public String getSearchSalesmanLastName() {
		return this.searchSalesmanLastName;
	}
	
	public void setSearchSalesmanLastName(String searchSalesmanLastName) {
		this.searchSalesmanLastName = searchSalesmanLastName;
	}
	
	public String getCountrySearch() {
		return this.countrySearch;
	}
	
	public void setCountrySearch(String countrySearch) {
		this.countrySearch = countrySearch;
	}

	public String[] getSeekedContactPersonStates() {
		return this.seekedContactPersonStates;
	}
	
	public void setSeekedContactPersonStates(String[] seekedContactPersonStates) {
		this.seekedContactPersonStates = seekedContactPersonStates;
	}
	
	public String seekContactPersons() {
		List<String> listSeekedContactPersonStates = new ArrayList<String>();
		for(String seekedContactPersonState: seekedContactPersonStates)
			listSeekedContactPersonStates.add(seekedContactPersonState);
		if (currentUser.getRole().equals("SALESMAN")) 
			this.searchedContactPersons = 
					contactPersonService.seekContactPersonsForSalesman(
							this.currentUser,
							listSeekedContactPersonStates,
							this.getFirstNameSearch(),
							this.getLastNameSearch(),
							this.getCompanyNameSearch(),
							this.getSearchSalesmanFirstNames(),
							this.getSearchSalesmanLastName(),
							this.getCountrySearch());
		else 
			this.searchedContactPersons = 
					contactPersonService.seekContactPersons(
							listSeekedContactPersonStates, 
							this.getFirstNameSearch(),
							this.getLastNameSearch(),
							this.getCompanyNameSearch(),
							this.getSearchSalesmanFirstNames(),
							this.getSearchSalesmanLastName(),
							this.getCountrySearch());

		System.out.println("SEEKED CONTACT PERSONS");
		for(ContactPerson c: searchedContactPersons)
			System.out.println(c);
		return "";
	}
	
	public String setContactPersonsToLead() {
		System.out.println("setContactPersonsToLead");
		for(ContactPerson scp : this.selectedContactPersons)
			System.out.println("selectedContactPerson " + scp);
		
		contactPersonService.setContactPersonsToState(this.getSelectedContactPersons(), "Lead");
		
		List<String> listSeekedContactPersonStates = new ArrayList<String>();
		for(String seekedContactState: seekedContactPersonStates)
			listSeekedContactPersonStates.add(seekedContactState);
		
		if (currentUser.getRole().equals("SALESMAN")) 
			this.searchedContactPersons = 
					contactPersonService.seekContactPersonsForSalesman(
							this.currentUser,
							listSeekedContactPersonStates,
							this.getFirstNameSearch(),
							this.getLastNameSearch(),
							this.getCompanyNameSearch(),
							this.getSearchSalesmanFirstNames(),
							this.getSearchSalesmanLastName(),
							this.getCountrySearch());
		else 
			this.searchedContactPersons = 
					contactPersonService.seekContactPersons(
							listSeekedContactPersonStates, 
							this.getFirstNameSearch(),
							this.getLastNameSearch(),
							this.getCompanyNameSearch(),
							this.getSearchSalesmanFirstNames(),
							this.getSearchSalesmanLastName(),
							this.getCountrySearch());
		return "";
	}

	public List<ContactPerson> getSearchedContactPersons() {
		return searchedContactPersons;
	}

	public void setSearchedContactPersons(List<ContactPerson> searchedContactPersons) {
		this.searchedContactPersons = searchedContactPersons;
	}

	public String getFirstNameSearch() {
		return firstNameSearch;
	}

	public void setFirstNameSearch(String firstNameSearch) {
		this.firstNameSearch = firstNameSearch;
	}

	public String getLastNameSearch() {
		return lastNameSearch;
	}

	public void setLastNameSearch(String lastNameSearch) {
		this.lastNameSearch = lastNameSearch;
	}

	public String getCompanyNameSearch() {
		return companyNameSearch;
	}

	public void setCompanyNameSearch(String companyNameSearch) {
		this.companyNameSearch = companyNameSearch;
	}
	
}
