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
public class UserView {
	
	@ManagedProperty("#{userService}")
	private UserService userService;	

	private User user;
	private boolean modifyBasics = false;
	private String backPage = "";
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getBackPage() {
		return backPage;
	}

	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}

	public boolean isModifyBasics() {
		return modifyBasics;
	}
	
	public void setModifyBasics(boolean modifyBasics) {
		this.modifyBasics = modifyBasics;
	}
	
	public String toModifyBasics() {
		return null;
	}	
	
	public String saveModifiedUser() {
		userService.updateUser(this.getUser());
		return null;
	}	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String toUserPage() {
		System.out.println("KÄYTTÄJÄN SIVULLE");
		modifyBasics = false;
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No user selected!"));
			return "/user/user_search";
		}
		return "/user/akkis_user";
	}	
	
	
}
