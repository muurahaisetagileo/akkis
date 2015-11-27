package fi.agileo.primefaces.beans.contract;

// import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/* import fi.agileo.akkis.jpa.Contact;
import fi.agileo.akkis.jpa.ContactCompany; */
import fi.agileo.akkis.jpa.Contract;
/* import fi.agileo.akkis.jpa.Deal;
import fi.agileo.akkis.jpa.User; */
import fi.agileo.spring.service.ContractService;

@ManagedBean
@SessionScoped
public class ShowOrModifyContract {
	@ManagedProperty("#{contractService}")
	private ContractService contractService;
	
	private Contract contract;
	private boolean modifyBasics = false;

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	public String toShowContract() {
		return "/contract/showormodifycontract";
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public boolean isModifyBasics() {
		return modifyBasics;
	}

	public void setModifyBasics(boolean modifyBasics) {
		this.modifyBasics = modifyBasics;
	}
	
	public String toModifyBasics() {
		return null;
	}
	
	public String saveModifiedContract() {
		contractService.modifyContractBasicInfo(contract);
		return null;
	}
}
