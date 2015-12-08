package fi.agileo.primefaces.beans.contact;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.spring.service.ContactPersonService;

@ManagedBean
@SessionScoped
public class ListContactPersonsView {

	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;

	private List<ContactPerson> searchedContactPersons = new ArrayList<ContactPerson>();
	private List<ContactPerson> selectedContactPersons = new ArrayList<ContactPerson>();

	private String searchSalesmanFirstNames;
	private String searchSalesmanLastName;
	
	private String countrySearch;
	private Integer[] seekedContactPersonTypes;
	
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
		seekedContactPersonTypes = new Integer[3];
		seekedContactPersonTypes[0] = new Integer(0);
		seekedContactPersonTypes[1] = new Integer(1);
		seekedContactPersonTypes[2] = new Integer(2);
	}
	
	public SelectItem[] getContactPersonTypeOptions() {
		SelectItem[] contactPersonTypeOptions = new SelectItem[3];
		contactPersonTypeOptions[0] = new SelectItem(0, "Contact");
		contactPersonTypeOptions[1] = new SelectItem(1, "Lead");
		contactPersonTypeOptions[2] = new SelectItem(2, "Customer");
		return contactPersonTypeOptions;
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

	public Integer[] getSeekedContactPersonTypes() {
		return this.seekedContactPersonTypes;
	}
	
	public void setSeekedContactPersonTypes(Integer[] seekedContactPersonTypes) {
		this.seekedContactPersonTypes = seekedContactPersonTypes;
	}
	
	public String seekContactPersons() {
		List<Integer> listSeekedContactPersonTypes = new ArrayList<Integer>();
		for(Integer seekedContactPersonType: seekedContactPersonTypes)
			listSeekedContactPersonTypes.add(seekedContactPersonType);
		this.searchedContactPersons = 
				contactPersonService.seekContactPersons(
						listSeekedContactPersonTypes, 
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
		
		contactPersonService.setContactPersonsToType(this.getSelectedContactPersons(), 1);
		
		List<Integer> listSeekedContactPersonTypes = new ArrayList<Integer>();
		for(Integer seekedContactType: seekedContactPersonTypes)
			listSeekedContactPersonTypes.add(seekedContactType);
		this.searchedContactPersons = 
				contactPersonService.seekContactPersons(
						listSeekedContactPersonTypes, 
						this.getSearchSalesmanFirstNames(),
						this.getSearchSalesmanLastName(),
						this.getCountrySearch());
		return "";
	}
	
}
