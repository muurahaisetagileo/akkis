package fi.agileo.akkis.test.service;

import org.junit.*;

import java.util.List;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

import java.util.ArrayList;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContactCompanyService;
import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.Contract;

public class ContactCompanyServiceTest {
	@Test
	public void testCreation() {
		ContactCompanyService ccs = new ContactCompanyService();
		EntityManager em = mock(EntityManager.class);
		ccs.setEm(em);
		assertEquals(ccs.getEm(), em);
	}
}
