package fi.agileo.primefaces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Contract;
import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.Deal;
import fi.agileo.spring.service.DealService;

@ManagedBean
@SessionScoped
public class CreateDeal {
	@ManagedProperty("#{dealService}")
	private DealService dealService;
	
	private Contract contract = new Contract();
	private Contact contact = new Contact();

	private Deal deal = new Deal();

	public DealService getDealService() {
		return dealService;
	}

	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}
	
	public Contract getContract() {
		return contract;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	

	public String saveDeal() {
		return null;
		/*
		 * // Calling Business Service dealService.createDeal(deal); //
		 * Add message FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("The deal " + this.deal.getDeal() +
		 * " is saved successfully")); return "";
		 */

	}
}
