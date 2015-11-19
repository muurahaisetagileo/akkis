package fi.agileo.akkis.jpa;

import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CONTRACT")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTRACT_ID")
	private long contractId;

	@Column(name = "SIGNDATE")
	@Temporal(TemporalType.DATE)
	private Date signdate;

	@Column(name = "ENDDATE")
	@Temporal(TemporalType.DATE)	
	private Date enddate;

	@Column(name = "CONTRACTSTATUS")
	private String contractstatus;

	@Column(name = "PRICE")
	private double price;
	
	@ManyToMany(mappedBy="contracts")
	private List<Contact> contacts;
	
	@OneToMany(mappedBy="contract")
	private List<Deal> deals;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="CONTACTCOMPANYID")
	private ContactCompany contactCompany;

	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="USERID")
	private User user;
	
	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public String getContractstatus() {
		return contractstatus;
	}

	public void setContractstatus(String contractstatus) {
		this.contractstatus = contractstatus;
	}
	
	// Huom. Suomalaiset kommentit tässä vaiheessa selvennyksinä koodissa, voi
	// poistaa myöhemmin
	// kun alunperin oli kai tarkoitus kommentoida englanniksi

	// signDate (Sopimuksen solmimispäivämäärä)
	public Date getSigndate() {
		return this.signdate;
	}

	public void setSigndate(Date signdate) {
		this.signdate = signdate;
	}

	// endDate (Sopimuksen päättymispäivämäärä)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	// status draft, signed, delivered or expired (Sopimuksen tila)
	public String getcontractStatus() {
		return this.contractstatus;

	}

	public void setcontractStatus(String contractstatus) {
		this.contractstatus = contractstatus;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

	public ContactCompany getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(ContactCompany contactCompany) {
		this.contactCompany = contactCompany;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Contract))
			return false;
		Contract c = (Contract) o;
		if (c.getContractId() == this.getContractId())
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", signdate=" + signdate + ", enddate="
				+ enddate + ", contractstatus=" + contractstatus + ", price=" + price + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
