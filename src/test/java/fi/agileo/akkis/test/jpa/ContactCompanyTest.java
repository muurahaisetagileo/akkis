package fi.agileo.akkis.test.jpa;

import org.junit.Test;

import fi.agileo.akkis.jpa.ContactPerson;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ContactCompanyTest {
	@Test
	public void testBasicContactCompanyCreation() {
		ContactCompany cc = new ContactCompany();
		cc.setId(12);
		cc.setInternetAddress("www.yritys.net");
		cc.setName("Yritys");
		cc.setAddress("Osoite x");
		cc.setCompanyIdentifier("Y-220920");
		
		assertEquals(cc.getId(), 12);
		assertEquals(cc.getInternetAddress(), "www.yritys.net");
		assertEquals(cc.getName(), "Yritys");
		assertEquals(cc.getAddress(), "Osoite x");
		assertEquals(cc.getCompanyIdentifier(), "Y-220920");
	}
	
	@Test
	public void testContactCompanyRelationShips() {
		
		System.out.println("HERE1");
		
		ContactCompany cc = new ContactCompany();
		List<Contract> contracts = new ArrayList<Contract>();
		List<Contract> contactPerson2Contracts = new ArrayList<Contract>();
		List<ContactPerson> contract2contactPersons = new ArrayList<ContactPerson>();
		User contact2SalesUser = new User();
		
		Date today = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(GregorianCalendar.MONTH, 12);
		cal.add(GregorianCalendar.DAY_OF_MONTH, 7);
		Date endDate = cal.getTime();
		cal.setTime(today);
		cal.add(GregorianCalendar.DAY_OF_MONTH, 2);
		Date signDate2 = cal.getTime();
		
		Contract contract1 = new Contract();
		contract1.setContractId(19);
		contract1.setcontractStatus("done");
		contract1.setSigndate(today);
		contract1.setEnddate(endDate);
		contract1.setContactCompany(cc);
		
		Contract contract2 = new Contract();
		contract2.setSigndate(signDate2);
		contract2.setContactPersons(contract2contactPersons);

		contracts.add(contract1);
		contracts.add(contract2);
		
		contactPerson2Contracts.add(contract2);
		
		/* 2 contacts to list of company contacts */
		List<ContactPerson> contactPersons = new ArrayList<ContactPerson>();
		
		ContactPerson contactPerson1 = new ContactPerson();
		contactPerson1.setAddress("osoite");
		contactPerson1.setName("Heikki Mattila");
		contactPerson1.setPhone("014343211");
		
		ContactPerson contactPerson2 = new ContactPerson();
		contactPerson2.setEmail("s-posti");
		contactPerson2.setContactPersonId(17);
		contactPerson2.setPublicAvailability(true);
		contactPerson2.setContactCompany(cc);
		contactPerson2.setContracts(contactPerson2Contracts);
		contactPerson2.setSalesPerson(contact2SalesUser);
		
		ContactPerson contactPerson3 = new ContactPerson();
		contactPerson3.setCountry("Suomi");
		

		contactPersons.add(contactPerson1);
		contactPersons.add(contactPerson2);
		contactPersons.add(contactPerson3);
		
		cc.setContracts(contracts);
		cc.setContactPersons(contactPersons);
		
		/* asserts */
		// Basic collection members
		
		assertEquals(cc.getContracts(), contracts);
		assertEquals(cc.getContactPersons(), contactPersons);
		
		/*
		 *  Collection member contacts properties
		 */
		
		// Members exists in list
		System.out.println("HERE4");
		assertEquals(cc.getContactPersons().contains(contactPerson1), true);
		assertEquals(cc.getContactPersons().contains(contactPerson2), true);
		assertEquals(cc.getContactPersons().contains(contactPerson3), true);
		assertEquals(cc.getContactPersons().size(), 3);

		// Member basic properties are the same
		System.out.println("HERE5");
		List<ContactPerson> gotContactPersons = cc.getContactPersons();
		for(ContactPerson contactPerson : gotContactPersons) {
			if (contactPerson == contactPerson1) {
				assertEquals("osoite", contactPerson.getAddress());
				assertEquals("Heikki Mattila", contactPerson.getName());
				assertEquals("014343211", contactPerson.getPhone());
			}
			else if (contactPerson == contactPerson2) {
				assertEquals("s-posti", contactPerson.getEmail());
				assertEquals(17, contactPerson.getContactPersonId());
				assertEquals(true, contactPerson.isPublicAvailability());
				assertEquals(cc, contactPerson.getContactCompany());
				assertEquals(contactPerson2Contracts, contactPerson.getContracts());
				assertEquals(contact2SalesUser, contactPerson.getSalesPerson());
			}
			else /* contactPerson == contactPerson3 */ {
				assertEquals("Suomi", contactPerson.getCountry());
			}
		}
		
		/* Contracts */
		assertEquals(cc.getContracts().contains(contract1), true);
		assertEquals(cc.getContracts().contains(contract2), true);
		assertEquals(cc.getContracts().size(), 2);
		
		for(Contract contract : cc.getContracts()) {
			if (contract1 == contract) {
				assertEquals(contract.getContractId(), 19);
				assertEquals(contract.getEnddate(), endDate);
				assertEquals(contract.getSigndate(), today);
				assertEquals(contract.getContractstatus(), "done");
				assertEquals(contract.getContactCompany(), cc);
			}
			else /* contract == contract2 */ {
				assertEquals(contract.getSigndate(), signDate2);
				assertEquals(contract2contactPersons, contract.getContactPersons());
			}
		}
	}
}
