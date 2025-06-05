package com.dhinithya.journalApp.service;

import com.dhinithya.journalApp.Repository.JournalEntryRepository;
import com.dhinithya.journalApp.Repository.UserRepository;
import com.dhinithya.journalApp.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest// to start application context so that autowire work
@Disabled
public class UserServiceTests {

//    @Test
//    public void testAdd() {
//        assertEquals(4, 2 + 2);
//        //assertEquals(4,2+1);
//    }
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,10,12",
//            "3,3,9"
//
//    }) // if we have an excel sheet, we may use @CsvFileSource
//
//    public void test(int a, int b, int expected){ // if we want to give multiple inputs/we don't want to hard code each data
//        assertEquals(expected, a + b);
//    }

    @Autowired
    private UserRepository userRepository;

    // @BeforeAll means this part will run before all of these test
    // @AfterAll after all test runs
    // @AfterEach runs after each test
    @BeforeEach // means before each test this part will run, can be used to initialization
    void setup(){
    }


    @Disabled // for now we dont want this test to run
    @Test
    public void findByUserName(){
       // this is what we have to find or say test userRepository.findByuserName("Ram");
        assertNotNull( userRepository.findByuserName("Ram")); //ctrl+D this is to test that userRepo se jo user call hoga wo null nahi hona chahiye
        // there was a null pointer exception because userRepo is null because spring application doesn't start yet so there will be nothing to autowired

        User user = userRepository.findByuserName("Ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Null",
//            "Caffe",
//            "EXE"
//    })
//    @Disabled
//    public void testFindByUserName(String name) {
//        assertNotNull(userRepository.findByuserName(name), "failed for: " + name);
//    }


}
