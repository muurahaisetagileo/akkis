package fi.agileo.primefaces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class RegisterUser {

	@ManagedProperty("#{userService}")
	private UserService userService;

	private User user = new User();

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register() {
		// Calling Business Service
		int i = userService.register(user);
		String msg = " could not be registered";
		
		if (i == 1) {
			msg = " is registered successfully";
		}
		
		// Add message
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("The user " + 
					this.user.getUsername()+
					msg));
		return "";
	}
}
