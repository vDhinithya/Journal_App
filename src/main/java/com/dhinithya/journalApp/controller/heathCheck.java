package com.dhinithya.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class heathCheck {
    @GetMapping("health-check")
    public String HealthCheck (){
        return "your spring boot project is running ";
    }
}
