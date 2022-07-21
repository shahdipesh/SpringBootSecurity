package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/currentUser")
public class CurrentUserController {
    @GetMapping
    public String index(Principal principal) {
        return principal.getName();
    }
}
