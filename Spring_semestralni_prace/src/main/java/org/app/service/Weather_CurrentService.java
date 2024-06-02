package org.app.service;

import org.app.data.Town;
import org.app.data.Weather_Current;
import org.app.repositories.Weather_CurrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Weather_CurrentService {

    @Autowired
    private Weather_CurrentRepository weather_current_repository;

    public List<Weather_Current> getWeathersCurrent() {
        return StreamSupport.stream(weather_current_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
