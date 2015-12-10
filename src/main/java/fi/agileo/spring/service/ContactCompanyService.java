package fi.agileo.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.User;
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
			List<ContactPerson> contactPersons) {
		
		contactCompany.setContactPersons(contactPersons);
		
		for (ContactPerson c : contactPersons) {
			c.setContactCompany(contactCompany);
			em.merge(c);
		}
		
		em.persist(contactCompany);
	}
	
	public List<ContactPerson> seekContactsOfCompanyForSalesman(
			ContactCompany contactCompany,
			User salesManUser) {
		return (List<ContactPerson>)
				(em.createNamedQuery("ContactPerson.contactsOfCompanyForSalesman")
						.setParameter("user", salesManUser)
						.setParameter("company", contactCompany));
	}
	
	
	@Transactional
	public void modifyBasicContactCompanyProperties(ContactCompany contactCompany) {
		em.merge(contactCompany);
	}
	
	@Transactional
	public void addContactsToContactCompany(
			ContactCompany contactCompany,
			List<ContactPerson> contactPersons) {
		contactCompany.getContactPersons().addAll(contactPersons);
		for(ContactPerson contactPerson : contactPersons) {
			contactPerson.setContactCompany(contactCompany);
			em.merge(contactPerson);
		}
		em.merge(contactCompany);
	}
	
	public List<ContactCompany> getAllContactCompanies() {
		return (List<ContactCompany>)em.createNamedQuery(
				"ContactCompany.allAlphabeticallyByName").
				getResultList();
	}
}
