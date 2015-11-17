package fi.agileo.spring.service.console;

import org.springframework.stereotype.Component;

import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.UserService;

@Component
public class UserConsole {

	public static void main(String[] args) {
		
		int result = 0;
		
		// Create test user
		
		User u = new User();
		
		u.setFirstNames("Andrei");
		u.setLastName("Kiuru");
		
		// Create test UserService
		
		UserService us = new UserService();
		
		result = us.register(u);
		
		if (result == 1) {
			System.out.println("Registration successful");
		} else {
			System.out.println("Registration failed");
		}
	
	}

}
