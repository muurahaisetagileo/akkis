package fi.agileo.akkis.jpa;

import java.util.List;

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
@Table(name="CONTACTCOMPANY")
@NamedQueries({
	@NamedQuery(
			name="ContactCompany.allAlphabeticallyByName",
			query="SELECT cc FROM ContactCompany cc ORDER BY cc.name"),
})
public class ContactCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")	
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="INTERNETADDRESS")
	private String internetAddress;
	
	@Column(name="COMPANYIDENTIFIER")
	private String companyIdentifier; // Y-Tunnus
	
	@OneToMany(mappedBy="contactCompany")
	private List<ContactPerson> contactPersons;
	
	@OneToMany(mappedBy="contactCompany")
	private List<Contract> contracts;
	
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

	public List<ContactPerson> getContactPersons() {
		return contactPersons;
	}

	public void setContactPersons(List<ContactPerson> contactPersons) {
		this.contactPersons = contactPersons;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ContactCompany))
			return false;
		ContactCompany c = (ContactCompany) o;
		if (c.getId() == this.getId())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "ContactCompany [id=" + id + ", name=" + name + 
				", address=" + address + ", internetAddress="
				+ internetAddress + 
				", companyIdentifier=" + companyIdentifier + "]";
	}
}
