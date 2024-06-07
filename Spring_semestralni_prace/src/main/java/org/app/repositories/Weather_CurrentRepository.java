package org.app.repositories;

import org.app.data.CompositeKey;
import org.app.data.Town;
import org.app.data.Weather_Current;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface Weather_CurrentRepository extends CrudRepository<Weather_Current, CompositeKey> {
}
