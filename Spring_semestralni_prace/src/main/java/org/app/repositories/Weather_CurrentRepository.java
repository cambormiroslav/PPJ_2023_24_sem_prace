package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Town;
import org.app.data.Weather_Current;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Weather_CurrentRepository extends CrudRepository<Weather_Current, CompositeKey> {
    @Query("SELECT W FROM Weather_Current as W WHERE town.name=:town_name")
    public List<Weather_Current> findByTownName(@Param("town_name") String town_name);
}
