package org.app.service;

import org.app.data.Country;
import org.app.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CountryService {

    @Autowired
    private CountryRepository country_repository;

    public List<Country> getCountries() {
        return StreamSupport.stream(country_repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}