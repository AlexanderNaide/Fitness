package org.satal.backservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.satal.backservice.api.AuthRequest;
import org.satal.backservice.api.AuthResponse;
import org.satal.backservice.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/token")
    public AuthResponse token(@RequestBody AuthRequest request){
//        log.info("Request from: {}", request.getUsername());
        System.out.printf("Request from: {%s}", request.getLogin());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

//    @PostMapping("/registrations")
//    public AuthResponse register(@RequestBody AuthRequest request){
//        log.info("Request registration from: {}", request.getUsername());
//        if(userService.findByUsername(request.getUsername()).isPresent()){
//            throw new BadCredentialsException("Пользователь c таким логином уже существует.");
//        } else {
//            User user = new User();
//            user.setUsername(request.getUsername());
//            user.setPassword(passwordEncoder.encode(request.getPassword()));
//            user.setRoles(Collections.singletonList(roleService.findByRole("ROLE_USER").orElse(null)));
//            userService.save(user);
//        }
//        return token(request);
//    }

}
