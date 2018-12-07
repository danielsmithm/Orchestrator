package br.ufrn.dimap.orchestrator.web.controller;

import br.ufrn.dimap.orchestrator.domain.application.AppNameNotInformedException;
import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;
import br.ufrn.dimap.orchestrator.security.ApplicationUserDetailsAdapter;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.dimap.orchestrator.web.form.ApplicationCreationForm;
import org.springframework.web.servlet.ModelAndView;

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
    	model.addAttribute("app", ApplicationCreationForm.from(userDetails.getApplication()));
    	return "application/edit";
    }

    @GetMapping("/register")
    public String register(Model model){
    	model.addAttribute("app", new ApplicationCreationForm());
        return "application/register";
    }

    @PostMapping("/register")
    public String submitRegister(@ModelAttribute("app") @Valid ApplicationCreationForm app, BindingResult bindingResult, Model model) throws ApplicationAlreadyRegisteredException, PasswordNotInformedException, AppNameNotInformedException {

    	model.addAttribute("app", app);
    	
    	if (app.getPassword() == null || app.getPassword().equals(""))
    		bindingResult.rejectValue("password", "error.password", "Password must be provided");
    	if (app.getPasswordConfirmation() == null || app.getPasswordConfirmation().equals(""))
    		bindingResult.rejectValue("passwordConfirmation", "error.passwordConfirmation", "Password confirmation must be provided");
    	if (app.getPasswordConfirmation() != null && !app.getPasswordConfirmation().equals("") &&
    			app.getPassword() != null && !app.getPassword().equals("") 
    			&& !app.getPasswordConfirmation().equals(app.getPassword()))
    		bindingResult.rejectValue("passwordConfirmation", "error.passwordConfirmation", "Password and confirmation do not match");
    	
    	if (bindingResult.hasErrors())
    		return "application/register";
    	
        Application application = applicationService.registerApplication(Appspot.from(app.getAppspot()),
                                                                        app.getOwnerName(),
                                                                        passwordEncoder.encode(app.getPassword()),
                                                                        app.getAppName(),
                                                                        app.getAppDescription());
        
        return "redirect:/login";
    }

    @PostMapping("")
    public String updateRegister(@Valid @ModelAttribute("app") ApplicationCreationForm app, BindingResult bindingResult, Model model) throws ApplicationNotFoundException {
    	
    	if (app.getPasswordConfirmation() != null && !app.getPasswordConfirmation().equals("") &&
    			app.getPassword() != null && !app.getPassword().equals("") 
    			&& !app.getPasswordConfirmation().equals(app.getPassword()))
    		bindingResult.rejectValue("passwordConfirmation", "error.passwordConfirmation", "Password and confirmation do not match");
    	
    	if (bindingResult.hasErrors())
    		return "application/edit";
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUserDetailsAdapter authenticationDetails = (ApplicationUserDetailsAdapter) auth.getPrincipal();

        Application currentApp = authenticationDetails.getApplication();
    	    	
    	Application application = applicationService.update(currentApp.getAppspot(),
                                                            app.getOwnerName(),
                                                            app.getPassword(),
                                                            app.getAppName(),
                                                            app.getAppDescription());
    	
    	authenticationDetails.setApplication(application);
    	
    	model.addAttribute("app", ApplicationCreationForm.from(application));
    	
    	return "application/edit";
    }

}
