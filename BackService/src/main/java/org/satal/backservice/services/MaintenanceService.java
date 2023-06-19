package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.Maintenance;
import org.satal.backservice.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    public List<Maintenance> findAll(){
        return maintenanceRepository.findAll();
    }
}
