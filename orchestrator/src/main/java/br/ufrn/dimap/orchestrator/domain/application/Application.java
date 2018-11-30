package br.ufrn.dimap.orchestrator.domain.application;

import java.util.ArrayList;
import java.util.List;

public class Application {

	private String ownerName;
	private Appspot appspot;
	private List<ProvidedService> providedServices;

	public Application(Appspot appspot){
		this.appspot = appspot;
		this.providedServices = new ArrayList<>();
	}

	//Persistence constructor. Should be used only by the persistence mechanism.
	public Application(){}

	public boolean hasProvidedService(String serviceName) {
		return providedServices.stream().anyMatch(providedService -> providedService.getServiceName().equals(serviceName));
	}

	public void addProvidedService(String serviceName, String serviceDescription) {
		ProvidedService providedService = new ProvidedService(serviceName,serviceDescription);

		providedServices.add(providedService);
	}

	//GETTERS AND SETTERS BELLOW THIS LINE
	public Appspot getAppspot() {
		return appspot;
	}

	public void setAppspot(Appspot appspot) {
		this.appspot = appspot;
	}

	public List<ProvidedService> getProvidedServices() {
		return providedServices;
	}

	public void setProvidedServices(List<ProvidedService> providedServices) {
		this.providedServices = providedServices;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
