package fi.agileo.spring.service;

import java.util.ArrayList;
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
		List<Contact> userContacts = user.getContacts();
		if (userContacts == null)
			userContacts = new ArrayList<Contact>();
		userContacts.add(contact);
		user.setContacts(userContacts);
		contact.setSalesPerson(user);
		em.merge(user);
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
	
	public List<Contact> seekContacts(List<Integer> types, String salesManSearch, String countrySearch) {
		
		if (types == null || types.size() == 0)
			return new ArrayList<Contact>();
		
		String salesManSearchExpr;
		String countrySearchExpr;
		if (salesManSearch == "")
			salesManSearchExpr = "%";
		else 
			salesManSearchExpr = "%" + salesManSearch + "%";
		if (countrySearch == "")
			countrySearchExpr = "%";
		else 
			countrySearchExpr = "%" + countrySearch + "%";

		List<Contact> seekedContacts = (List<Contact>)em.createNamedQuery("Contact.findForSearch").
				setParameter("seekedTypes", types).
				setParameter("salesManSearch", salesManSearchExpr).
				setParameter("countrySearch", countrySearchExpr).getResultList();
		
		return seekedContacts;
	}
	
	public List<Contact> seekAllContacts() {
		
		List<Contact> seekedContacts = (List<Contact>)em.createNamedQuery("seekAllContacts")
				.getResultList();
		
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