package fi.agileo.akkis.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEAL")
public class Deal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DEAL_ID")
	private long dealId;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="PERIODTYPE")
	private int periodType;
	
	@Column(name="PERIODLENGTH")
	private int periodLength;
	
	private Contract contract;
	
	private User user;
	
	private List<Contact> contacts;


	public long getDealId() {
		return dealId;
	}

	public void setDealId(long dealId) {
		this.dealId = dealId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPeriodType() {
		return periodType;
	}

	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

	public int getPeriodLength() {
		return periodLength;
	}

	public void setPeriodLength(int periodLength) {
		this.periodLength = periodLength;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Deal [dealId=" + dealId + ", price=" + price + ", periodType=" + periodType + ", periodLength="
				+ periodLength + ", contract=" + contract + ", user=" + user + ", contacts=" + contacts + "]";
	}
	
}
