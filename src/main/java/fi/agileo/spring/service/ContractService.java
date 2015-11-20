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
			contract.setContactCompany(contactCompany);
			contract.setContacts(contractMakerContacts);
			contract.setUser(contractMakerUser);
			contractMakerUser.getContracts().add(contract);
			contactCompany.getContracts().add(contract);
			em.persist(contract);
			em.merge(contactCompany);
			em.merge(contractMakerUser);
			for(Contact c : contractMakerContacts) {
				c.getContracts().add(contract);
				em.merge(c);
			}
	}
	

	@Transactional
	public void addContactsToContract(Contract contract,
			List<Contact> contactsToBeAdded) {
			List<Contact> existingcontacts=contract.getContacts();
			
			// Loop through list contactsToBeAdded to check and remove existing contacts
			for (int i = 0; i < contactsToBeAdded.size(); i++) {
				Contact c = contactsToBeAdded.get(i);
				if (existingcontacts.contains(c))
					contactsToBeAdded.remove(c);
			}
			// Set customers to the contract
			// existingcontacts.addAll(contactsToBeAdded);
			contract.getContacts().addAll(contactsToBeAdded);
			em.merge(contract);
			for(Contact c : contactsToBeAdded)
				em.persist(c);
	}
	

	public Contract getContractPropertiesForView(Contract contract) {
		em.refresh(contract);
		contract.getContacts();
		contract.getContactCompany();
		contract.getUser();
		return contract;
	}
	
	/* A method that gets contract information. 
	Also a way to get contacts of contract.*/
	
	public Contract getContacts(Contract contract) {
		contract.getContacts();
	    return contract;		
	}
}
