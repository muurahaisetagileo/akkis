package fi.agileo.akkis.test.jpa;

import org.junit.*;

import java.util.List;
import java.util.ArrayList;
import fi.agileo.akkis.jpa.User;
import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;

import static org.junit.Assert.*;

public class DealTest {
	public static final double EPSILON = 1e-15;

	@Test
	public void testBasicDealCreation() {
		Deal d = new Deal();
		d.setDealId(10);
		d.setPeriodType(10);
		d.setPrice(30.0);

		assertEquals(d.getDealId(), 10);
		assertEquals(d.getPeriodType(), 10);
		assertEquals(d.getPrice(), 30.0, EPSILON);
		
	}
		
		@Test
		public void testRelationShips() {
			Deal d = new Deal();
			List<Contact> contacts = new ArrayList<Contact>();
			
			d.setContacts(contacts);
		
			assertEquals(d.getContacts(), contacts);
		
	}
}
