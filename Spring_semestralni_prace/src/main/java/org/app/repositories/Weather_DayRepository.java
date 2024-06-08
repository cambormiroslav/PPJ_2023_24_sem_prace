package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Weather_Day;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Weather_DayRepository extends CrudRepository<Weather_Day, CompositeKey> {
    @Query("SELECT W FROM Weather_Day as W WHERE town.name=:town_name")
    public List<Weather_Day> findByTownName(@Param("town_name") String town_name);
}
