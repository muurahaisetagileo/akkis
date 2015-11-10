package fi.agileo.akkis.jpa;

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

public String getContact() {
return null;	
}

//Solmimispäivämäärä
//Sopimuksen päättymispäivämäärä
//Sopimuksen tila, signed tai delivered
//Mäppäytyy edelleen kauppaan


public void setContact(String contact){}		

}

