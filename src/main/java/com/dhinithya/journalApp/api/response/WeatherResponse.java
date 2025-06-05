package com.dhinithya.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current {
        private int temperature;
        @JsonProperty("weather_descriptions") // to change the json coming to pojo this process is DESERIALISING
        private List<String> weatherDescriptions;
        private int feelslike;
    }

}

