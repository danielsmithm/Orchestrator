package br.ufrn.dimap.orchestrator.web.controller;

import br.ufrn.dimap.orchestrator.service.ApplicationService;
import br.ufrn.dimap.orchestrator.domain.application.Application;
import br.ufrn.dimap.orchestrator.domain.application.Appspot;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.PasswordNotInformedException;
import br.ufrn.dimap.orchestrator.domain.application.exceptions.ApplicationAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.dimap.orchestrator.web.form.ApplicationCreationForm;

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
public class OrchestratorController {

    @Autowired
    public OrchestratorController() {}
    
    @GetMapping("explore")
    public String list() {
    	return "application/list";
    }
    
    @GetMapping("login")
    public String login() {
    	return "login";
    }
    
    @GetMapping("{appspot}/services")
    public String listServices(@PathParam("appspot") String appspot, Model model) {
    	return null;
    }
    
    @GetMapping("{appspot}/services/{serviceId}")
    public String viewService(
    		@PathParam("appspot") String appspot, 
    		@PathParam("serviceId") String serviceId, 
    		Model model) {
    	return null;
    }

}
