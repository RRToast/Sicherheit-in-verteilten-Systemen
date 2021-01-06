package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HelloController {

	@RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        return "customers";
    }


    @GetMapping("/tee")
    public String tee(){
	    return "iterationTest";
    }



}
