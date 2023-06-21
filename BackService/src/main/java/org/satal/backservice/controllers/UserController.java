package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.services.MaintenanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final MaintenanceService maintenanceService;

    @PostMapping()
    public void auth(@RequestBody AuthRequest authRequest){
        System.out.println("Пришло: " + authRequest.getLogin() + " " + authRequest.getPassword());
    }


}
