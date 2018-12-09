package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProvidedService implements Serializable {

    private Long id;
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
        this.setServiceParameters(new ArrayList<>());
    }

    //Persistence constructor. Should be used only by the persistence mechanism.
    public ProvidedService() {}

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

	public void update(String serviceName, String serviceDescription, String accessPath, HTTPVerb httpVerb) {
		//TODO perform checks!
		this.serviceName = serviceName;
		this.serviceDescription = serviceDescription;
		this.accessPath = accessPath;
		this.httpVerb = httpVerb;
	}

	public List<ServiceParameter> getServiceParameters() {
		return serviceParameters;
	}

	public void setServiceParameters(List<ServiceParameter> serviceParameters) {
		this.serviceParameters = serviceParameters;
	}
}
