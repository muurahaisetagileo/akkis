package fi.agileo.akkis.jpa;

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
	
}
