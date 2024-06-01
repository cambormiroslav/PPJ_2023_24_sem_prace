package org.app.repositories;

import org.app.data.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends CrudRepository<Town, String> {
}
