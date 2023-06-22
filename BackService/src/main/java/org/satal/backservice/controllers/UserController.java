package org.satal.backservice.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.exception.AppError;
import org.satal.backservice.exception.BadCredentialsException;
import org.satal.backservice.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

        return switch (user.getRoles().get(0).getRole()){
            case "super" -> new SuperAdminDto(user, "super");
            case "admin" -> new AdminDto(user, "admin");
            case "trainer" -> new TrainerDto(user, "trainer");
            default -> new ClientDto(user, "user");
        };
    }

    @PostMapping("/list")
    public List<User> getAllUsers(){

        return userService.findAll();
    }

    @PostConstruct
    public void init(){
        implementService.init();
    }


}
