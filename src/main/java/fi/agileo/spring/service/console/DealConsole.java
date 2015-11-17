package fi.agileo.spring.service.console;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User;

import fi.agileo.spring.service.ContactService;
import fi.agileo.primefaces.beans.user.LoginUser;
import fi.agileo.primefaces.beans.user.RegisterUser;

@Component
public class DealConsole {

	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("fi/agileo/spring/service/console/consoleApplicationContext.xml");
		
		
		// Create test deal
		
		Deal d = new Deal();
		
		d.setPrice(30.0);
		d.setPeriodType(1);
		d.setPeriodLength(1);
		
		Contact c = new Contact();
		
		System.out.println("Contact print results: " + c); 
	
	}

}
