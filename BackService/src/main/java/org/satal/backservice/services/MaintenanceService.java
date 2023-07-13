package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.gridClasses.Maintenance;
import org.satal.backservice.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    public void save(Maintenance maintenance){
        maintenanceRepository.save(maintenance);
    }

    public Optional<Maintenance> findById(Long id){
        return maintenanceRepository.findById(id);
    }

    public List<Maintenance> findAll(){
        return (List<Maintenance>) maintenanceRepository.findAll();
    }
}
