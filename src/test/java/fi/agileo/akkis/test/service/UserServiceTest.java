package fi.agileo.akkis.test.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;



import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.UserService;

public class UserServiceTest {
	@Test
	public void testCreation() {
		
	}
	
	@Ignore
	@Test
	public void testLoginSuccess() {
		EntityManager em = mock(EntityManager.class);
		
		List<User> users = new ArrayList<User>();
		User u = new User();
		u.setUsername("admin");
		u.setPassword("admin");
		u.setRole("ADMIN");
		users.add(u);
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        when(mockedQuery.setParameter("username","admin")).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("password","admin")).thenReturn(mockedQuery3);
        when(mockedQuery3.getResultList()).thenReturn(users);
        when(em.createNamedQuery("login")).thenReturn(mockedQuery);

        UserService us = new UserService();
        us.setEm(em);
        
        User ucall = new User();
        ucall.setUsername("admin");
        ucall.setPassword("admin");
        assertEquals(us.login(ucall).getRole(), "ADMIN");
     
	}
	
	@Ignore
	@Test
	public void testLoginFail_error_password() {
		EntityManager em = mock(EntityManager.class);
		List<User> users = new ArrayList<User>();
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        when(mockedQuery.setParameter("username","admin")).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("password","a")).thenReturn(mockedQuery3);
        when(mockedQuery3.getResultList()).thenReturn(users);
        when(em.createNamedQuery("login")).thenReturn(mockedQuery);

        UserService us = new UserService();
        us.setEm(em);
        
        User ucall = new User();
        ucall.setUsername("admin");
        ucall.setPassword("a");
        assertEquals(us.login(ucall), null);		
	}
	
	@Ignore
	@Test
	public void testLoginFail_error_username() {
		EntityManager em = mock(EntityManager.class);
		List<User> users = new ArrayList<User>();
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        when(mockedQuery.setParameter("username","a")).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("password","admin")).thenReturn(mockedQuery3);
        when(mockedQuery3.getResultList()).thenReturn(users);
        when(em.createNamedQuery("login")).thenReturn(mockedQuery);

        UserService us = new UserService();
        us.setEm(em);
        
        User ucall = new User();
        ucall.setUsername("a");
        ucall.setPassword("admin");
        assertEquals(us.login(ucall), null);		
	}

	@Ignore
	@Test
	public void testLoginFail_duplicate_identical_user_and_password() {
		EntityManager em = mock(EntityManager.class);
		List<User> users = new ArrayList<User>();

		User u = new User();
		u.setUsername("u");
		u.setPassword("u");
		u.setRole("ADMIN");
		users.add(u);
		
		User u2 = new User();
		u.setUsername("u");
		u.setPassword("u");
		u.setRole("ADMIN");
		users.add(u2);
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        Query mockedQuery3 = mock(Query.class);
        when(mockedQuery.setParameter("username","u")).thenReturn(mockedQuery2);
        when(mockedQuery2.setParameter("password","u")).thenReturn(mockedQuery3);
        when(mockedQuery3.getResultList()).thenReturn(users);
        when(em.createNamedQuery("login")).thenReturn(mockedQuery);

        UserService us = new UserService();
        us.setEm(em);
        
        User ucall = new User();
        ucall.setUsername("u");
        ucall.setPassword("u");
        assertEquals(us.login(ucall), null);		
	}
	
	/*
	@Test
	public void testRegisterSuccess() {
		
		EntityManager em = mock(EntityManager.class);
		
		List<User> users = new ArrayList<User>();
		User u = new User();
		u.setUsername("Kalle");
		u.setType(1);
		users.add(u);
		
        Query mockedQuery = mock(Query.class);
        Query mockedQuery2 = mock(Query.class);
        when(mockedQuery.setParameter("username","Kalle")).thenReturn(mockedQuery2);
        when(mockedQuery2.getResultList()).thenReturn(users);
        when(em.createNamedQuery("findByUsername")).thenReturn(mockedQuery);
        when(em).persist(User.class).thenReturn(mockedQuery);

        UserService us = new UserService();
        us.setEm(em);
        
        User ucall = new User();
        ucall.setUsername("Pelle");
        assertEquals(us.register(ucall), 1);
	}
	*/
}
