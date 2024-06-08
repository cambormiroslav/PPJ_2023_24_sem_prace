package org.app.repositories;

import org.app.data.Fourteen_Days_Weather;
import org.app.data.Town;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends CrudRepository<Town, String> {
    @Query("SELECT T FROM Town as T WHERE name=:town_name")
    public List<Town> findByName(@Param("town_name") String town_name);
}
