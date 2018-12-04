package br.ufrn.dimap.orchestrator.domain.application;

public class ServiceParameter{

    private int parameterId;
    private String parameterName;
    private ParameterType parameterType;
    private String description;

    public ServiceParameter(String parameterName, ParameterType parameterType, String description) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.description = description;
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
}
