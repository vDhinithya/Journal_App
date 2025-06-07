package com.dhinithya.journalApp.cache;

import com.dhinithya.journalApp.Repository.ConfigJournalAppRepository;
import com.dhinithya.journalApp.entity.ConfigJournalAppEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
   /*this postconstruct will call the data from mongodb and load that into the appCache hashmap that will be available for the use*/

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>(); //to reinitialise the init method, if we update the api then we need to reload the app to reflect the change so we are reinitializing here so that app reload won't require
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
