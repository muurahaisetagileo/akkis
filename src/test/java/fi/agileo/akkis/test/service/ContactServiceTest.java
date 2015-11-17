package fi.agileo.akkis.test.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		
		/*
		
		EntityManager em = mock(EntityManager.class);
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		User u1 = new User();
		u1.setFirstNames("Matti");
		u1.setFirstNames("Vainio");
		User u2 = new User();
		u2.setFirstNames("Ulla");
		u2.setFirstNames("Pietilä");
		User u3 = new User();
		u3.setFirstNames("Raimo");
		u3.setFirstNames("Karppinen");
		
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
		
		contacts.add(c1);
		contacts.add(c2);
		contacts.add(c3);
		contacts.add(c4);
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        Query mockedQuery4 = mock(Query.class);
        when(mockedQuery.setParameter("type",1)).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("salesman",u3)).thenReturn(mockedQuery3);
        when(mockedQuery2.setParameter("country","Suomi")).thenReturn(mockedQuery4);
        when(mockedQuery3.getResultList()).thenReturn(contacts);
        when(em.createNamedQuery("seekContacts")).thenReturn(mockedQuery);

        ContactService cs = new ContactService();
        cs.setEm(em);
        
        Contact seekContact = new Contact();
        seekContact.setType(1);
        seekContact.setSalesPerson(u3);
        seekContact.setCountry("Suomi");
        
        List<Contact> wantedResult = new ArrayList();
        
        wantedResult.add(c3);
       
        assertEquals(cs.seekContacts(seekContact), wantedResult);
        
        */
        
	}
	
}