package org.satal.backservice.services;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.entities.gridClasses.ClubService;
import org.satal.backservice.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    public void save(ClubService maintenance){
        maintenanceRepository.save(maintenance);
    }

    public Optional<ClubService> findById(Long id){
        return maintenanceRepository.findById(id);
    }

    public List<ClubService> findAll(){
        return (List<ClubService>) maintenanceRepository.findAll();
    }
}
