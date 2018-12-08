package br.ufrn.dimap.orchestrator.web.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufrn.dimap.orchestrator.domain.providedService.HTTPVerb;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedService;
import br.ufrn.dimap.orchestrator.domain.providedService.ServiceParameter;

public class
ProvidedServiceCreationForm {

	private Long serviceId;
	
	@NotBlank
	@NotNull
    private String serviceName;
	
	@NotBlank
	@NotNull
    private String serviceDescription;
	
	@NotBlank
	@NotNull
    private String accessPath;
    private HTTPVerb httpVerb;
    private List<ServiceParameter> serviceParameters;
    
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
		if (providedService.getId() != null)
			form.setServiceId(providedService.getId());
		form.serviceDescription= providedService.getServiceDescription();
		form.serviceName= providedService.getServiceName();
		form.accessPath = providedService.getAccessPath();
		form.httpVerb = providedService.getHttpVerb();
		form.serviceParameters = providedService.getServiceParameters();
		return form;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public List<ServiceParameter> getServiceParameters() {
		return serviceParameters;
	}

	public void setServiceParameters(List<ServiceParameter> serviceParameters) {
		this.serviceParameters = serviceParameters;
	}
}
