package br.ufrn.dimap.orchestrator.web.controller;

import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;
import br.ufrn.dimap.orchestrator.security.ApplicationUserDetailsAdapter;
import br.ufrn.dimap.orchestrator.security.GoogleCloudAuthenticationProvider;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationController(ApplicationService applicationService, PasswordEncoder passwordEncoder) {
        this.applicationService = applicationService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("")
    public String index(Model model){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	ApplicationUserDetailsAdapter userDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();
    	//model.addAttribute("app", ApplicationCreationForm.from(userDetails.getApplication()));
    	model.addAttribute("app", ApplicationCreationForm.from(userDetails.getApplication()));
    	return "application/edit";
    }

    @GetMapping("/register")
    public String register(Model model){
    	ApplicationCreationForm appCreationForm = new ApplicationCreationForm();
    	model.addAttribute("app", new ApplicationCreationForm());
        return "application/register";
    }

    @PostMapping("/register")
    public String submitRegister(@Valid @ModelAttribute("appRegistration") ApplicationCreationForm form) throws ApplicationAlreadyRegisteredException, PasswordNotInformedException {

        Application application = applicationService.registerApplication(Appspot.from(form.getAppspot()), form.getOwnerName(), passwordEncoder.encode(form.getPassword()));

        return "application/register";
    }


}
