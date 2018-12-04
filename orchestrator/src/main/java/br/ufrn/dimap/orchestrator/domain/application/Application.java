package br.ufrn.dimap.orchestrator.domain.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;

public class Application {

	private String ownerName;
	private Appspot appspot;
	private String password;
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

	public void addParameter(int serviceId, String parameterName,ParameterType parameterType,String description) throws ServiceNotFoundException, ParameterNameAlreadyTaken {
		Optional<ProvidedService> serviceOptional = providedServices.stream()
				.filter(providedService -> providedService.getId() == serviceId)
				.findFirst();

		if(!serviceOptional.isPresent()){
			throw new ServiceNotFoundException("The provided service was not found for the application.");
		}

		ProvidedService providedService = serviceOptional.get();

		providedService.addParameter(parameterName,parameterType,description);

	}

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

	public List<ProvidedService> getProvidedServices() {
		return providedServices;
	}

	public void setProvidedServices(List<ProvidedService> providedServices) {
		this.providedServices = providedServices;
	}

}
