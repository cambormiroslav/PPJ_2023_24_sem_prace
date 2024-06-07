package org.app.service;

import org.app.data.Fourteen_Days_Weather;
import org.app.data.Town;
import org.app.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TownService {

    @Autowired
    private TownRepository town_repository;

    public List<Town> getTowns() {
        return StreamSupport.stream(town_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void createOrUpdate(Town town){
        town_repository.save(town);
    }

    public Town getTownById(String id){
        Optional<Town> town = town_repository.findById(id);
        return town.get();
    }
}
