package fi.agileo.spring.service.console;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.User;

import fi.agileo.spring.service.ContactService;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.primefaces.beans.user.RegisterUser;

@Component
public class ContactConsole {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = 
			new ClassPathXmlApplicationContext("consoleApplicationContext.xml");;
		
		// Create test contact
		
		Contact c = new Contact();
		
		c.setName("Pekka Vieru");
		c.setAddress("Tulitie 4");
		c.setPhone("0403242345");
		c.setEmail("pevi@mail.com");
		c.setCountry("Finland");
		
		User u = new User();
		
		u.setFirstNames("Andrei");
		u.setLastName("Kiuru");
		u.setUsername("Kanki");
		u.setPassword("abcd");
		
		ContactCompany cc = new ContactCompany();
		cc.setName("Rekso Oy");
		
		Contract cr = new Contract();
		
		// Create test UserService
		
		ContactService cs = new ContactService();
		
		int result = cs.createContact(u, c);
		
		if (result == 1) {
			System.out.println("Registration successful");
		} else {
			System.out.println("Registration failed");
		}
	
	}

}
