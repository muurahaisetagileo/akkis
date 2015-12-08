package fi.agileo.spring.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.ContactPerson;
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
			User technicianUser,
			ContactCompany contactCompany, 
			List<ContactPerson> contractMakerContactPersons) {
		contract.setContactCompany(contactCompany);
		contract.setContactPersons(contractMakerContactPersons);
		contract.setUser(contractMakerUser);
		contract.setTechnicianUser(technicianUser);
		contractMakerUser.getContracts().add(contract);
		contactCompany.getContracts().add(contract);
		em.persist(contract);
		em.merge(contactCompany);
		em.merge(contractMakerUser);
		for (ContactPerson c : contractMakerContactPersons)
			c.getContracts().add(contract);
		for (ContactPerson c : contract.getContactCompany().getContactPersons()) {
			if (c.getType() < 2)
				c.setType(2);
			em.merge(c);
		}
	}

	@Transactional
	public void addContactsToContract(Contract contract, 
			List<ContactPerson> contactPersonsToBeAdded) {
		List<ContactPerson> existingContactPersons = contract.getContactPersons();

		// Loop through list contactPersonssToBeAdded to 
		// check and remove existing contact persons
		for (int i = 0; i < contactPersonsToBeAdded.size(); i++) {
			ContactPerson c = contactPersonsToBeAdded.get(i);
			if (existingContactPersons.contains(c))
				contactPersonsToBeAdded.remove(c);
		}
		// Set customers to the contract
		// existingcontacts.addAll(contactsToBeAdded);
		contract.getContactPersons().addAll(contactPersonsToBeAdded);
		for (ContactPerson c : contactPersonsToBeAdded) {
			c.getContracts().add(contract);
			em.merge(c);
		}
		em.merge(contract);
	}

	@Transactional
	public Contract getContractPropertiesForView(Contract contract) {
		contract = em.find(Contract.class, contract.getContractId());
		// em.merge(contract);
		em.refresh(contract);
		contract.getContactPersons();
		contract.getContactCompany();
		contract.getUser();
		return contract;
	}

	/*
	 * A method that gets contract information. Also a way to get contacts of
	 * contract.
	 */

	public Contract getContactPersons(Contract contract) {
		contract.getContactPersons();
		return contract;
	}

	@Transactional
	public void modifyContractBasicInfo(Contract contract) {
		em.merge(contract);
	}
}
