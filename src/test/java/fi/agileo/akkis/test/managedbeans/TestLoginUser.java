package fi.agileo.akkis.test.managedbeans;


import org.junit.Test;

import fi.agileo.akkis.jpa.User;
import fi.agileo.primefaces.beans.user.LoginUserView;
import fi.agileo.spring.service.UserService;

import static org.junit.Assert.*;

public class TestLoginUser {
	
	@Test
	public void testCreation() {
		LoginUserView lu = new LoginUserView();
		UserService us = new UserService();
		User u = new User();
		lu.setUserService(us);
		lu.setUser(u);
		
		assertEquals(lu.getUser(), u);
		assertEquals(lu.getUserService(), us);
	}
	
}
