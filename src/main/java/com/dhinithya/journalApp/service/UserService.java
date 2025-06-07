package com.dhinithya.journalApp.service;

import com.dhinithya.journalApp.Repository.UserRepository;
import com.dhinithya.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

 //here we will write our business logic and will be ued in controller
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository; // dependency injection
    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

// instead if this @Slf4j can be used    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

//    public void saveNewUser(User user) {
//        try {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            user.setRoles(Arrays.asList("USER"));
//            userRepository.save(user);
//        } catch (Exception e) {
//            log.error("Exception ", e);
//
//        }
//
//    }

    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            //log.error("hahahhahhahahahah");
            //log.warn("hahahhahhahahahah");
            //log.info("hahahhahhahahahah");
            log.error("Error occured for {} :", user.getUserName(), e);
            //log.debug("hahahhahhahahahah");
            //log.trace("hahahhahhahahahah");
            return false;
        }
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

//    public void saveNewUser(User user) {
//        String raw = user.getPassword();
//        String encoded = passwordEncoder.encode(raw);
//        System.out.println("raw" + raw);
//        System.out.println("encoded"+encoded);
//        //user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode before saving
//        user.setPassword(encoded);
//        user.setRoles(Arrays.asList("USER"));
//        userRepository.save(user); // Save after encoding
//    }


    public void saveUser (User user){
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {

        return userRepository.findByuserName(userName);
    }
}
