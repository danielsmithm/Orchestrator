package br.ufrn.dimap.orchestrator.domain.application;

public class ProvidedService {

    private int id;
    private String serviceName;
    private String serviceDescription;
    private String accessPath;
    private HTTPVerb httpVerb;
    
    enum HTTPVerb {
    	POST, GET, PUT, DELETE
    }

    public ProvidedService(String serviceName, String serviceDescription) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
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
