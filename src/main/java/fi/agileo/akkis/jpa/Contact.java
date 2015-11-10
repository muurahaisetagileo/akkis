package fi.agileo.akkis.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact2 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONTACT_ID")
	private long contactId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="TYPE")
	private int type;
	
	@Column(name="CONTRACT")
	private List<Contract> contracts;
	
	public long getContactId(){return 0;}
	public void setId(long contactId){}
	
	public String getName(){return null;}
	public String setName(String name){}
	
	public String getAddress(){return null;}
	public void setAddress(String address){}
	
	public String getPhone(return null;){}
	public void setPhone(){}
	
	public String getEmail(return null;){}
	public void setEmail(String email){}
	
	public String getCountry(return null;){}
	public void setEmail(String country){}
	
	public int getType(return 0;){}
	public void setEmail(int type){}
	
	public List<Contract> getContracts(return null;){}
	public void setContracts(List<Contract> contracts){}
	
	@Override
	public String toString() {}
}