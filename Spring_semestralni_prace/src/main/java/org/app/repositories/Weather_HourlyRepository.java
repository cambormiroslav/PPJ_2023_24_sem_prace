package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Weather_Hourly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface Weather_HourlyRepository extends CrudRepository<Weather_Hourly, CompositeKey> {
}
