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
public class LoginUserView {
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
		if (role.equals("ADMIN")) {	
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
		System.out.println("user " + user);
		User foundUser = this.userService.login(user);
		System.out.println("foundUser " + foundUser);
		if (foundUser != null) {
			System.out.println("Logged user: " + foundUser);
			this.user = foundUser;
			return mainPage(this.user.getRole());
		}
		System.out.println("login failed -> message");
		this.user.setPassword("");
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
	
	public String getMenuInclude() {
		String ur = user.getRole();
		if (ur.equals("ADMIN")) {	
			return "/admin_navi.xhtml";
		}
		else if (ur.equals("USER")) {
			return "/user_navi.xhtml";
		}
		else if (ur.equals("SALESPERSON")) {
			return "/salesperson_navi.xhtml";
		}
		else if (ur.equals("SECRETARY")) {
			return "/secretary_navi.xhtml";
		}
		else if (ur.equals("BILLER")) {
			return "/biller_navi.xhtml";
		}
		else if (ur.equals("CUSTOMERSERVICE")) {
			return "/customerService_navi.xhtml";
		}
		else if (ur.equals("TECHNICIAN")) {
			return "/technician_navi.xhtml";
		}
		else if (ur.equals("BOSS")) {
			return "/boss_navi.xhtml";
		}			
		return "";
	}

}
