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
	public int createContact(Contact contact) {
		// Save new Contact to DB
		this.em.persist(contact);
		return 0;
	}
	
	@Transactional
	public List<User> getUsers(User user) {
		
		/*
		
		List<User> users = (List<User>)em.createNamedQuery("getUsers");
		
		for (User u : users) {
			System.out.println(u);
		}
		
		if (users.size() == 1) {
			return users.get(0).getType();
		}
		
		*/
		
		return null;
	}
	
}