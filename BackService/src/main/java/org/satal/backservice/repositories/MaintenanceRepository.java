package org.satal.backservice.repositories;

import org.satal.backservice.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository <Maintenance, Long> {
}
