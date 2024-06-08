package org.app.service;

import org.app.data.Weather_Day;
import org.app.repositories.Weather_DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Weather_DayService {

    @Autowired
    private Weather_DayRepository weather_hourly_repository;

    public List<Weather_Day> getAllDayWeathers() {
        return StreamSupport.stream(weather_hourly_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createOrUpdate(Weather_Day weather_hourly){
        weather_hourly_repository.save(weather_hourly);
    }

    public void delete(Weather_Day weather_hourly) {
        weather_hourly_repository.delete(weather_hourly);
    }

    public List<Weather_Day> getWeatherForTown(String town_name){
        return weather_hourly_repository.findByTownName(town_name);
    }
}
