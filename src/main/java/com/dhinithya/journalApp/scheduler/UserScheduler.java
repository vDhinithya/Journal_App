package com.dhinithya.journalApp.scheduler;

import com.dhinithya.journalApp.Repository.UserRepository;
import com.dhinithya.journalApp.Repository.UserRepositoryImpl;
import com.dhinithya.journalApp.cache.AppCache;
import com.dhinithya.journalApp.entity.JournalEntry;
import com.dhinithya.journalApp.entity.Sentiment;
import com.dhinithya.journalApp.entity.User;
import com.dhinithya.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

//    @Autowired
//    private SentimentConsumerService sentimentConsumerService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0)+1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()){
                if (entry.getValue() > maxCount);
                maxCount = entry.getValue();
                mostFrequentSentiment = entry.getKey();
            }
            if(mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(), "Sentiments for last 7 days ", mostFrequentSentiment.toString());
            }

//            String entry = String.join(" ",filteredEntries) ;
//            String sentiment = sentimentConsumerService.getSentiment(entry);
//            emailService.sendEmail(user.getEmail(), "sentiments for last week", sentiment);
        }
    }
//    @Scheduled(cron = "0 0/10 * ? * *")
//    public void clearAppCache(){
//        appCache.init();
//    }


}
