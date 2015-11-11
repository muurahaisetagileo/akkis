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
@Table(name="CONTRACT")

public class Contract {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="CONTRACT_ID")
private long contractId;

@Column(name="CONTACT")
private String contact;

@Column(name="SIGNDATE")
private String signdate;

@Column(name="ENDDATE")
private String enddate;

@Column(name="CONTRACTSTATUS")
private String contractstatus;

@Column(name="PRICE")
private String price;


public String getContact() 
{
	return this.contact;	
}

public void setContact(String contact)
{		
	this.contact = contact;
}

// Huom. Suomalaiset kommentit tässä vaiheessa selvennyksinä koodissa, voi poistaa myöhemmin
// kun alunperin oli kai tarkoitus kommentoida englanniksi

//signDate (Sopimuksen solmimispäivämäärä)
public String getsignDate() 
{
	return this.signdate;	
}

public void setsignDate(String signdate) {
	this.signdate = signdate;
}

//endDate (Sopimuksen päättymispäivämäärä)
public String getendDate() {
	return this.enddate;	
}	

public void setendDate(String enddate) {
	this.enddate = enddate;
}

//status draft, signed, delivered or expired (Sopimuksen tila)
public String getcontractStatus() {
	return this.contractstatus;
	
}

public void setcontractStatus(String contractstatus) {
	this.contractstatus = contractstatus;
}

public String getPrice() {
	return this.price;
	
}

public void setcontractPrice(String price) {
	this.price = price;
}

@Override
public String toString() {
	return "Contract [contractId=" + contractId + ", contact=" + contact + ", signdate=" + signdate + ", enddate="
			+ enddate + ", contractstatus=" + contractstatus + ", price=" + price + "]";
}





//Mäppäytyy edelleen kauppaan

}
