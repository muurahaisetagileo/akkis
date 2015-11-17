package fi.agileo.spring.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.User;

@Component
public class UserService {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public int register(User user) {
		
		// Check that the username doesn't already exist in database
		
		List<User> checkUsers = (List<User>)em.createNamedQuery("register").
				setParameter("username", user.getUsername()).getResultList();
		if (checkUsers.size() == 1) {
			return -1;
		}
		
		// No duplicate found - save the user
		
		this.em.persist(user);
		
		// Check that the user was successfully saved
		
		List<User> regUsers = (List<User>)em.createNamedQuery("register").
				setParameter("username", user.getUsername()).getResultList();
		if (regUsers.size() == 1)
			return 1;
		return 0;
	}
	
	public int login(User user) {
		System.out.println("login");
		List<User> loginUsers = (List<User>)em.createNamedQuery("login").
				setParameter("username", user.getUsername()).
				setParameter("password", user.getPassword()).getResultList();
		System.out.println("user count " + loginUsers.size());
		if (loginUsers.size() == 1)
			return loginUsers.get(0).getType();
		return 0;
	}

}
