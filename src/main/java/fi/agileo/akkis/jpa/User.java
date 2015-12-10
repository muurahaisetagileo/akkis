package fi.agileo.akkis.jpa;

import javax.persistence.Transient;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
@NamedQueries({
	@NamedQuery(name="findByUsername",
		query="SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name="login", 
		query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
	@NamedQuery(name="User.findAll", 
	query="SELECT u FROM User u"),
	@NamedQuery(name="User.search",
	query="SELECT u FROM User u WHERE u.firstNames LIKE :firstNames AND u.lastName LIKE :lastName" +
	" AND u.username LIKE :username AND u.role IN :roles"),
	@NamedQuery(name="User.rolesByName",
	query="SELECT u FROM User u WHERE u.role IN :roles"
			+ " ORDER BY u.lastName, u.firstNames")
	
	
})
public class User {
	
	// Database columns
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private long userId;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="FIRSTNAMES")
	private String firstNames;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	// Relationships
	@OneToMany(mappedBy="user")
	private List<Contract> contracts;
	
	@OneToMany(mappedBy="salesPerson")
	private List<ContactPerson> contactPersons;	

	
	// Public methods

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {	
		return password;
	}

	public void setPassword(String password) {		
		this.password = password;
	}
	
	@Transient
	public String getDecodedPassword() {
		String decodedPassword ="";
		try {
			byte[] bytesPW =  Base64.getDecoder().decode(this.password);
			decodedPassword = new String(bytesPW, "utf-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error :" + e.getMessage());
		}	
		return decodedPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Transient
	public String getWholeName() {
		return this.lastName + " " + this.firstNames;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public List<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	public void setContactPersons(List<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof User))
			return false;
		User u = (User) o;
		if (u.getUserId() == this.getUserId())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + 
				username + ", password=" + password + ", type=" + role + "]";
	}

}
