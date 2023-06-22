package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.dto.users.SuperAdminDto;
import org.satal.backservice.dto.users.TestUserDto;
import org.satal.backservice.dto.users.UserDto;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.MaintenanceService;
import org.satal.backservice.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FitnessController {
    private final MaintenanceService maintenanceService;

    @PostMapping("/maintenance")
    public List<MaintenanceDto> getAllMaintenance(){
        return maintenanceService.findAll().stream().map(MaintenanceDto::new).toList();
    }

}
