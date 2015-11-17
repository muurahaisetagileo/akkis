package fi.agileo.akkis.test.jpa;

import org.junit.Test;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;
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
		List<Contract> contact2Contracts = new ArrayList<Contract>();
		List<Contact> contract2contacts = new ArrayList<Contact>();
		List<Deal> contract2deals = new ArrayList<Deal>();
		User contact2SalesUser = new User();
		
		System.out.println("HERE2");
		
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
		contract2.setDeals(contract2deals);
		contract2.setContacts(contract2contacts);

		contracts.add(contract1);
		contracts.add(contract2);
		
		contact2Contracts.add(contract2);
		
		/* 2 contacts to list of company contacts */
		List<Contact> contacts = new ArrayList<Contact>();
		
		Contact contact1 = new Contact();
		contact1.setAddress("osoite");
		contact1.setName("Heikki Mattila");
		contact1.setPhone("014343211");
		
		Contact contact2 = new Contact();
		contact2.setEmail("s-posti");
		contact2.setContactId(17);
		contact2.setPublicAvailability(true);
		contact2.setContactCompany(cc);
		contact2.setContracts(contact2Contracts);
		contact2.setSalesPerson(contact2SalesUser);
		
		Contact contact3 = new Contact();
		contact3.setCountry("Suomi");
		

		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
		
		cc.setContracts(contracts);
		cc.setCompanyContacts(contacts);
		
		/* asserts */
		// Basic collection members
		
		System.out.println("HERE3");
		assertEquals(cc.getContracts(), contracts);
		assertEquals(cc.getCompanyContacts(), contacts);
		
		/*
		 *  Collection member contacts properties
		 */
		
		// Members exists in list
		System.out.println("HERE4");
		assertEquals(cc.getCompanyContacts().contains(contact1), true);
		assertEquals(cc.getCompanyContacts().contains(contact2), true);
		assertEquals(cc.getCompanyContacts().contains(contact3), true);
		assertEquals(cc.getCompanyContacts().size(), 3);

		// Member basic properties are the same
		System.out.println("HERE5");
		List<Contact> gotContacts = cc.getCompanyContacts();
		for(Contact contact : gotContacts) {
			if (contact == contact1) {
				assertEquals("osoite", contact.getAddress());
				assertEquals("Heikki Mattila", contact.getName());
				assertEquals("014343211", contact.getPhone());
			}
			else if (contact == contact2) {
				System.out.println("contact == contact2 begin");
				assertEquals("s-posti", contact.getEmail());
				assertEquals(17, contact.getContactId());
				assertEquals(true, contact.isPublicAvailability());
				assertEquals(cc, contact.getContactCompany());
				System.out.println("contact == contact2 before collections");
				assertEquals(contact2Contracts, contact.getContracts());
				System.out.println("contact == contact2, before user");
				assertEquals(contact2SalesUser, contact.getSalesPerson());
				System.out.println("contact == contact2 end");
			}
			else /* contact == contact3 */ {
				assertEquals("Suomi", contact.getCountry());
			}
		}
		
		/* Contracts */
		System.out.println("HERE6");
		assertEquals(cc.getContracts().contains(contract1), true);
		assertEquals(cc.getContracts().contains(contract2), true);
		assertEquals(cc.getContracts().size(), 2);
		
		System.out.println("HERE7");
		
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
				assertEquals(contract2deals, contract.getDeals());
				assertEquals(contract2contacts, contract.getContacts());
			}
		}
	}
}
