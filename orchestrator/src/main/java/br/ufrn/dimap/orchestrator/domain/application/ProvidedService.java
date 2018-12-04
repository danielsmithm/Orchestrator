package br.ufrn.dimap.orchestrator.domain.application;

import java.util.ArrayList;
import java.util.List;

public class ProvidedService {

    private int id;
    private String serviceName;
    private String serviceDescription;
    private String accessPath;
    private HTTPVerb httpVerb;

    private List<ServiceParameter> serviceParameters;

    public ProvidedService(String serviceName, String serviceDescription) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceParameters = new ArrayList<>();
    }

    public void addParameter(String parameterName,ParameterType parameterType,String description) throws ParameterNameAlreadyTaken {

        if(serviceParameters.stream().anyMatch(serviceParameter -> serviceParameter.getParameterName().equals(parameterName))){
            throw new ParameterNameAlreadyTaken("This service already has a parameter with the provided name");
        }

        serviceParameters.add(new ServiceParameter(parameterName,parameterType,description));
    }

    //Persistence constructor. Should be used only by the persistence mechanism.
    public ProvidedService() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
