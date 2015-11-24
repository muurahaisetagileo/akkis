package fi.agileo.primefaces.beans.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.spring.service.ContactService;

@ManagedBean
@SessionScoped
public class ListContacts {

	@ManagedProperty("#{contactService}")
	private ContactService contactService;

	private List<Contact> searchedContacts = new ArrayList<Contact>();
	private List<Contact> selectedContacts = new ArrayList<Contact>();

	private String searchSalesmanName;
	private String countrySearch;
	private Integer[] seekedContactTypes;
	
	public SelectItem[] getContactTypeOptions() {
		SelectItem[] contactTypeOptions = new SelectItem[3];
		contactTypeOptions[0] = new SelectItem(0, "Contact");
		contactTypeOptions[1] = new SelectItem(1, "Lead");
		contactTypeOptions[2] = new SelectItem(2, "Customer");
		return contactTypeOptions;
	}
	
	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public List<Contact> getContactList() {
		return this.searchedContacts;
	}
	
	public List<Contact> getSelectedContacts() {
		return this.selectedContacts;
	}
	
	public void setSelectedContacts(List<Contact> selectedContacts) {
		System.out.println("setSelectedContacts");
		for(Contact sc : selectedContacts)
			System.out.println(sc);
		this.selectedContacts = selectedContacts;
	}
	
	public String getSearchSalesmanName() {
		return this.searchSalesmanName;
	}
	
	public void setSearchSalesmanName(String searchSalesmanName) {
		this.searchSalesmanName = searchSalesmanName;
	}
	
	public String getCountrySearch() {
		return this.countrySearch;
	}
	
	public void setCountrySearch(String countrySearch) {
		this.countrySearch = countrySearch;
	}

	public Integer[] getSeekedContactTypes() {
		return this.seekedContactTypes;
	}
	
	public void setSeekedContactTypes(Integer[] seekedContactTypes) {
		this.seekedContactTypes = seekedContactTypes;
	}
	
	public String seekContacts() {
		List<Integer> listSeekedContactTypes = new ArrayList<Integer>();
		for(Integer seekedContactType: seekedContactTypes)
			listSeekedContactTypes.add(seekedContactType);
		this.searchedContacts = 
				contactService.seekContacts(
						listSeekedContactTypes, 
						this.getSearchSalesmanName(),
						this.getCountrySearch());
		System.out.println("SEEKED CONTACTS");
		for(Contact c: searchedContacts)
			System.out.println(c);
		return "";
	}
	
	public String setContactsToLead() {
		System.out.println("setContactsToLead");
		for(Contact sc : this.selectedContacts)
			System.out.println("selectedContact " + sc);
		
		contactService.setContactsToType(this.getSelectedContacts(), 1);
		
		List<Integer> listSeekedContactTypes = new ArrayList<Integer>();
		for(Integer seekedContactType: seekedContactTypes)
			listSeekedContactTypes.add(seekedContactType);
		this.searchedContacts = 
				contactService.seekContacts(
						listSeekedContactTypes, 
						this.getSearchSalesmanName(),
						this.getCountrySearch());
		return "";
	}
	
}
