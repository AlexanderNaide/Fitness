package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.services.MaintenanceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FitnessController {
    private final MaintenanceService maintenanceService;

    @PostMapping("/maintenance")
    public List<MaintenanceDto> gatAllMaintenance(){
        return maintenanceService.findAll().stream().map(MaintenanceDto::new).toList();
    }


}
