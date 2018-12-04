package br.ufrn.dimap.orchestrator.web.form;

import br.ufrn.dimap.orchestrator.domain.providedService.HTTPVerb;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedService;

public class ProvidedServiceCreationForm {

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
	
	public static ProvidedServiceCreationForm from(ProvidedService providedService) {
		ProvidedServiceCreationForm form = new ProvidedServiceCreationForm();
		form.serviceDescription= providedService.getServiceDescription();
		form.serviceName= providedService.getServiceName();
		form.accessPath = providedService.getAccessPath();
		form.httpVerb = providedService.getHttpVerb();
		return form;
	}
}
