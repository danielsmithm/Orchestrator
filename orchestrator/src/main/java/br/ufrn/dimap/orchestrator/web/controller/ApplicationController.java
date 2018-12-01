package br.ufrn.dimap.orchestrator.web.controller;

import br.ufrn.dimap.orchestrator.application.ApplicationService;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.dimap.orchestrator.domain.application.ProvidedService;
import br.ufrn.dimap.orchestrator.web.form.ApplicationCreationForm;

import javax.validation.Valid;

/**
 * Controller for the application use cases.
 * 
 * @author Daniel Smith
 *
 */
@Controller
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/register")
    public String register(Model model){
    	ApplicationCreationForm appCreationForm = 
    			new ApplicationCreationForm();
    	
    	//appCreationForm.getApp().setProvidedServices(new AutoPopulatingList<>(ProvidedService.class));
    	
    	model.addAttribute("appRegistration", new ApplicationCreationForm());
        return "application/register";
    }

    @PostMapping("/register")
    public String submitRegister(@Valid @ModelAttribute("appRegistration")ApplicationCreationForm form) throws ApplicationAlreadyRegisteredException {

        Application application = applicationService.registerApplication(Appspot.from(form.getAppspot()));

        return "application/register";
    }



}
