package fi.agileo.akkis.test.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContactPersonService;

public class ContactPersonServiceTest {
	
	@Test
	public void testCreation() {
		
	}
	
	private List<ContactPerson> createTestContacts() {
		List<ContactPerson> contacts = new ArrayList<ContactPerson>();
		
		User u1 = new User();
		u1.setFirstNames("Matti");
		u1.setLastName("Vainio");
		
		User u2 = new User();
		u2.setFirstNames("Ulla");
		u2.setLastName("Pietilä");
		
		User u3 = new User();
		u3.setFirstNames("Raimo");
		u3.setLastName("Karppinen");
		
		ContactPerson c1 = new ContactPerson();
		c1.setState("Customer");
		c1.setSalesPerson(u1);
		c1.setCountry("Espanja");
		
		ContactPerson c2 = new ContactPerson();
		c2.setState("Lead");
		c2.setSalesPerson(u2);
		c2.setCountry("Ruotsi");
		
		ContactPerson c3 = new ContactPerson();
		c3.setState("Lead");
		c3.setSalesPerson(u3);
		c3.setCountry("Suomi");
		
		ContactPerson c4 = new ContactPerson();
		c4.setState("Customer");
		c4.setSalesPerson(u3);
		c4.setCountry("Suomi");
		
		contacts.add(c3);
		contacts.add(c4);

		return contacts;
	}
	
	@Test
	public void testSeeking(){
		EntityManager em = mock(EntityManager.class);
		
		List<ContactPerson> contactPersons = 
				createTestContacts();
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        Query mockedQuery4 = mock(Query.class);
        Query mockedQuery5 = mock(Query.class);
        Query mockedQuery6 = mock(Query.class);
        Query mockedQuery7 = mock(Query.class);
        Query mockedQuery8 = mock(Query.class);
        
        List<String> states = new ArrayList<String>();
        states.add("Lead");
        
        when(em.createNamedQuery("ContactPerson.findForSearch")).thenReturn(mockedQuery);
        when(mockedQuery.setParameter("firstNameSearch","%")).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("lastNameSearch","%")).thenReturn(mockedQuery3);
        when(mockedQuery3.setParameter("companyNameSearch","%Yritys%")).thenReturn(mockedQuery4);        
        when(mockedQuery4.setParameter("seekedStates",states)).thenReturn(mockedQuery5);
        when(mockedQuery5.setParameter("salesManFirstNamesSearch", "%Raimo%")).thenReturn(mockedQuery6);
        when(mockedQuery6.setParameter("salesManLastNameSearch", "%Karp%")).thenReturn(mockedQuery7);        
        when(mockedQuery7.setParameter("countrySearch","%Suomi%")).thenReturn(mockedQuery8);
        when(mockedQuery8.getResultList()).thenReturn(contactPersons);

        ContactPersonService cs = new ContactPersonService();
        cs.setEm(em);
        
        assertEquals(cs.seekContactPersons(states, 
        		"",
        		"",
        		"Yritys",
        		"Raimo", "Karp", "Suomi").size(), 2);
	}
	
}