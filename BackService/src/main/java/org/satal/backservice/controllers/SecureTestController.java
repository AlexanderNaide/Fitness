package org.satal.backservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/secure_test")
public class SecureTestController {


    @GetMapping()
    @ResponseBody
    public String test(Principal principal){
        System.out.println(principal.getName());
        System.out.println(principal.toString());
        return "Secure Hello " + principal.getName();
    }

}
