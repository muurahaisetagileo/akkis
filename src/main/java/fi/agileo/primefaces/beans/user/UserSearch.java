package fi.agileo.primefaces.beans.user;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class UserSearch {
	
	@ManagedProperty("#{userService}")
	private UserService userService;	
	
	private List<User> searchedUsers = new ArrayList<User>();
	private List<User> selectedUsers = new ArrayList<User>();
	
	private String searchFirstname;
	private String searchLastname;
	private String searchUsername;
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public List<User> getSearchedUsers() {
		return searchedUsers;
	}
	
	public void setSearchedUsers(List<User> searchedUsers) {
		this.searchedUsers = searchedUsers;
	}
	
	public List<User> getSelectedUsers() {
		return selectedUsers;
	}
	
	public void setSelectedUsers(List<User> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	
	public String getSearchFirstname() {
		return searchFirstname;
	}
	
	public void setSearchFirstname(String searchFirstname) {
		this.searchFirstname = searchFirstname;
	}
	
	public String getSearchLastname() {
		return searchLastname;
	}
	
	public void setSearchLastname(String searchLastname) {
		this.searchLastname = searchLastname;
	}
	
	public String getSearchUsername() {
		return searchUsername;
	}
	
	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}
	
	public void allUsers() {
		this.searchedUsers = userService.findAllUsers();
		for(User u: searchedUsers)
			System.out.println("User: " + u);		
	}
	
}
