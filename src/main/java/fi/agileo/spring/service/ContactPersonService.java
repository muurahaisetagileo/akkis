package fi.agileo.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.User;

@Component
public class ContactPersonService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public int createContactPerson(User user, 
			ContactPerson contactPerson) {
		List<ContactPerson> userContacts = user.getContactPersons();
		if (userContacts == null)
			userContacts = new ArrayList<ContactPerson>();
		userContacts.add(contactPerson);
		user.setContactPersons(userContacts);
		contactPerson.setSalesPerson(user);
		em.merge(user);
		this.em.persist(contactPerson);
		return 0;
	}
	
	/* Skype-chatista (toteutusta alla)
	  Tuon rinnalle tarvitaan toinen CreateContact -metodi, 
	  jolla on vain Contact -parametri perusominaisuuksien testausta varten, 
	  siis signaturella public int createContact(Contact contact), 
	  missä on tuo sisältö, mitä tuossa tämänhetkisessä methodissa on.*/
	
	@Transactional
	 public int createContact(ContactPerson contactPerson) {
		 this.em.persist(contactPerson);
		 return 0;
	 }
	
	@Transactional
	public void modifyBasics(ContactPerson contactPerson) {
		em.merge(contactPerson);
	}
	
	public List<ContactPerson> getContactPersonsWithoutCompany(){
		List<ContactPerson> contactPersonsWithoutCompany = 
				(List<ContactPerson>)em.createNamedQuery("ContactPerson.findContactPersonsWithoutCompany")
				.getResultList();
		for (ContactPerson c : contactPersonsWithoutCompany) {
			System.out.println(" ***** ");
			System.out.println(c);
			System.out.println(" ***** ");
		}
		return contactPersonsWithoutCompany;
	}
	
	private String getQueryLikeString(String fieldString) {
		if (fieldString == "")
			return "%";
		return "%" + fieldString + "%";
	}
	
/*	public List<Contact> getContactsOfContactCompany() {
		
	}*/
	
	public List<ContactPerson> seekContactPersons(
			List<Integer> types, 
			String salesManFirstNamesSearch, 
			String salesManLastNameSearch, 
			String countrySearch) {

		String salesManFirstNameSearchExpr;
		String salesManLastNameSearchExpr;
		String countrySearchExpr;
		
		if (types == null || types.size() == 0)
			return new ArrayList<ContactPerson>();
		
		salesManFirstNameSearchExpr = 
				getQueryLikeString(salesManFirstNamesSearch);
		salesManLastNameSearchExpr = 
				getQueryLikeString(salesManLastNameSearch);
		countrySearchExpr = 
				getQueryLikeString(countrySearch);

		List<ContactPerson> seekedContactPersons = 
			(List<ContactPerson>)em.createNamedQuery(
					"ContactPerson.findForSearch").
				setParameter("seekedTypes", types).
				setParameter("salesManFirstNamesSearch", 
						salesManFirstNameSearchExpr).
				setParameter("salesManLastNameSearch", 
						salesManLastNameSearchExpr).
				setParameter("countrySearch", 
						countrySearchExpr).getResultList();
		
		return seekedContactPersons;
	}
	
	@Transactional
	public void setContactPersonsToType(List<ContactPerson> contactPersons, int type) {
		for(ContactPerson c: contactPersons) {
			if (c.getType() >= type)
				continue;
			System.out.println("Setting type " + type + 
					" to contact person " + c);
			c.setType(type);
			em.merge(c);
		}
	}
	
}