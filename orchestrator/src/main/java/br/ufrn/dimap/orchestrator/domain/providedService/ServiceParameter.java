package br.ufrn.dimap.orchestrator.domain.providedService;

import java.io.Serializable;

import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.providedService.ParameterType;

public class ServiceParameter implements Serializable {

    private Long parameterId;
    private Long serviceId;
    private Appspot appspot;
    private String parameterName;
    private ParameterType parameterType;
    private String description;
    private ParameterScope parameterScope;

    public ServiceParameter() {}
    
    public ServiceParameter(Appspot appspot, Long serviceId, String parameterName, ParameterType parameterType, String description,ParameterScope parameterScope) {
        this.appspot = appspot;
    	this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.description = description;
        this.serviceId = serviceId;
        this.parameterScope = parameterScope;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

    public ParameterScope getParameterScope() {
        return parameterScope;
    }

    public void setParameterScope(ParameterScope parameterScope) {
        this.parameterScope = parameterScope;
    }

	public Appspot getAppspot() {
		return appspot;
	}

	public void setAppspot(Appspot appspot) {
		this.appspot = appspot;
	}
}
