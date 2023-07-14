package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.ClubServiceDto;
import org.satal.backservice.services.MaintenanceService;
import org.satal.backservice.services.SpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fit")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FitnessController {
    private final MaintenanceService maintenanceService;

    private final SpecializationService specializationService;

    @PostMapping("/maintenance")
    public List<ClubServiceDto> getAllMaintenance(){
        return maintenanceService.findAll().stream().map(ClubServiceDto::new).toList();
    }



}
