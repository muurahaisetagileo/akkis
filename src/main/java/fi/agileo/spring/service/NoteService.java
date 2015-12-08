package fi.agileo.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Note;

public class NoteService {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
	@Transactional
	public int createNote(Note note) {
		// Tee tähän uuden nootin luominen
		em.persist(note);
		return 0;
	}
	
	@Transactional
	public void updateNote(Note note) {
		// Nootin päivitys
		em.merge(note);
	}
		
}
