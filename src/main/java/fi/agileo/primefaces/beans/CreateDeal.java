package fi.agileo.primefaces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User;
import fi.agileo.spring.service.DealService;
import fi.agileo.spring.service.UserService;

@ManagedBean
@SessionScoped
public class CreateDeal {
	@ManagedProperty("#{dealService}")
	private DealService dealService;

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

	public String saveDeal() {
		return null;
		/*
		 * // Calling Business Service delaService.createDeal(deal); //
		 * Add message FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("The deal " + this.deal.getDeal() +
		 * " is saved successfully")); return "";
		 */

	}
}
