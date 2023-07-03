package org.satal.backservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.satal.backservice.api.AuthRequest;
import org.satal.backservice.api.AuthResponse;
import org.satal.backservice.entities.users.User;
import org.satal.backservice.services.JwtService;
import org.satal.backservice.services.RoleService;
import org.satal.backservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtService jwtService;

    @PostMapping()
    public AuthResponse token(@RequestBody AuthRequest request){
        log.info("Request from: {}", request.getLogin());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
//        User u = userService.findByLogin(request.getLogin()).orElse(null);
//        System.out.println(userService.findByLogin(request.getLogin()).get());
//        return new AuthResponse(token, userService.findByLogin(request.getLogin()));
        return new AuthResponse(token, userService.findByLogin(request.getLogin()).orElse(null));
    }

    @PostMapping("/reg")
    public AuthResponse register(@RequestBody AuthRequest request){
        log.info("Request registration from: {}", request.getLogin());
        if(userService.findByLogin(request.getLogin()).isPresent()){
            throw new BadCredentialsException("Пользователь c таким логином уже существует.");
        } else {
            User user = new User();
            user.setLogin(request.getLogin());
//            user.setName("User_" + (userService.findCount() + 1));
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(roleService.findByTitle("user").orElse(null));
            userService.save(user);
        }
        return token(request);
    }

}
