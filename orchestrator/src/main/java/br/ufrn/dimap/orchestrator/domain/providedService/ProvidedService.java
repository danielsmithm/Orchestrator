package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;

import java.util.ArrayList;
import java.util.List;

public class ProvidedService {

    private long id;
    private Appspot appspot;
    private String serviceName;
    private String serviceDescription;
    private String accessPath;
    private HTTPVerb httpVerb;
    private List<ServiceParameter> serviceParameters;

    public ProvidedService(Appspot appspot, String serviceName, String serviceDescription, String accessPath, HTTPVerb httpVerb) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.accessPath = accessPath;
        this.httpVerb = httpVerb;
        this.appspot = appspot;
        this.serviceParameters = new ArrayList<>();
    }

    public void addParameter(String parameterName, ParameterType parameterType, String description) throws ParameterNameAlreadyTaken {

        if(serviceParameters.stream().anyMatch(serviceParameter -> serviceParameter.getParameterName().equals(parameterName))){
            throw new ParameterNameAlreadyTaken("This service already has a parameter with the provided name");
        }

        serviceParameters.add(new ServiceParameter(parameterName,parameterType,description));
    }

    //Persistence constructor. Should be used only by the persistence mechanism.
    public ProvidedService() {}

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public HTTPVerb getHttpVerb() {
		return httpVerb;
	}

	public void setHttpVerb(HTTPVerb httpVerb) {
		this.httpVerb = httpVerb;
	}

	public Appspot getAppspot() {
		return appspot;
	}

	public void setAppspot(Appspot appspot) {
		this.appspot = appspot;
	}
}
