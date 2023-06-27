package org.satal.backservice.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final MaintenanceService maintenanceService;

    private final RoleService roleService;

    private final SpecialisationService specialisationService;
    private final UserService userService;

    private final ImplementService implementService;

    @PostMapping()
    public UserDto auth(@RequestBody AuthRequest authRequest){
        User user = userService.getUser(authRequest).orElse(null);

        // пока не подключено Security - не могу кидаться ошибками во фронт.
        // в проекте должно быть как-то так:
//        User user = userService.getUser(authRequest).orElseThrow(() -> new BadCredentialsException("Пользователь не найден"));
        assert user != null;

        return parseUsers(user);
    }

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

//        System.out.println("Controller >>>");
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
        System.out.printf(">>>>>>> role - %d, spec - %d, val - %s, page - %d\n", role, specialization, val, page);
        return userService.findAll(role, specialization, val, page).map(this::parseUsers);
    }

    @PostMapping("/role_list")
    public List<RoleDto> getAllRoles(){
        return roleService.findAll().stream().map(RoleDto::new).toList();
    }

    @PostMapping("/specialization_list")
    public List<SpecializationDto> getAllSpecializations(){
        return specialisationService.findAll().stream().map(SpecializationDto::new).toList();
    }


//    @PostConstruct
//    public void init(){
//        implementService.init();
//    }


}
