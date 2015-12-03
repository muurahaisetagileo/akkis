package fi.agileo.primefaces.beans.user;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

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
	
	private String filterFirstname;
	private String filterLastname;
	private String filterUsername;
	private String[] filterUserRoles;
	
	
	public UserSearch() {
		initializeRoles();
	}
	
	public void initializeRoles() {
		String defaultRoles[] = {"USER", "SALESPERSON", "SECRETARY", "BILLER", "CUSTOMERSERVICE", "TECHNICIAN", "BOSS", "ADMIN"};
		this.filterUserRoles = defaultRoles;		
	}

	public SelectItem[] getUserRoles() {
		SelectItem[] userRoles = new SelectItem[8];
		userRoles[0] = new SelectItem("USER", "User");	
		userRoles[1] = new SelectItem("SALESPERSON", "Salesperson");
		userRoles[2] = new SelectItem("SECRETARY", "Secretary");
		userRoles[3] = new SelectItem("BILLER", "Invoicing");
		userRoles[4] = new SelectItem("CUSTOMERSERVICE", "Customer service");
		userRoles[5] = new SelectItem("TECHNICIAN", "Technician");
		userRoles[6] = new SelectItem("BOSS", "Boss");
		userRoles[7] = new SelectItem("ADMIN", "Admin");
		return userRoles;
	}
	
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
	
	public String getFilterFirstname() {
		return filterFirstname;
	}

	public void setFilterFirstname(String filterFirstname) {
		this.filterFirstname = filterFirstname;
	}

	public String getFilterLastname() {
		return filterLastname;
	}

	public void setFilterLastname(String filterLastname) {
		this.filterLastname = filterLastname;
	}

	public String getFilterUsername() {
		return filterUsername;
	}

	public void setFilterUsername(String filterUsername) {
		this.filterUsername = filterUsername;
	}
	
	public String[] getFilterUserRoles() {
		return filterUserRoles;
	}

	public void setFilterUserRoles(String[] filterUserRoles) {
		this.filterUserRoles = filterUserRoles;
	}

	public void allUsers() {
		this.searchedUsers = userService.findAllUsers();
		for(User u: searchedUsers)
			System.out.println("User: " + u);		
	}
	
	public void filteredUsers() {
		List<String> searchedUserList = new ArrayList<String>();
		for(String userRole: filterUserRoles) {
			searchedUserList.add(userRole);
		}
		
		this.searchedUsers = userService.findFilteredUsers(
				searchedUserList ,
				this.getFilterFirstname(),
				this.getFilterLastname(),
				this.getFilterUsername()
				);	
		
		System.out.println("Searhed users: ");
		for(User u: searchedUsers)
			System.out.println(u);			
	}

}
