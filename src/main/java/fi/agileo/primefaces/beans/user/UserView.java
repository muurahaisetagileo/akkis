package fi.agileo.primefaces.beans.user;

import java.util.Base64;

import javax.annotation.PostConstruct;
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
	private boolean modifyPassword = false;
	private String backPage = "";
	private User unmodifiedUser; 
	
	@PostConstruct
    private void init() {
		unmodifiedUser = new User();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUnmodifiedUser() {
		return unmodifiedUser;
	}

	public void setUnmodifiedUser(User unmodifiedUser) {
		this.unmodifiedUser = unmodifiedUser;
	}

	public String toBackPage() {;
		this.modifyBasics = false;
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
	
	public boolean isModifyPassword() {
		return modifyPassword;
	}

	public void setModifyPassword(boolean modifyPassword) {
		this.modifyPassword = modifyPassword;
	}

	public void toModifyBasics() {
		unmodifiedUser.setFirstNames(user.getFirstNames());
		unmodifiedUser.setLastName(user.getLastName());
		unmodifiedUser.setRole(user.getRole());
		unmodifiedUser.setPassword(user.getDecodedPassword());
	}	
	
	public void toModifyPassword() {
		// Ei tehdä mitään muutokset tehdään xhtml -dokumentissa akkis_user.xhtml
	}
	
	public String saveModifiedUser() {
		if (this.modifyPassword) {	
			String decoderPW = this.getUser().getPassword();
			this.getUser().setPassword(encryptPassword(decoderPW));		
		}
		userService.updateUser(this.getUser());
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Your changes have been saved successfully."));		
		
		this.modifyPassword = false;
		return null;
	}	
	
	public void cancelChanges() {		
		user.setFirstNames(unmodifiedUser.getFirstNames());
		user.setLastName(unmodifiedUser.getLastName());
		user.setRole(unmodifiedUser.getRole());
		if(modifyPassword) {
			user.setPassword(unmodifiedUser.getDecodedPassword());
		}
		user.setPassword(encryptPassword(unmodifiedUser.getPassword()));
		modifyBasics = false;
		modifyPassword = false;
	}
	
	public String encryptPassword(String pw) {
		byte[] passwordBytes = pw.getBytes();
		return Base64.getEncoder().encodeToString(passwordBytes);			
	}	

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String toUserPage() {
		modifyBasics = false;
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No user selected!", "At first select user."));
			return "/user/user_search";
		}
		return "/user/akkis_user";
	}	
	
	
}
