package br.ufrn.dimap.orchestrator.web.controller;

import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.service.ProvidedServiceService;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;
import br.ufrn.dimap.orchestrator.domain.providedService.ProvidedService;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;

import br.ufrn.dimap.orchestrator.web.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufrn.dimap.orchestrator.web.form.ApplicationCreationForm;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * Controller for the application use cases.
 * 
 * @author Daniel Smith
 *
 */
@Controller
@RequestMapping("/")
public class OrchestratorController extends BaseController {

    private final ApplicationService applicationService;
    
    private final ProvidedServiceService providedServiceService;

    @Autowired
    public OrchestratorController(ApplicationService applicationService, ProvidedServiceService providedServiceService) {
        this.applicationService = applicationService;
        this.providedServiceService = providedServiceService;
    }
    
    @GetMapping("/")
    public String index() {
    	return "redirect:/explore";
    }
    
    @GetMapping("/explore")
    public String list(Model model) {
    	
    	List<Application> apps = applicationService.findAllApplications();
    	
    	model.addAttribute("apps", apps);
    	
    	return "application/list";
    }
    
    @GetMapping(value="/explore", params= {"search"})
    public String list(Model model,
    		@RequestParam("search") String search) {
    	
    	List<Application> apps  = applicationService.search(search);
    	
    	model.addAttribute("apps", apps);
    	
    	return "application/list";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
    
    @GetMapping("/explore/{appspot}")
    public String listServices(@PathVariable("appspot") String appspot, Model model) throws ApplicationNotFoundException {
    	
    	Application app = applicationService.findApplicationByAppspot(new Appspot(appspot));

    	app.setServices(providedServiceService.listByApplication(app.getAppspot(), true));
    	
    	model.addAttribute("app", app);
    	
    	return "application/view";
    }
    
}
