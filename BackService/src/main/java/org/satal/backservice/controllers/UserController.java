package org.satal.backservice.controllers;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.MaintenanceDto;
import org.satal.backservice.dto.users.*;
import org.satal.backservice.entities.users.AuthRequest;
import org.satal.backservice.entities.users.Role;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.MaintenanceService;
import org.satal.backservice.services.RoleService;
import org.satal.backservice.services.SpecialisationService;
import org.satal.backservice.services.UserService;
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

    @PostMapping()
    public UserDto auth(@RequestBody AuthRequest authRequest){
        System.out.println("Пришло: " + authRequest.getLogin() + " " + authRequest.getPassword());
        User user = userService.getUser(authRequest).orElse(null);
        assert user != null;
        return switch (user.getRoles().get(0).getRole()){
            case "super" -> new SuperAdminDto(user);
            case "admin" -> new AdminDto(user);
            case "trainer" -> new TrainerDto(user);
            case "user" -> new ClientDto(user);
            default -> null;
        };
    }

//    @PostConstruct
//    public void init(){
//        Role superUserRole = new Role();
//        superUserRole.setRole("super");
//        roleService.save(superUserRole);
//
//        Role adminRole = new Role();
//        adminRole.setRole("admin");
//        roleService.save(adminRole);
//
//        Role trainerRole = new Role();
//        trainerRole.setRole("trainer");
//        roleService.save(trainerRole);
//
//        Role userRole = new Role();
//        userRole.setRole("user");
//        roleService.save(userRole);
//
//        Specialization functional = new Specialization();
//        functional.setSpecialization("functional");
//        specialisationService.save(functional);
//
//        Specialization water = new Specialization();
//        water.setSpecialization("water");
//        specialisationService.save(water);
//
//        Specialization group = new Specialization();
//        group.setSpecialization("group");
//        specialisationService.save(group);
//
//        Specialization kids = new Specialization();
//        kids.setSpecialization("kids");
//        specialisationService.save(kids);
//
//        Specialization massage = new Specialization();
//        massage.setSpecialization("massage");
//        specialisationService.save(massage);
//
//        User superUser = new User();
//        superUser.setLogin("super");
//        superUser.setPassword("123");
//        superUser.setRoles(List.of(superUserRole));
//        superUser.setName("Super");
//        superUser.setSurname("Admin");
//        userService.save(superUser);
//
//        User trainer1 = new User();
//        trainer1.setLogin("tr1");
//        trainer1.setPassword("123");
//        trainer1.setRoles(List.of(trainerRole));
//        trainer1.setName("Trainer1");
//        trainer1.setSurname("***");
//        trainer1.setSpecialization(functional);
//        userService.save(trainer1);
//
//        User trainer2 = new User();
//        trainer2.setLogin("tr2");
//        trainer2.setPassword("123");
//        trainer2.setRoles(List.of(trainerRole));
//        trainer2.setName("Trainer2");
//        trainer2.setSurname("***");
//        trainer2.setSpecialization(water);
//        userService.save(trainer2);
//
//        User admin = new User();
//        admin.setLogin("admin");
//        admin.setPassword("123");
//        admin.setRoles(List.of(adminRole));
//        admin.setName("Admin");
//        admin.setSurname("***");
//        userService.save(admin);
//
//        User user = new User();
//        user.setLogin("user");
//        user.setPassword("123");
//        user.setRoles(List.of(userRole));
//        user.setName("User");
//        user.setSurname("***");
//        userService.save(user);
//
//    }


}
