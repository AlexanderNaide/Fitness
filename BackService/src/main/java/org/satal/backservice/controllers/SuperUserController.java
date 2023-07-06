package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.api.AuthRequest;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/super")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class SuperUserController {

    private final MaintenanceService maintenanceService;

    private final RoleService roleService;

    private final SpecializationService specializationService;
    private final UserService userService;
    private final ImplementService implementService;

    private UserDto parseUsers(User user){
        return switch (user.getRole().getTitleRole()){
            case "super" -> new SuperAdminDto(user);
            case "admin" -> new AdminDto(user);
            case "trainer" -> new TrainerDto(user);
            default -> new ClientDto(user);
        };
    }

    @GetMapping("/list")
    public Page<UserDto> getAllUsers(){
        return userService.findAll(null, null, null, 1).map(this::parseUsers);
    }

    @PostMapping("/updates")
    public Page<UserDto> upAllUsers(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false) Long role,
                                    @RequestParam(required = false) Long specialization,
                                    @RequestParam(required = false) String val
    ){
        if(page < 1){
            page = 1;
        }
        return userService.findAll(role, specialization, val, page).map(this::parseUsers);
    }

    @PostMapping("/role_list")
    public List<RoleDto> getAllRoles(){
        return roleService.findAll().stream().map(RoleDto::new).toList();
    }

    @PostMapping("/specialization_list")
    public List<SpecializationDto> getAllSpecializations(){
        return specializationService.findList().stream().map(SpecializationDto::new).toList();
    }

    @PostMapping("/spec_list")
    public Page<SpecializationDto> getAllSpecializations(@RequestParam(required = false, defaultValue = "1") Integer page){
        if(page < 1){
            page = 1;
        }
        return specializationService.findAll(page).map(SpecializationDto::new);
    }

    @PostMapping("/spec_one")
    public SpecializationDto getProductById(@RequestParam Long id){
        Optional<Specialization> specializationOptional = specializationService.findById(id);
        return specializationOptional.map(SpecializationDto::new).orElseGet(() -> new SpecializationDto(null, "Специализация с id:" + id + " не найдена."));
    }

//    @PostConstruct
//    public void init(){
//        implementService.init();
//    }

}
