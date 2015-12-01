package fi.agileo.akkis.test.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContactService;

public class ContactServiceTest {
	
	@Test
	public void testCreation() {
		
	}
	
	@Test
	public void testSeeking(){
		
	
		
		EntityManager em = mock(EntityManager.class);
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		User u1 = new User();
		u1.setFirstNames("Matti");
		u1.setLastName("Vainio");
		
		User u2 = new User();
		u2.setFirstNames("Ulla");
		u2.setLastName("Pietilä");
		
		User u3 = new User();
		u3.setFirstNames("Raimo");
		u3.setLastName("Karppinen");
		
		Contact c1 = new Contact();
		c1.setType(5);
		c1.setSalesPerson(u1);
		c1.setCountry("Espanja");
		
		Contact c2 = new Contact();
		c2.setType(1);
		c2.setSalesPerson(u2);
		c2.setCountry("Ruotsi");
		
		Contact c3 = new Contact();
		c3.setType(1);
		c3.setSalesPerson(u3);
		c3.setCountry("Suomi");
		
		Contact c4 = new Contact();
		c4.setType(2);
		c4.setSalesPerson(u3);
		c4.setCountry("Suomi");
		
		contacts.add(c3);
		contacts.add(c4);
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        Query mockedQuery4 = mock(Query.class);
        Query mockedQuery5 = mock(Query.class);
        
        List<Integer> types = new ArrayList<Integer>();
        types.add(1);
        
        when(em.createNamedQuery("Contact.findForSearch")).thenReturn(mockedQuery);        
        when(mockedQuery.setParameter("seekedTypes",types)).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("salesManFirstNamesSearch", "%Raimo%")).thenReturn(mockedQuery3);
        when(mockedQuery3.setParameter("salesManLastNameSearch", "%Karp%")).thenReturn(mockedQuery4);        
        when(mockedQuery4.setParameter("countrySearch","%Suomi%")).thenReturn(mockedQuery5);
        when(mockedQuery5.getResultList()).thenReturn(contacts);

        ContactService cs = new ContactService();
        cs.setEm(em);
        
        assertEquals(cs.seekContacts(types, "Raimo", "Karp", "Suomi").size(), 2);
	}
	
}