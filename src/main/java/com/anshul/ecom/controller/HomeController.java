package com.anshul.ecom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    
    
    @GetMapping("/home")
    public String handleWelcome() {
        return "<h1>home</h1>";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "<h1>home_admin</h1>";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "<h1>home_user</h1>";
    }
}   
