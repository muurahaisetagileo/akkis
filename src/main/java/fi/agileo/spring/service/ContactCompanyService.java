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
		
		contactCompany.setCompanyContacts(contacts);
		
		for (Contact c : contacts) {
			c.setContactCompany(contactCompany);
			em.merge(c);
		}
		
		em.persist(contactCompany);
	}
	
	@Transactional
	public void addContactsToContactCompany(
			ContactCompany contactCompany,
			List<Contact> contacts) {
		
	}
	
	public List<ContactCompany> getAllContactCompanies() {
		return (List<ContactCompany>)em.createNamedQuery(
				"ContactCompany.allAlphabeticallyByName").
				getResultList();
	}
	
	public ContactCompanyInformation getContactCompanyInformation(ContactCompany contactCompany) {
		return null;
	}
	
}
