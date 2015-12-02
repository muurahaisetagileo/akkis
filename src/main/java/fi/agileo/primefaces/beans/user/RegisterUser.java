package fi.agileo.primefaces.beans.user;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class RegisterUser {

	@ManagedProperty("#{userService}")
	private UserService userService;

	private User user = new User();
	private List<String> roles; 
	
    @PostConstruct
    public void init() {
        roles = new ArrayList<String>();
        roles.add("USER");
        roles.add("SALESPERSON");
        roles.add("SECRETARY");
        roles.add("BILLER");
        roles.add("CUSTOMERSERVICE");
        roles.add("TECHNICIAN");
        roles.add("BOSS");
        roles.add("ADMIN");
    }	

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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String register() {		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			// Message: Username must be at least 4 characters
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Passwords don't match"));	
		} else if(user.getUsername().length()<4) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Username length must be at least 4 characters"));	
		}
		
		else {
			// Calling Business Service
			int i = userService.register(user);
			String msg = " could not be registered";
			
			if (i == 1) {
				msg = " is registered successfully";
			} else if (i == -1){
				msg = " already exists";
			}
			
			// Add message
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("The user " + 
						this.user.getUsername()+
						msg));
		}
		return "";
	}
}
