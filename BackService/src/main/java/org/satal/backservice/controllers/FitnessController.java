package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.dto.users.SpecializationDto;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.services.MaintenanceService;
import org.satal.backservice.services.SpecializationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fit")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FitnessController {
    private final MaintenanceService maintenanceService;

    private final SpecializationService specializationService;

    @PostMapping("/maintenance")
    public List<MaintenanceDto> getAllMaintenance(){
        return maintenanceService.findAll().stream().map(MaintenanceDto::new).toList();
    }



}
