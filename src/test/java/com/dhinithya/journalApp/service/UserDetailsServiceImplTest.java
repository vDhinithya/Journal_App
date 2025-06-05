package com.dhinithya.journalApp.service;

import com.dhinithya.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Disabled
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Disabled
    @Test
    void loadUserByUsername (){

//        when(userRepository.findByuserName(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Caffe").password("Caffe").roles(new ArrayList<>()).build());
//        userDetailsService.loadUserByUsername("Caffe");
//        Assertions.assertNotNull(user);
    }
}
