package fi.agileo.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.User;

@Component
public class ContactService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public void changeToLead(Contact contact){
		
	}
	
	@Transactional
	public void changeToLead(List<Contact> contacts) {
		
	}
	
	@Transactional
	public int createContact(User user, Contact contact) {
		this.em.persist(contact);
		return 0;
	}
	
	/* Skype-chatista (toteutusta alla)
	  Tuon rinnalle tarvitaan toinen CreateContact -metodi, 
	  jolla on vain Contact -parametri perusominaisuuksien testausta varten, 
	  siis signaturella public int createContact(Contact contact), 
	  missä on tuo sisältö, mitä tuossa tämänhetkisessä methodissa on.*/
	
	@Transactional
	 public int createContact(Contact contact) {
		 this.em.persist(contact);
		 return 0;
	 }
	
	public List<Contact> seekContacts(Contact c) {
		
		List<Contact> seekedContacts = (List<Contact>)em.createNamedQuery("getContacts").
				setParameter("type", c.getType()).
				setParameter("salesman", c.getSalesPerson()).
				setParameter("country", c.getCountry()).getResultList();
		
		return seekedContacts;
	}
	
	@Transactional
	public void setContactsToType(List<Contact> contacts, int type) {
		for(Contact c: contacts) {
			c.setType(type);
			em.merge(c);
		}
	}
	
}