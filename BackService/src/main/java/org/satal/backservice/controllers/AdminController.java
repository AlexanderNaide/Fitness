package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.ClientDto;
import org.satal.backservice.dto.users.UserDto;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.RoleService;
import org.satal.backservice.services.SpecializationService;
import org.satal.backservice.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RoleService roleService;
    private final SpecializationService specializationService;
    private final UserService userService;

    @GetMapping("/list")
    public Page<UserDto> getAllUsers(){
        Page<User> list = userService.findListUsers();
//        list.forEach(u -> System.out.println(u.getName()));
//        return userService.findAll(null, null, null, 1).map(ClientDto::new);
        return list.map(ClientDto::new);
    }

}
