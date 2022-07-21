//package com.example.demo.Db;
//
//import com.example.demo.Entity.User;
//import com.example.demo.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class DbInit implements CommandLineRunner {
//
//    @Autowired
//    private  UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        User dan = new User("dan","dan@gmail.com",passwordEncoder.encode("dan123"),"USER","ACCESS_TEST1");
//        User admin = new User("admin","admin@gmail.com", passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
//        User manager = new User("manager","manager@gmail.com",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");
//
//        List<User> users=Arrays.asList(dan,admin,manager);
//        this.userRepository.saveAll(users);
//
//    }
//}
