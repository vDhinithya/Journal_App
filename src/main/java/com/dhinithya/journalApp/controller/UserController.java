
package com.dhinithya.journalApp.controller;
// controllers are the special types of classes/components that handles http requests
// controllers makes the end points and calls the  services
//
import com.dhinithya.journalApp.Repository.UserRepository;
import com.dhinithya.journalApp.entity.User;
import com.dhinithya.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
// this adds mapping to whole class, ie if we want to access /abc we will search for /journal/abc
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }

//    @PostMapping
//    public void createUser(@RequestBody User user){
//        userService.saveEntry(user);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser (@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
