package org.satal.backservice.repositories;

import org.satal.backservice.entities.gridClasses.Maintenance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {
}
