package fi.agileo.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.ContactCompany;
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
		contactPerson.setState("Contact");
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
		 contactPerson.setState("Contact");
		 this.em.persist(contactPerson);
		 return 0;
	 }
	
	@Transactional
	public void modifyBasics(ContactPerson contactPerson) {
		em.merge(contactPerson);
	}
	
	public List<ContactPerson> getContactPersonsOfCompany(
			User currentUser, ContactCompany cc) {
		if (currentUser.getRole().equals("SALESMAN"))
			return (List<ContactPerson>)(em.createNamedQuery("ContactPerson.contactsOfCompanyForSalesman")
					.setParameter("user", currentUser)
					.setParameter("company",cc));
		return cc.getContactPersons();
	}
	
	public List<ContactPerson> getContactPersonsWithoutCompany(
			User currentUser) {
		List<ContactPerson> contactPersonsWithoutCompany = 
				(List<ContactPerson>)(
						em.createNamedQuery("ContactPerson.findContactPersonsWithoutCompany")
						.setParameter("user", currentUser))
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
			List<String> states, 
			String firstNameSearch,
			String lastNameSearch,
			String companyNameSearch,
			String salesManFirstNamesSearch, 
			String salesManLastNameSearch, 
			String countrySearch) {

		System.out.println("seekContactPersonsForSalesman");
		System.out.println("states " + states);
		System.out.println("firstNameSearch " + firstNameSearch);
		System.out.println("lastNameSearch " + lastNameSearch);
		System.out.println("salesManFirstNamesSearch " + salesManLastNameSearch);
		System.out.println("salesManLastNameSearch " + salesManLastNameSearch);
		System.out.println("countrySearch " + countrySearch);
		
		String firstNameSearchExpr;
		String lastNameSearchExpr;
		String companyNameSearchExpr;

		String salesManFirstNameSearchExpr;
		String salesManLastNameSearchExpr;
		String countrySearchExpr;
		
		if (states == null || states.size() == 0)
			return new ArrayList<ContactPerson>();
		
		salesManFirstNameSearchExpr = 
				getQueryLikeString(salesManFirstNamesSearch);
		salesManLastNameSearchExpr = 
				getQueryLikeString(salesManLastNameSearch);
		countrySearchExpr = 
				getQueryLikeString(countrySearch);
		firstNameSearchExpr = 
				getQueryLikeString(firstNameSearch);
		lastNameSearchExpr = 
				getQueryLikeString(lastNameSearch);
		companyNameSearchExpr = 
				getQueryLikeString(companyNameSearch);
		
		System.out.println("salesManFirstNameSearchExpr " + salesManFirstNameSearchExpr);
		System.out.println("salesManLastNameSearchExpr " + salesManLastNameSearchExpr);
		System.out.println("countrySearchExpr " + countrySearchExpr);
		System.out.println("firstNameSearchExpr " + firstNameSearchExpr); 
		System.out.println("lastNameSearchExpr " + lastNameSearchExpr);
		System.out.println("companyNameSearchExpr " + companyNameSearchExpr); 

		
		
		List<ContactPerson> seekedContactPersons;
		if (companyNameSearch.equals(""))
			seekedContactPersons = (List<ContactPerson>)em.createNamedQuery(
					"ContactPerson.findForSearchCompanyFree").
				setParameter("firstNameSearch", firstNameSearchExpr).
				setParameter("lastNameSearch", lastNameSearchExpr).
				setParameter("seekedStates", states).
				setParameter("salesManFirstNamesSearch", 
						salesManFirstNameSearchExpr).
				setParameter("salesManLastNameSearch", 
						salesManLastNameSearchExpr).
				setParameter("countrySearch", 
						countrySearchExpr).getResultList();
		else
			seekedContactPersons = (List<ContactPerson>)em.createNamedQuery(
					"ContactPerson.findForSearch").
				setParameter("firstNameSearch", firstNameSearchExpr).
				setParameter("lastNameSearch", lastNameSearchExpr).
				setParameter("companyNameSearch", companyNameSearchExpr).
				setParameter("seekedStates", states).
				setParameter("salesManFirstNamesSearch", 
						salesManFirstNameSearchExpr).
				setParameter("salesManLastNameSearch", 
						salesManLastNameSearchExpr).
				setParameter("countrySearch", 
						countrySearchExpr).getResultList();
		
		System.out.println("seekedContactPersons:");
		for(ContactPerson cp : seekedContactPersons)
			System.out.println(cp);
		
		return seekedContactPersons;
	}
	
	public List<ContactPerson> seekContactPersonsForSalesman(
			User seekingUser,
			List<String> states, 
			String firstNameSearch,
			String lastNameSearch,
			String companyNameSearch,
			String salesManFirstNamesSearch, 
			String salesManLastNameSearch, 
			String countrySearch) {
		System.out.println("seekContactPersonsForSalesman");
		System.out.println("states " + states);
		System.out.println("firstNameSearch " + firstNameSearch);
		System.out.println("lastNameSearch " + lastNameSearch);
		System.out.println("salesManFirstNamesSearch " + salesManLastNameSearch);
		System.out.println("salesManLastNameSearch " + salesManLastNameSearch);
		System.out.println("countrySearch " + countrySearch);
		
		
		String firstNameSearchExpr;
		String lastNameSearchExpr;
		String companyNameSearchExpr;

		String salesManFirstNameSearchExpr;
		String salesManLastNameSearchExpr;
		String countrySearchExpr;
		
		if (states == null || states.size() == 0)
			return new ArrayList<ContactPerson>();
		
		salesManFirstNameSearchExpr = 
				getQueryLikeString(salesManFirstNamesSearch);
		salesManLastNameSearchExpr = 
				getQueryLikeString(salesManLastNameSearch);
		countrySearchExpr = 
				getQueryLikeString(countrySearch);
		firstNameSearchExpr = 
				getQueryLikeString(firstNameSearch);
		lastNameSearchExpr = 
				getQueryLikeString(lastNameSearch);
		companyNameSearchExpr = 
				getQueryLikeString(companyNameSearch);
		
		System.out.println("salesManFirstNameSearchExpr " + salesManFirstNameSearchExpr);
		System.out.println("salesManLastNameSearchExpr " + salesManLastNameSearchExpr);
		System.out.println("countrySearchExpr " + countrySearchExpr);
		System.out.println("firstNameSearchExpr " + firstNameSearchExpr); 
		System.out.println("lastNameSearchExpr " + lastNameSearchExpr);
		System.out.println("companyNameSearchExpr " + companyNameSearchExpr); 
		
		List<ContactPerson> seekedContactPersons;
		if (companyNameSearch.equals(""))
			seekedContactPersons = 
			(List<ContactPerson>)em.createNamedQuery(
					"ContactPerson.findForSearchForSalesmanCompanyFree").
				setParameter("user", seekingUser).
				setParameter("firstNameSearch", firstNameSearchExpr).
				setParameter("lastNameSearch", lastNameSearchExpr).
				setParameter("seekedStates", states).
				setParameter("salesManFirstNamesSearch", 
						salesManFirstNameSearchExpr).
				setParameter("salesManLastNameSearch", 
						salesManLastNameSearchExpr).
				setParameter("countrySearch", 
						countrySearchExpr).getResultList();
		else
			seekedContactPersons = 
			(List<ContactPerson>)em.createNamedQuery(
					"ContactPerson.findForSearchForSalesman").
				setParameter("user", seekingUser).
				setParameter("firstNameSearch", firstNameSearchExpr).
				setParameter("lastNameSearch", lastNameSearchExpr).
				setParameter("companyNameSearch", companyNameSearchExpr).
				setParameter("seekedStates", states).
				setParameter("salesManFirstNamesSearch", 
						salesManFirstNameSearchExpr).
				setParameter("salesManLastNameSearch", 
						salesManLastNameSearchExpr).
				setParameter("countrySearch", 
						countrySearchExpr).getResultList();
		
		System.out.println("seekedContactPersons:");
		for(ContactPerson cp : seekedContactPersons)
			System.out.println(cp);
		
		return seekedContactPersons;
	}
	
	@Transactional
	public void setContactPersonsToState(List<ContactPerson> contactPersons, String state) {
		for(ContactPerson c: contactPersons) {
			if (c.getState().equals("Customer") &&
				(state.equals("Lead") || state.equals("Contact")))
				continue;
			
			System.out.println("Setting state " + state + 
					" to contact person " + c);
			c.setState(state);
			em.merge(c);
		}
	}
	
}