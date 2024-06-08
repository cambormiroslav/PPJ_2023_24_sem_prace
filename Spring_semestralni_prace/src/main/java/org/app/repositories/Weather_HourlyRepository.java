package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Weather_Current;
import org.app.data.Weather_Hourly;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Weather_HourlyRepository extends CrudRepository<Weather_Hourly, CompositeKey> {
    @Query("SELECT W FROM Weather_Hourly as W WHERE town.name=:town_name")
    public List<Weather_Hourly> findByTownName(@Param("town_name") String town_name);
}
