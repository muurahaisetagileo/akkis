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
	
	@Transactional
	public void modifyBasics(Contact contact) {
		em.merge(contact);
	}
	
	public List<Contact> getContactsWithoutCompany(){
		List<Contact> contactsWithoutCompany = (List<Contact>)em.createNamedQuery("Contact.findContactsWithoutCompany")
				.getResultList();
		for (Contact c : contactsWithoutCompany) {
			System.out.println(" ***** ");
			System.out.println(c);
			System.out.println(" ***** ");
		}
		return contactsWithoutCompany;
	}
	
	private String getQueryLikeString(String fieldString) {
		if (fieldString == "")
			return "%";
		return "%" + fieldString + "%";
	}
	
/*	public List<Contact> getContactsOfContactCompany() {
		
	}*/
	
	public List<Contact> seekContacts(
			List<Integer> types, 
			String salesManFirstNamesSearch, 
			String salesManLastNameSearch, 
			String countrySearch) {

		String salesManFirstNameSearchExpr;
		String salesManLastNameSearchExpr;
		String countrySearchExpr;
		
		if (types == null || types.size() == 0)
			return new ArrayList<Contact>();
		
		salesManFirstNameSearchExpr = 
				getQueryLikeString(salesManFirstNamesSearch);
		salesManLastNameSearchExpr = 
				getQueryLikeString(salesManLastNameSearch);
		countrySearchExpr = 
				getQueryLikeString(countrySearch);

		List<Contact> seekedContacts = 
			(List<Contact>)em.createNamedQuery(
					"Contact.findForSearch").
				setParameter("seekedTypes", types).
				setParameter("salesManFirstNamesSearch", 
						salesManFirstNameSearchExpr).
				setParameter("salesManLastNameSearch", 
						salesManLastNameSearchExpr).
				setParameter("countrySearch", 
						countrySearchExpr).getResultList();
		
		return seekedContacts;
	}
	
	@Transactional
	public void setContactsToType(List<Contact> contacts, int type) {
		for(Contact c: contacts) {
			if (c.getType() >= type)
				continue;
			System.out.println("Setting type " + type + " to contact " + c);
			c.setType(type);
			em.merge(c);
		}
	}
	
}