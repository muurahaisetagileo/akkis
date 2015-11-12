package fi.agileo.spring.service;

import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;

import java.util.List;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.User;

public class ContractInformation {
	private Contract contract;
	private User user;
	private ContactCompany contactCompany;
	private List<Contact> contacts;
	private List<Deal> deals;
	
	public ContractInformation(
			Contract contract,
			User user,
			ContactCompany contactCompany,
			List<Contact> contacts) {
		this.contract = contract;
		this.user = user;
		this.contactCompany = contactCompany;
		this.contacts = contacts;
	}

	public Contract getContract() {
		return contract;
	}

	public User getUser() {
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
	}
}
