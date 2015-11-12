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
	public int createContact(User user, Contact contact) {
		this.em.persist(contact);
		return 0;
	}
	
	public List<Contact> getContacts(int type, 
			User responsible_salesman, String country) {
		
		/*
		List<Contact> contacts = (List<Contact>)em.createNamedQuery("getContacts");
		for (Contact c : contacts) {
			System.out.println(c);
		}
s		*/
		
		return null;
	}
	
}