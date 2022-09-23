package com.sempliq.springsec.controllers;

import com.sempliq.springsec.entities.User;
import com.sempliq.springsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @GetMapping("/")
    public String index()
    {

        return "index";

    }
    @GetMapping("/profile")
    public String profile(){

        return "profile";
    }

    @GetMapping("/admin")
    public String admin(){

        return "admin";
    }

    @GetMapping("/accessdenied")
    public String accessdenied(){

        return "403";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }



    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }
}
