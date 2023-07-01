package org.satal.backservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.satal.backservice.api.AuthRequest;
import org.satal.backservice.api.AuthResponse;
import org.satal.backservice.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping()
    @ResponseBody
    public String test(){
        return "Hello";
    }

    @GetMapping("/secure")
    @ResponseBody
    public String secureTest(){
        return "Secure Hello";
    }

}
