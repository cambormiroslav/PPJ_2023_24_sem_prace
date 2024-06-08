package org.app.service;

import org.app.data.Fourteen_Days_Weather;
import org.app.repositories.Fourteen_Days_WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Fourteen_Days_WeatherService {

    @Autowired
    private Fourteen_Days_WeatherRepository fourteen_days_weather_repository;

    public List<Fourteen_Days_Weather> getAllFourteenDaysWeather() {
        return StreamSupport.stream(fourteen_days_weather_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createOrUpdate(Fourteen_Days_Weather fourteen_days_weather){
        fourteen_days_weather_repository.save(fourteen_days_weather);
    }

    public void delete(Fourteen_Days_Weather fourteen_days_weather) {
        fourteen_days_weather_repository.delete(fourteen_days_weather);
    }

    public List<Fourteen_Days_Weather> getWeatherForTown(String town_name){
        return fourteen_days_weather_repository.findByTownName(town_name);
    }
}
