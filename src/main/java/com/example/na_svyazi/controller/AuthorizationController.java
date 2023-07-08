package com.example.na_svyazi.controller;


import com.example.na_svyazi.entity.User;
import com.example.na_svyazi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String userRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String userCreate(@RequestParam("file") MultipartFile file, User user) {
        userService.createUser(file, user);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String mainPage(){
        return "redirect:/publication/page";
    }
}
