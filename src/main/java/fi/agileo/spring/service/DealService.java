package fi.agileo.spring.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User;


@Component
public class DealService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
	@Transactional
	public void savedeal(User dealMakerUser, Deal deal, Contract contract, List<Contact> dealMakerContacts) {
		// Save deal
		this.em.persist(deal); 		
		
	}
	
	@Transactional
	public void addDealsToContract(Deal deal,
			List<Deal> dealsToBeAdded) {
	}
	
}
