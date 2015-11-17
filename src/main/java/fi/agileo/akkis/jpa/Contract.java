package fi.agileo.akkis.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String price;
	
	private List<Contact> contacts;
	private List<Deal> deals;
	private ContactCompany contactCompany;

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

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
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
	public String toString() {
		return "Contract [contractId=" + contractId + ", signdate=" + signdate + ", enddate="
				+ enddate + ", contractstatus=" + contractstatus + ", price=" + price + "]";
	}
}
