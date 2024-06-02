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
}
