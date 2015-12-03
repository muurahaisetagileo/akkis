package fi.agileo.primefaces.beans.contact;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.spring.service.ContactService;


@ManagedBean
@SessionScoped
public class ShowOrModifyContactView {
	@ManagedProperty("#{contactService}")
	private ContactService contactService;
	
	private String backPage = "";

	private Contact selectedContact = null;
	
	private boolean modifyBasics = false;
	
	private int originalContactType = 0;
	
	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public String getBackPage() {
		return backPage;
	}

	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}
	
	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(Contact selectedContact) {
		this.selectedContact = selectedContact;
	}
	
	public SelectItem[] getTypeOptions() {
		SelectItem[] options = new SelectItem[2];
		options[0] = new SelectItem(0, "Contact");
		options[1] = new SelectItem(1, "Lead");
		return options;
	}
	
	public String getContactTypeForView() {
		if (selectedContact == null)
			return "";
		originalContactType = selectedContact.getType();
		switch(originalContactType) {
			case 0 : return "Contact";
			case 1 : return "Lead";
			case 2 : return "Customer";
		}
		return "";
	}
	
	public String getSalesPersonName() {
		if (selectedContact != null &&
			selectedContact.getSalesPerson() != null)
			return 
				selectedContact.getSalesPerson().getLastName() + " " +
				selectedContact.getSalesPerson().getFirstNames();
		return "";
	}
	
	public String toBackPage() {
		return this.getBackPage();
	}
	
	
	public String toShowOrModifyContact() {
		if (selectedContact == null) {
			System.out.println("return to back page " + backPage + 
					", no selection of contact");
			return backPage;
		}
		modifyBasics = false;
		return "/contact/showormodifycontact";
	}

	public boolean isModifyBasics() {
		return modifyBasics;
	}

	public void setModifyBasics(boolean modifyBasics) {
		this.modifyBasics = modifyBasics;
	}
	
	public boolean getContactTypeUpdateable() {
		return modifyBasics && this.selectedContact != null &&
				this.selectedContact.getType() != 2;
	}
	
	public String toModifyBasics() {
		return null;
	}
	
	public String saveModifiedContact() {
		contactService.modifyBasics(selectedContact);
		return null;
	}
}
