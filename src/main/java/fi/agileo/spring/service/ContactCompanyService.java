package fi.agileo.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;


@Component
public class ContactCompanyService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public void createContactCompanyAndContactsToIt(
			ContactCompany contactCompany, 
			List<Contact> contacts) {
	}
	
	@Transactional
	public void addContactsToContactCompany(
			ContactCompany contactCompany,
			List<Contact> contacts) {
		
	}
	
	public ContactCompanyInformation getContactCompanyInformation(ContactCompany contactCompany) {
		return null;
	}
	
}
