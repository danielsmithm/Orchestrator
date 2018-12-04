package br.ufrn.dimap.orchestrator.domain.providedService;

import br.ufrn.dimap.orchestrator.domain.providedService.ParameterType;

public class ServiceParameter{

    private Long parameterId;
    private Long serviceId;
    private String parameterName;
    private ParameterType parameterType;
    private String description;

    public ServiceParameter(Long serviceId, String parameterName, ParameterType parameterType, String description) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.description = description;
        this.serviceId = serviceId;
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
}
