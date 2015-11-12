package fi.agileo.spring.service;

import java.util.List;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;

public class ContactCompanyInformation {
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	private List<Contract> contracts;
	
	public ContactCompanyInformation(
			ContactCompany contactCompany,
			List<Contact> contacts,
			List<Contract> contracts) {
		this.contactCompany = contactCompany;
		this.contacts = contacts;
		this.contracts = contracts;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<Contract> getContracts() {
		return contracts;
	}
}
