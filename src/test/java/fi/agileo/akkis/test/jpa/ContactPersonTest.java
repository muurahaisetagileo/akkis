package fi.agileo.akkis.test.jpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;

public class ContactPersonTest {
	public static final double EPSILON = 1e-15;

	@Test
	public void testBasicContactCreation() {
		ContactPerson c = new ContactPerson();
		
		c.setContactPersonId(50);
		c.setName("Rauno Hakola");
		c.setAddress("Kalliokatu 2B");
		c.setEmail("ransu@mail.com");
		c.setPhone("0402342345");
		c.setCountry("Finland");
		c.setType(2);
		c.setPublicAvailability(true);
		
		assertEquals(c.getContactPersonId(), 50);
		assertEquals(c.getName(), "Rauno Hakola");
		assertEquals(c.getAddress(), "Kalliokatu 2B");
		assertEquals(c.getEmail(), "ransu@mail.com");
		assertEquals(c.getPhone(), "0402342345" );
		assertEquals(c.getCountry(), "Finland");
		assertEquals(c.getType(), 2);
		assertEquals(c.isPublicAvailability(), true);
		
	}
	
	@Test
	public void testRelationShips() {
		ContactPerson c = new ContactPerson();
		
		List<Contract> contracts = new ArrayList<Contract>();
		User u = new User();
		ContactCompany cc = new ContactCompany();
		
		c.setContracts(contracts);
		c.setContactCompany(cc);
		c.setSalesPerson(u);
			
		assertEquals(c.getContracts(), contracts);
		assertEquals(c.getSalesPerson(), u);
		assertEquals(c.getContactCompany(), cc);
	}
}
