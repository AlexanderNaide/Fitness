package org.satal.backservice.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

        return switch (user.getRole().getTitleRole()){
            case "super" -> new SuperAdminDto(user);
            case "admin" -> new AdminDto(user);
            case "trainer" -> new TrainerDto(user);
            default -> new ClientDto(user);
        };
    }

    @PostMapping("/list")
    public Page<User> getAllUsers(){
        return userService.findAll(null, null, null, 1).map(productConverter::entityToDto);
    }

    @PostConstruct
    public void init(){
        implementService.init();
    }


}
