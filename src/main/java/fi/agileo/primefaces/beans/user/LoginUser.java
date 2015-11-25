package fi.agileo.primefaces.beans.user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	private String mainPage(String role) {
		System.out.println("PÖÖÖÖÖÖÖÖ1");
		if (role.equals("ADMIN")) {	
			System.out.println("PÖÖÖÖÖÖÖÖ2");
			return "admin_index";
		}
		else if (role.equals("USER")) {
			return "user_index";
		}
		else if (role.equals("SALESPERSON")) {
			return "salesperson_index";
		}
		else if (role.equals("SECRETARY")) {
			return "secretary_index";
		}
		else if (role.equals("BILLER")) {
			return "biller_index";
		}
		else if (role.equals("CUSTOMERSERVICE")) {
			return "customerService_index";
		}
		else if (role.equals("TECHNICIAN")) {
			return "technician_index";
		}
		else if (role.equals("BOSS")) {
			return "boss_index";
		}		
		return "";
	}
	
	public String toMainPage() {
		if (user == null) {
			System.out.println("user is null when returning to main page");
			return "";
		}
		System.out.println("toMainPage, user.getRole()" + user.getRole());
		String page = "/" + mainPage(user.getRole());
		System.out.println("page " + page);
		return page;
	}

	public String login() {
		System.out.println("managed bean 1ogin");
		User foundUser = this.userService.login(user);
		if (foundUser != null) {
			System.out.println("Logged user: " + foundUser);
			this.user = foundUser;
			return mainPage(this.user.getRole());
		}
		System.out.println("login failed -> message");
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Login failed!"));
		System.out.println("Login failed return");
		return "";
	}
	
	public String logout() {
		System.out.println("logout");
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
			System.out.println("session invalidated");
		}
		System.out.println("to signout");
		return "/signed_out";
	}

}
