package org.app.service;

import org.app.data.Fourteen_Days_Weather;
import org.app.data.Seven_Days_Weather;
import org.app.repositories.Fourteen_Days_WeatherRepository;
import org.app.repositories.Seven_Days_WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Seven_Days_WeatherService {
    @Autowired
    private Seven_Days_WeatherRepository seven_days_weather_repository;

    public List<Seven_Days_Weather> getAllSevenDaysWeather() {
        return StreamSupport.stream(seven_days_weather_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createOrUpdate(Seven_Days_Weather seven_days_weather){
        seven_days_weather_repository.save(seven_days_weather);
    }

    public void delete(Seven_Days_Weather seven_days_weather) {
        seven_days_weather_repository.delete(seven_days_weather);
    }

    public List<Seven_Days_Weather> getWeatherForTown(String town_name){
        return seven_days_weather_repository.findByTownName(town_name);
    }
}
