package fi.agileo.spring.service.console;

import fi.agileo.akkis.jpa.User;

import fi.agileo.spring.service.UserService;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.primefaces.beans.user.RegisterUser;

public class UserConsole {

	public static void main(String[] args) {
		
		// Create test user
		
		User u = new User();
		
		u.setFirstNames("Andrei");
		u.setLastName("Kiuru");
		
		// Create test UserService
		
		UserService us = new UserService();
		
		int result = us.register(u);
		
		if (result == 1) {
			System.out.println("Registration successful");
		} else {
			System.out.println("Registration failed");
		}
	
	}

}
