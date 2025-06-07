package com.dhinithya.journalApp.scheduler;

import com.dhinithya.journalApp.Repository.UserRepository;
import com.dhinithya.journalApp.Repository.UserRepositoryImpl;
import com.dhinithya.journalApp.cache.AppCache;
import com.dhinithya.journalApp.entity.JournalEntry;
import com.dhinithya.journalApp.entity.User;
import com.dhinithya.journalApp.service.EmailService;
import com.dhinithya.journalApp.service.SentimentConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentConsumerService sentimentConsumerService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList()); //map(x -> x.getSentiment()).
            String entry = String.join(" ",filteredEntries) ;
            String sentiment = sentimentConsumerService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "sentiments for last week", sentiment);
        }
    }
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache(){
        appCache.init();
    }


}
