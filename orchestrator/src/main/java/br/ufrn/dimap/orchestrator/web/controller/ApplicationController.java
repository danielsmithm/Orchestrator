package br.ufrn.dimap.orchestrator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.dimap.orchestrator.domain.application.ProvidedService;
import br.ufrn.dimap.orchestrator.web.form.ApplicationCreationForm;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @GetMapping("/register")
    public String register(Model model){
    	ApplicationCreationForm appCreationForm = 
    			new ApplicationCreationForm();
    	
    	appCreationForm.getApp().setProvidedServices(new AutoPopulatingList<>(ProvidedService.class));
    	
    	model.addAttribute("appRegistration", new ApplicationCreationForm());
        return "application/register";
    }

}
