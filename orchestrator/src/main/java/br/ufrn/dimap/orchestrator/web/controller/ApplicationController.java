package br.ufrn.dimap.orchestrator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
