package com.example.demo.Controller;


import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.sun.xml.bind.annotation.XmlIsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CreateUserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        //check if any fields are null
        if (user.getUsername() == "" || user.getPassword() == "" || user.getEmail() == "") {
            throw new IllegalArgumentException("Username, password, and email are required");
        }
       String pwd=user.getPassword();
       user.setPassword(passwordEncoder.encode(pwd));
       user.setLoginMethod(0);
        return userService.create(user);
    }
}
