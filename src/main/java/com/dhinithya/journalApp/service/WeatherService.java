package com.dhinithya.journalApp.service;

import com.dhinithya.journalApp.api.response.WeatherResponse;
import com.dhinithya.journalApp.cache.AppCache;
import com.dhinithya.journalApp.constants.Placeholders;
import com.dhinithya.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

//    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";      now taking from mongodb's collection

    @Autowired
    private RestTemplate restTemplate;// used to hit the api call

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){

        WeatherResponse weatherResponse = redisService.get("weather of " + city, WeatherResponse.class); // the logic here used is, if the data is present in the cache/ redis, then get it from redis else get the data from the api and save to redis if not present
        if(weatherResponse != null){
            return weatherResponse;
        }else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key","value");
//        User user = User.builder().userName("KNOX").password("welcome")build()
//        HttpEntity<String> httpEntity = new HttpEntity<>(user, httpHeaders);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null){
                redisService.set("weather of " + city, body, 300l);
            }
            return body;
        }

    }
}
