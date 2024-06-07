package org.app.service;

import org.app.data.Town;
import org.app.data.Weather_Current;
import org.app.repositories.Weather_CurrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Weather_CurrentService {

    @Autowired
    private Weather_CurrentRepository weather_current_repository;

    public List<Weather_Current> getWeathersCurrent() {
        return StreamSupport.stream(weather_current_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createOrUpdate(Weather_Current weather_current){
        weather_current_repository.save(weather_current);
    }

    public void delete(Weather_Current weather_current) {
        weather_current_repository.delete(weather_current);
    }
}
