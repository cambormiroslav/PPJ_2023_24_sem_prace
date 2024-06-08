package org.app.service;

import org.app.data.Weather_Current;
import org.app.data.Weather_Hourly;
import org.app.repositories.Weather_HourlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Weather_HourlyService {

    @Autowired
    private Weather_HourlyRepository weather_hourly_repository;

    public List<Weather_Hourly> getWeathersHourly() {
        return StreamSupport.stream(weather_hourly_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createOrUpdate(Weather_Hourly weather_hourly){
        weather_hourly_repository.save(weather_hourly);
    }

    public void delete(Weather_Hourly weather_hourly) {
        weather_hourly_repository.delete(weather_hourly);
    }

    public List<Weather_Hourly> getWeatherCurrentForTown(String town_name){
        return weather_hourly_repository.findByTownName(town_name);
    }
}
