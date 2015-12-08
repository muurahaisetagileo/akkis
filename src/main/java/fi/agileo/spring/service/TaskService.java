package fi.agileo.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Task;

public class TaskService {
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
	@Transactional
	public int createTask(Task task) {
		// Tee tähän uuden taskin luominen
		em.persist(task);
		return 0;
	}	
	
	@Transactional
	public void updateTask(Task task) {
		// taskin päivitys
		em.merge(task);
	}	

}
