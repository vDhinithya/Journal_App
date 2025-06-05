package com.dhinithya.journalApp.controller;

import com.dhinithya.journalApp.entity.User;
import com.dhinithya.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/health-check") // this function is mapped with health-check, when we will hit this health-check at our local host it will run the function
    public String healthCheck(){
        return "ok";
    }

    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
