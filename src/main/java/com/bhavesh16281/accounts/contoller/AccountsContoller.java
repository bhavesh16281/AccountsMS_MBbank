package com.bhavesh16281.accounts.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsContoller {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Bhavesh!";
    }
}
