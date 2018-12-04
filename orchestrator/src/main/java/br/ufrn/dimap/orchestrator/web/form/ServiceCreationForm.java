package br.ufrn.dimap.orchestrator.web.form;

import br.ufrn.dimap.orchestrator.domain.application.ProvidedService.HTTPVerb;

public class ServiceCreationForm {

    private String serviceName;
    private String serviceDescription;
    private String accessPath;
    private HTTPVerb httpVerb;
    
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
	
}
