package fi.agileo.spring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;

@Component
public class ContractService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public void createContract(
			Contract contract,
			User contractMakerUser, 
			ContactCompany contactCompany,
			List<Contact> contractMakerContacts) {
	}
	
	@Transactional
	public void addContactsToContract(Contract contract,
			List<Contact> contactsToBeAdded) {
		
	}

	public ContractInformation getContractPropertiesForView(Contract contract) {
		return null;
	}
}
