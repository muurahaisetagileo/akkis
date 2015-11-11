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
public class Contact {
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
	
	@Column(name="PUBLICAVAILABILITY")
	private boolean publicAvailability;
	
	public long getContactId()
	{
		return this.contactId;
	}
	
	public void setContactId(long contactId)
	{
		this.contactId = contactId;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getPhone() 
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}

	public boolean isPublicAvailability() {
		return publicAvailability;
	}

	public void setPublicAvailability(boolean publicAvailability) {
		this.publicAvailability = publicAvailability;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + 
				", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", country=" + country + ", type=" + type + ", publicAvailability="
				+ publicAvailability + "]";
	}
}