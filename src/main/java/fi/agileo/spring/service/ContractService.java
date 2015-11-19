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
			//List<Contract> ContractCompany)
	}
	
	@Transactional
	public void addContactsToContract(Contract contract,
			List<Contact> contactsToBeAdded) {
			List<Contact> existingcontacts=contract.getContacts();
			
			// Loop through list contactsToBeAdded
			for (int i = 0; i < contactsToBeAdded.size(); i++) {
				Contact c = contactsToBeAdded.get(i);
				if (existingcontacts.contains(c))
					contactsToBeAdded.remove(c);
			}
			
			
		// Prioriteetti 6 alin taski. Käydä läpi contacts to be added
	}
	

	public ContractInformation getContractPropertiesForView(Contract contract) {
		return null;
	}
	
	/* Create a method that gets contract information. 
	Also a way to get contacts of contract.*/
	
	public Contract getContacts(Contract contract) {
		contract.getContacts();
	    return contract;		
	}
}
