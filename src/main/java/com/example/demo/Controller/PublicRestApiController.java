package com.example.demo.Controller;


import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicRestApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test1")
    public String test1() {
        return "Api test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "Api test2";
    }

    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }
}
