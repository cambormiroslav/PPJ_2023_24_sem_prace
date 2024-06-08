package org.app.repositories;

import org.app.data.Country;
import org.app.data.Weather_Current;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    @Query("SELECT C FROM Country as C WHERE shortcut=:shortcut")
    public List<Country> findByCountryShortcut(@Param("shortcut") String shortcut);
}
