package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WannaBeController {

    @GetMapping("/test")
    public String test(Principal user) {
        return "Hello " + user.getName();
    }
}
