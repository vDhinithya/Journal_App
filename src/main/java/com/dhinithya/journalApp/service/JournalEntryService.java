package com.dhinithya.journalApp.service;

// here we will write our business logic and will be used in controller


import com.dhinithya.journalApp.Repository.JournalEntryRepository;
import com.dhinithya.journalApp.entity.JournalEntry;
import com.dhinithya.journalApp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

//    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class); // this is used to set up the logging into the application,
//    every logger in associated with a class so class name is provided and made that private static final to avoid duplicate
//
//    @Transactional
//   public void saveEntry(JournalEntry journalEntry, String userName) {
//        try {
//           User user = userService.findByUserName(userName);
//            journalEntry.setDate(LocalDateTime.now());
//            JournalEntry saved = journalEntryRepository.save(journalEntry); // this will give the saved entry from out database
//            user.getJournalEntries().add(saved);
//            userService.saveUser(user);
//        } catch (Exception e) {
//            logger.info("using ");
//            throw new RuntimeException("An error occured while saving the entry.", e);
//        }
//
//    }


    @Transactional // this two operations of getting user and then saving journal to particular user is acheived using this annotation, is either user or entry not found, no entry will be added in database
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry); // this will give the saved entry from out database
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry.", e);
        }

     }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));// checks if the id fetched is correct
            if (removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry",e);
        }
        return removed;

    }



}