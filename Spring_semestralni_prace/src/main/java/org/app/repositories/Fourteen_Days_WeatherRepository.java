package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Fourteen_Days_Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface Fourteen_Days_WeatherRepository extends CrudRepository<Fourteen_Days_Weather, CompositeKey> {
}
