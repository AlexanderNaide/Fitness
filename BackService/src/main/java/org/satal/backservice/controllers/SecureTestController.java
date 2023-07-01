package org.satal.backservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure_test")
public class SecureTestController {


    @GetMapping()
    @ResponseBody
    public String test(){
        return "Secure Hello";
    }

}
