package fi.agileo.primefaces.beans.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class LoginUser {
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

	public String login() {
		System.out.println("managed bean 1ogin");
		int type = this.userService.login(user);
		System.out.println("managed bean, type " + type);
		if (type == 1)
			return "admin_index";
		System.out.println("login failed -> message");
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Login failed!"));
		System.out.println("Login failed return");
		return "";
	}

}
