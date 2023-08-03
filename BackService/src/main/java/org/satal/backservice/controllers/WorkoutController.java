package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.UserDto;
import org.satal.backservice.dto.worcouts.WorkoutFullDto;
import org.satal.backservice.dto.worcouts.WorkoutGridDto;
import org.satal.backservice.services.RoleService;
import org.satal.backservice.services.SpecializationService;
import org.satal.backservice.services.UserService;
import org.satal.backservice.services.WorkoutService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

//    @GetMapping("/week")
//    public List<WorkoutGridDto> upAllUsers(@RequestParam(required = false, defaultValue = "0") Integer delta, Principal principal){
//        System.out.println(principal.getName());
//        return workoutService.findList().stream().map(WorkoutGridDto::new).toList();
//    }



//    @GetMapping("/week")
//    public List<WorkoutGridDto> upAllUsers(@RequestParam(required = false, defaultValue = "0") Integer delta, Principal principal){
//        return workoutService.findCurrentWeek(delta, principal.getName()).stream().map(WorkoutGridDto::new).toList();
//    }

    @GetMapping("/week")
    public WorkoutFullDto upAllUsers(@RequestParam(required = false, defaultValue = "0") Integer delta, Principal principal){
        return new WorkoutFullDto(delta, workoutService.findCurrentWeek(delta, principal.getName()));
    }



}
