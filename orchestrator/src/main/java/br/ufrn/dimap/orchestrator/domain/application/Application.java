package br.ufrn.dimap.orchestrator.domain.application;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.ParameterType;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedService;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNotFoundException;

public class Application implements Serializable {

	private String ownerName;
	private Appspot appspot;
	private String appName;
	private String appDescription;
	private String password;
	private List<ProvidedService> services;
	private List<ServiceUse> googleServiceUse;
	
	public Application(Appspot appspot){
		this.appspot = appspot;
	}

	//Persistence constructor. Should be used only by the persistence mechanism.
	public Application(){}

	public void update(String ownerName, String password, String appName, String appDescription) {
		if (!ownerName.isEmpty())
			this.ownerName = ownerName;
		if (!password.isEmpty())
			this.password = password;
		if (!appName.isEmpty())
			this.appName = appName;
		if (!appDescription.isEmpty())
			this.appDescription = appDescription;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public List<ServiceUse> getGoogleServiceUse() {
		return googleServiceUse;
	}

	public void setGoogleServiceUse(List<ServiceUse> googleServiceUse) {
		this.googleServiceUse = googleServiceUse;
	}
}
