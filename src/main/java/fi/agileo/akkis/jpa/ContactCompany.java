package fi.agileo.akkis.jpa;

import java.util.List;

public class ContactCompany {
	private long id;
	private String name;
	private String address;
	private String internetAddress;
	private String companyIdentifier; // Y-Tunnus
	
	private List<Contact> companyContacts;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getInternetAddress() {
		return internetAddress;
	}
	
	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
	
	public String getCompanyIdentifier() {
		return companyIdentifier;
	}
	
	public void setCompanyIdentifier(String companyIdentifier) {
		this.companyIdentifier = companyIdentifier;
	}

	public List<Contact> getCompanyContacts() {
		return companyContacts;
	}

	public void setCompanyContacts(List<Contact> companyContacts) {
		this.companyContacts = companyContacts;
	}
	
	@Override
	public String toString() {
		return "ContactCompany [id=" + id + ", name=" + name + 
				", address=" + address + ", internetAddress="
				+ internetAddress + 
				", companyIdentifier=" + companyIdentifier + "]";
	}
}
