package br.ufrn.dimap.orchestrator.web.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.ParameterType;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedService;
import br.ufrn.dimap.orchestrator.domain.providedService.ServiceParameter;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ParameterNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ProvidedServiceNotFoundException;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNameAlreadyTaken;
import br.ufrn.dimap.orchestrator.domain.providedService.exceptions.ServiceNotFoundException;
import br.ufrn.dimap.orchestrator.security.ApplicationUserDetailsAdapter;
import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.service.ProvidedServiceService;
import br.ufrn.dimap.orchestrator.web.form.ParameterCreationForm;
import br.ufrn.dimap.orchestrator.web.form.ProvidedServiceCreationForm;

@Controller
@RequestMapping("/services")
public class ServicesController {

    private final ApplicationService applicationService;
    private final ProvidedServiceService providedServiceService;
    
    @Autowired
    public ServicesController(
    		ApplicationService applicationService,
    		ProvidedServiceService providedServiceService) {
    	this.applicationService = applicationService;
    	this.providedServiceService = providedServiceService;
    }
    
    @GetMapping("")
    public String listServices(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUserDetailsAdapter authenticationDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();
 	
    	List<ProvidedService> services = this.providedServiceService
    			.listByApplication(authenticationDetails.getApplication().getAppspot(), false);
    	
    	model.addAttribute("services", services);
    	
    	return "application/services/list";
    }
    
    @GetMapping("new")
    public String createService(Model model) {
    	model.addAttribute("service", new ProvidedServiceCreationForm());
    	return "application/services/add";
    }
    
    @PostMapping("new")
    public String submitNewService(
    		@Valid @ModelAttribute("service") ProvidedServiceCreationForm serviceForm,
    		Model model) throws ApplicationNotFoundException, ServiceNameAlreadyTaken {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUserDetailsAdapter authenticationDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();
    	
    	ProvidedService newProvService = this.providedServiceService.createProvidedService(
    			authenticationDetails.getApplication().getAppspot(), 
    			serviceForm.getServiceName(), 
    			serviceForm.getServiceDescription(),
    			serviceForm.getAccessPath(),
    			serviceForm.getHttpVerb());
    	
    	model.addAttribute("service", serviceForm);
    	model.addAttribute("parameter", new ParameterCreationForm());
    	
    	return "redirect:/services/"+newProvService.getId();
    }
    
    @GetMapping("{serviceId}")
    public String editService(@PathVariable("serviceId") Long serviceId, Model model) throws ProvidedServiceNotFoundException {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUserDetailsAdapter authenticationDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();
  
    	ProvidedService provService = this.providedServiceService.findProvidedServiceById(
    			authenticationDetails.getApplication().getAppspot(), serviceId);

    	provService.setServiceParameters(this.providedServiceService.listServiceParameterByServiceId(serviceId));
    	
    	model.addAttribute("service", ProvidedServiceCreationForm.from(provService));
    	model.addAttribute("parameter", new ParameterCreationForm());
    	
    	return "application/services/edit";
    }
    
    @PostMapping("{serviceId}")
    public String editSubmitService(
    		@PathVariable("serviceId") Long serviceId, 
    		@Valid @ModelAttribute("service") ProvidedServiceCreationForm serviceForm,
    		Model model) throws ProvidedServiceNotFoundException {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUserDetailsAdapter authenticationDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();
  
    	ProvidedService provService = 
    			this.providedServiceService.update(
    					authenticationDetails.getApplication().getAppspot(),
    					serviceId,
    					serviceForm.getServiceName(),
    					serviceForm.getServiceDescription(),
    					serviceForm.getAccessPath(),
    					serviceForm.getHttpVerb());
    	
    	model.addAttribute("service", ProvidedServiceCreationForm.from(provService));
    	model.addAttribute("parameter", new ParameterCreationForm());
    	
    	return "redirect:/services/"+serviceId;
    }
    
    @PostMapping("{serviceId}/delete")
    public String deleteService(@PathVariable("serviceId") Long serviceId, Model model) throws ProvidedServiceNotFoundException {   
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUserDetailsAdapter authenticationDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();

    	this.providedServiceService.removeProvidedService(authenticationDetails.getApplication().getAppspot(), serviceId);

    	return "redirect:/services";
    }
    
    @PostMapping("{serviceId}/parameters/new")
    public String createParam(
    		@PathVariable("serviceId") Long serviceId, 
    		@Valid @ModelAttribute("parameter") ParameterCreationForm parameter,
    		Model model) 
    				throws ServiceNotFoundException, ParameterNameAlreadyTaken, ProvidedServiceNotFoundException {
    	    	
    	this.providedServiceService.addParameter(
    			serviceId,
    			parameter.getName(),
    			parameter.getType(),
    			parameter.getDescription(),
				parameter.getScope());
    	
    	// TODO what to put in the model?
    	return "redirect:/services/"+serviceId;
    }
    
    @PostMapping("{serviceId}/parameters/{parameterId}/delete")
    public String deleteParam(
    		@PathVariable("serviceId") Long serviceId, 
    		@PathVariable("parameterId") Long parameterId,
    		Model model) 
    				throws ProvidedServiceNotFoundException {
    	
    		this.providedServiceService.removeParameter(serviceId, parameterId);
    		
    	return "redirect:/services/"+serviceId;
    }
	
}
