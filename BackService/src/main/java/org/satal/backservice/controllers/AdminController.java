package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import org.satal.backservice.services.RoleService;
import org.satal.backservice.services.SpecializationService;
import org.satal.backservice.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RoleService roleService;
    private final SpecializationService specializationService;
    private final UserService userService;

}
