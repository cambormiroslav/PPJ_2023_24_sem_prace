package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Fourteen_Days_Weather;
import org.app.data.Seven_Days_Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Seven_Days_WeatherRepository extends CrudRepository<Seven_Days_Weather, CompositeKey> {
    @Query("SELECT F FROM Seven_Days_Weather as F WHERE town.name=:town_name")
    public List<Seven_Days_Weather> findByTownName(@Param("town_name") String town_name);
}
