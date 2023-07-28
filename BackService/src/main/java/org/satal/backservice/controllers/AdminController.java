package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.dto.users.ClientDto;
import org.satal.backservice.dto.users.SpecializationDto;
import org.satal.backservice.dto.users.UserDto;
import org.satal.backservice.dto.users.UserFullDto;
import org.satal.backservice.entities.users.Specialization;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.exception.ResourceNotFoundException;
import org.satal.backservice.services.RoleService;
import org.satal.backservice.services.SpecializationService;
import org.satal.backservice.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RoleService roleService;
    private final SpecializationService specializationService;
    private final UserService userService;

/*    @GetMapping("/list")
    public Page<UserDto> getAllUsers(){
        Page<User> list = userService.findListUsers();
//        list.forEach(u -> System.out.println(u.getName()));
//        return userService.findAll(null, null, null, 1).map(ClientDto::new);
        return list.map(ClientDto::new);
    }*/

    @PostMapping("/users")
    public Page<UserDto> upAllUsers(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String surname,
                                    @RequestParam(required = false) String login,
                                    @RequestParam(required = false) String phone,
                                    @RequestParam(required = false) String email
    ){
        if(page < 1){
            page = 1;
        }
        return userService.findListUsers(page, name, surname, login, phone, email).map(ClientDto::new);
    }

    @PostMapping("/user")
    public UserFullDto getProductById(@RequestParam Long id){
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(UserFullDto::new).orElseThrow(() -> new ResourceNotFoundException("id:" + id + " является ошибочным."));
    }
}
