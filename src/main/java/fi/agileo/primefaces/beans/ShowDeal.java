package fi.agileo.primefaces.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.ContractService;
import fi.agileo.spring.service.DealService;

@ManagedBean
@SessionScoped
public class ShowDeal {
	private DealService dealService;
	
	private Contact contact;
	private Deal deal;
	private List<Deal> deals;
	private List<Contact> contacts;
	
	public DealService getDealService() {
		return dealService;
	}

	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}
	
	public String viewDeal() {
		/* fetch deal information into DealInformation object 
		   and set its properties to members of this managed bean and 
		   return the view page name */
		
		return "";
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}
	
}
