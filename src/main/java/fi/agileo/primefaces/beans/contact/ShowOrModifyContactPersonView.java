package fi.agileo.primefaces.beans.contact;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.spring.service.ContactPersonService;


@ManagedBean
@SessionScoped
public class ShowOrModifyContactPersonView {
	@ManagedProperty("#{contactPersonService}")
	private ContactPersonService contactPersonService;
	
	private String backPage = "";

	private ContactPerson selectedContactPerson = null;
	
	private boolean modifyBasics = false;
	
	private int originalContactPersonType = 0;
	
	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public String getBackPage() {
		return backPage;
	}

	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}
	
	public ContactPerson getSelectedContactPerson() {
		return selectedContactPerson;
	}

	public void setSelectedContactPerson(ContactPerson selectedContactPerson) {
		this.selectedContactPerson = selectedContactPerson;
	}
	
	public SelectItem[] getContactPersonTypeOptions() {
		SelectItem[] options = new SelectItem[2];
		options[0] = new SelectItem(0, "Contact");
		options[1] = new SelectItem(1, "Lead");
		return options;
	}
	
	public String getContactPersonTypeForView() {
		if (selectedContactPerson == null)
			return "";
		originalContactPersonType = selectedContactPerson.getType();
		switch(originalContactPersonType) {
			case 0 : return "Contact";
			case 1 : return "Lead";
			case 2 : return "Customer";
		}
		return "";
	}
	
	public String getSalesPersonName() {
		if (selectedContactPerson != null &&
			selectedContactPerson.getSalesPerson() != null)
			return 
				selectedContactPerson.getSalesPerson().getLastName() + " " +
				selectedContactPerson.getSalesPerson().getFirstNames();
		return "";
	}
	
	public String toBackPage() {
		return this.getBackPage();
	}
	
	
	public String toShowOrModifyContactPerson() {
		if (selectedContactPerson == null) {
			System.out.println("return to back page " + backPage + 
					", no selection of contact person");
			return backPage;
		}
		modifyBasics = false;
		return "/contactperson/contactperson_showormodify";
	}

	public boolean isModifyBasics() {
		return modifyBasics;
	}

	public void setModifyBasics(boolean modifyBasics) {
		this.modifyBasics = modifyBasics;
	}
	
	public boolean getContactPersonTypeUpdateable() {
		return modifyBasics && this.selectedContactPerson != null &&
				this.selectedContactPerson.getType() != 2;
	}
	
	public String toModifyBasics() {
		return null;
	}
	
	public String saveModifiedContactPerson() {
		contactPersonService.modifyBasics(selectedContactPerson);
		return null;
	}
}
