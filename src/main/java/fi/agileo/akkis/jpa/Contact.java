package fi.agileo.akkis.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinTable;

@Entity

@NamedQueries({
	@NamedQuery(name="seekAllContacts", query="SELECT c FROM Contact c"),
	@NamedQuery(name="Contact.findByType", query="SELECT c FROM Contact c WHERE c.type = :type"),
	@NamedQuery(name="Contact.findForSearch", 
				query="SELECT c FROM Contact c WHERE c.type IN (:seekedTypes) AND c.country LIKE :countrySearch AND " + 
					  " c.salesPerson IS NOT NULL AND c.salesPerson.wholename LIKE :salesManSearch")
})
@Table(name="CONTACT")
public class Contact {

/* Lisättäviä properteja taskiin 
"Create persisted entity class and its properties for contact"	
liittyen: 
Tämä lienee aika valmis propertien osalta?
Yhden parametrin transaktiokerros spring kerrokseen, jotta näkee tallentuuko kantaan?
Väliaikainen CreateContact ContactServiceen?
*/
	
	// Database columns
	
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
	
	// Relationships
	@ManyToMany
	@JoinTable(name="CONTRACTS_CONTACTS")
	private List<Contract> contracts;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="USERID")
	private User salesPerson;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="CONTACTCOMPANYID")
	private ContactCompany contactCompany;

	@ManyToMany(mappedBy="contacts")
	private List<Deal> deals;
	
	// Public methods

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
	
	public User getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(User salesPerson) {
		this.salesPerson = salesPerson;
	}

	public boolean isPublicAvailability() {
		return publicAvailability;
	}

	public void setPublicAvailability(boolean publicAvailability) {
		this.publicAvailability = publicAvailability;
	}

	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
	}

	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Contact))
			return false;
		Contact c = (Contact) o;
		if (c.getContactId() == this.getContactId())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + 
				", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", email=" + email + ", country=" + country + ", type=" + type + ", publicAvailability="
				+ publicAvailability + "]";
	}
}