package br.ufrn.dimap.orchestrator.web.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.web.form.ParameterCreationForm;
import br.ufrn.dimap.orchestrator.web.form.ServiceCreationForm;

@Controller
@RequestMapping("/services")
public class ServicesController {

    private final ApplicationService applicationService;

    @Autowired
    public ServicesController(ApplicationService applicationService) {
    	this.applicationService = applicationService;
    }
    
    @GetMapping("")
    public String listServices() {
    	return "application/services/list";
    }
    
    @GetMapping("new")
    public String createService(Model model) {
    	model.addAttribute("service", new ServiceCreationForm());
    	return "application/services/add";
    }
    
    @GetMapping("{serviceId}")
    public String editService(@PathParam("serviceId") String serviceId, Model model) {
    	
    	model.addAttribute("service", new ServiceCreationForm());
    	model.addAttribute("parameter", new ParameterCreationForm());
    	
    	return "application/services/edit";
    }
    
    @PostMapping("{serviceId}/delete")
    public String deleteService(@PathParam("serviceId") String serviceId, Model model) {    	
    	return "application/services/edit";
    }
    
    @PostMapping("{serviceId}/parameters/new")
    public String createParam(@PathParam("serviceId") String serviceId, Model model) {
    	
    	return "application/services/edit";
    }
    
    @PostMapping("{serviceId}/parameters/delete")
    public String deleteParam(@PathParam("serviceId") String serviceId, Model model) {
    	
    	return "application/services/edit";
    }
	
}
