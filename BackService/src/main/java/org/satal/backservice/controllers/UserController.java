package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.exception.ResourceNotFoundException;
import org.satal.backservice.services.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final RoleService roleService;
    private final SpecializationService specializationService;
    private final UserService userService;

    @GetMapping("/schedule")
    public SpecializationDto getSchedule(@RequestParam Long id){
        Optional<Specialization> specializationOptional = specializationService.findById(id);
        return specializationOptional.map(SpecializationDto::new).orElseThrow(() -> new ResourceNotFoundException("Специализация с id:" + id + " не найдена."));
    }


}
