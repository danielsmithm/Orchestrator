package br.ufrn.dimap.orchestrator.domain.application;

import java.util.List;
import java.util.Optional;

import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.ParameterType;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedService;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNotFoundException;

public class Application {

	private String ownerName;
	private Appspot appspot;
	private String password;
	private List<ProvidedService> services;
	
	public Application(Appspot appspot){
		this.appspot = appspot;
	}

	//Persistence constructor. Should be used only by the persistence mechanism.
	public Application(){}

	public void update(String ownerName, String password) {
		if (!ownerName.isEmpty())
			this.ownerName = ownerName;
		if (!password.isEmpty())
			this.password = password;
	}

	//GETTERS AND SETTERS BELLOW THIS LINE
	public Appspot getAppspot() {
		return appspot;
	}

	public void setAppspot(Appspot appspot) {
		this.appspot = appspot;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ProvidedService> getServices() {
		return services;
	}

	public void setServices(List<ProvidedService> services) {
		this.services = services;
	}

}
