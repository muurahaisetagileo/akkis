package fi.agileo.akkis.test.jpa;

import org.junit.*;
import java.util.List;
import java.util.ArrayList;

import fi.agileo.akkis.jpa.User;
import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;

import static org.junit.Assert.*;
	
public class UserTest {
	@Test
	public void testBasicUserCreation() {
		User u = new User();
		u.setUserId(10);
		u.setFirstNames("Kalle Matti");
		u.setLastName("Heikkinen");
		u.setPassword("sala");
		u.setRole("ADMIN");
		
		assertEquals(u.getUserId(), 10);
		assertEquals(u.getFirstNames(), "Kalle Matti");
		assertEquals(u.getLastName(), "Heikkinen");
		assertEquals(u.getPassword(), "sala");
		assertEquals(u.getRole(), "ADMIN");
	}
	
	@Test
	public void testRelationShips() {
		User u = new User();
		List<Contact> contacts = new ArrayList<Contact>();
		List<Contract> contracts = new ArrayList<Contract>();
		List<Deal> deals = new ArrayList<Deal>();
		
		u.setContacts(contacts);
		u.setContracts(contracts);
		u.setDeals(deals);
		
		assertEquals(u.getContacts(), contacts);
		assertEquals(u.getContracts(), contracts);
		assertEquals(u.getDeals(), deals);
	}
}
