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
@Table(name="CONTACTPERSON")
@NamedQueries({
	@NamedQuery(name="ContactPerson.findAllWithoutCompany",
			query="SELECT c FROM ContactPerson c WHERE " +
					"c.salesPerson IS NOT NULL AND " +
					"(:companyNameSearch = '%' OR (c.contactCompany IS NOT NULL AND " +
					 "c.contactCompany.name LIKE :companyNameSearch))"
			),
	@NamedQuery(name="ContactPerson.findByState", query="SELECT c FROM ContactPerson c WHERE c.state = :state"),
	@NamedQuery(name="ContactPerson.findContactPersonsWithoutCompany", 
		query="SELECT c FROM ContactPerson c WHERE " + 
			  "c.contactCompany IS NULL AND " +
			  "c.salesPerson IS NOT NULL AND " +
			  "(c.publicAvailability = TRUE OR c.salesPerson = :user)"),
	@NamedQuery(name="ContactPerson.findForSearch", 
				query="SELECT c FROM ContactPerson c WHERE " +
					  "c.firstNames LIKE :firstNameSearch AND " +
					  "c.lastName LIKE :lastNameSearch AND " +	
					  "c.country LIKE :countrySearch AND " +
					  "c.salesPerson IS NOT NULL AND " +
					  "c.salesPerson.firstNames LIKE :salesManFirstNamesSearch AND " +
					  "c.salesPerson.lastName LIKE :salesManLastNameSearch AND " + 
					  "c.state IN :seekedStates AND " +
					  "c.contactCompany IS NOT NULL AND " +
					  "c.contactCompany.name LIKE :companyNameSearch" 
			),
	@NamedQuery(name="ContactPerson.findForSearchCompanyFree", 
		query="SELECT c FROM ContactPerson c WHERE " +
		  "c.firstNames LIKE :firstNameSearch AND " +
		  "c.lastName LIKE :lastNameSearch AND " +	
		  "c.country LIKE :countrySearch AND " +
		  "c.salesPerson IS NOT NULL AND " +
		  "c.salesPerson.firstNames LIKE :salesManFirstNamesSearch AND " +
		  "c.salesPerson.lastName LIKE :salesManLastNameSearch AND " + 
		  "c.state IN :seekedStates" 
		),
	@NamedQuery(name="ContactPerson.findForSearchSalesman", 
				query="SELECT c FROM ContactPerson c WHERE " +
					  "c.firstNames LIKE :firstNameSearch AND " +
					  "c.lastName LIKE :lastNameSearch AND " +	
					  "c.country LIKE :countrySearch AND " +
					  "c.state IN :seekedStates AND " +
					  "c.salesPerson IS NOT NULL AND " +
					  "c.salesPerson.firstNames LIKE :salesManFirstNamesSearch AND " +
					  "c.salesPerson.lastName LIKE :salesManLastNameSearch AND " + 
					  "(c.publicAvailability = TRUE OR c.salesPerson = :user) AND " +
					  "c.contactCompany IS NOT NULL AND " +
					  "c.contactCompany.name LIKE :companyNameSearch" 
			),
	@NamedQuery(name="ContactPerson.findForSearchSalesmanCompanyFree", 
	query="SELECT c FROM ContactPerson c WHERE " +
		  "c.firstNames LIKE :firstNameSearch AND " +
		  "c.lastName LIKE :lastNameSearch AND " +	
		  "c.country LIKE :countrySearch AND " +
		  "c.state IN :seekedStates AND " +
		  "c.salesPerson IS NOT NULL AND " +
		  "c.salesPerson.firstNames LIKE :salesManFirstNamesSearch AND " +
		  "c.salesPerson.lastName LIKE :salesManLastNameSearch AND " + 
		  "(c.publicAvailability = TRUE OR c.salesPerson = :user)"
	),
	@NamedQuery(name="ContactPerson.contactsOfCompanyForSalesman",
				query="SELECT c FROM ContactPerson c WHERE " +
	                  "c.contactCompany = :company AND " +
					  "(c.publicAvailability = TRUE OR c.salesPerson = :user)"),
	@NamedQuery(name="ContactPerson.contactsOfContractForSalesman",
				query="SELECT c FROM ContactPerson c WHERE " +
					  ":contract MEMBER c.contracts"
			)
})
public class ContactPerson {

/* Lisättäviä properteja taskiin 
"Create persisted entity class and its properties for contact person"	
liittyen: 
Tämä lienee aika valmis propertien osalta?
Yhden parametrin transaktiokerros spring kerrokseen, jotta näkee tallentuuko kantaan?
Väliaikainen CreateContact ContactServiceen?
*/
	
	// Database columns
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONTACTPERSON_ID")
	private long contactPersonId;
	
	@Column(name="FIRSTNAMES")
	private String firstNames;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="ZIPCODE")
	private String zipCode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="STATE")
	private String state;
	
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

	
	// Public methods

	public long getContactPersonId()
	{
		return this.contactPersonId;
	}
	
	public void setContactPersonId(long contactPersonId)
	{
		this.contactPersonId = contactPersonId;
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
	
	public String getState()
	{
		return this.state;
	}
	
	public void setState(String state)
	{
		this.state = state;
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

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ContactPerson))
			return false;
		ContactPerson c = (ContactPerson) o;
		if (c.getContactPersonId() == this.getContactPersonId())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "ContactPerson [" + 
				"contactPersonId=" + contactPersonId + 
				", firstNames=" + firstNames +
				", lastName=" + lastName +
				", address=" + address + 
				", zipCode=" + zipCode +
				", city=" + city +
				", phone=" + phone +
				", country=" + country +
				", email=" + email +  
				", state=" + state + 
				", publicAvailability=" + publicAvailability + 
				"]";
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}