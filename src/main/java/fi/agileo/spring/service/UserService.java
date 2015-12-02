package fi.agileo.spring.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.agileo.akkis.jpa.Contact;
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
		
		List<User> oldUsersBySameUsername = 
				(List<User>)em.createNamedQuery("findByUsername").
				setParameter("username", user.getUsername()).
				getResultList();
		if (oldUsersBySameUsername.size() > 0)
			return -1;
		
		// Encrypt password
		user.setPassword(encryptPassword(user.getPassword()));
		// Save user
		this.em.persist(user);
		
		List<User> regUsers = (List<User>)em.createNamedQuery("findByUsername").
				setParameter("username", user.getUsername()).
				getResultList();
		if (regUsers.size() > 0)
			return 1;
		return 0;
	}
	
	public String encryptPassword(String pw) {
		byte[] passwordBytes = pw.getBytes();
		return Base64.getEncoder().encodeToString(passwordBytes);			
	}
	
	public User login(User user) {
		System.out.println("login");
		List<User> loginUsers = (List<User>)em.createNamedQuery("login").
				setParameter("username", user.getUsername()).
				setParameter("password", encryptPassword(user.getPassword())).getResultList();
		System.out.println("user count " + loginUsers.size());
		if (loginUsers.size() == 1)
			return loginUsers.get(0);
		return null;
	}
	
	public List<User> findAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		allUsers = (List<User>)em.createNamedQuery("User.findAll").getResultList();
		
		return allUsers;
	}

	public List<User> getUserByRolesInNameOrder(List<String> roles) {
		return (List<User>)em.createNamedQuery("User.rolesByName").
					setParameter("roles", roles).
					getResultList();
	}

}
