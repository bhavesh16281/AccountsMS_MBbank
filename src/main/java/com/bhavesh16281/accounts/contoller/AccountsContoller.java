package com.bhavesh16281.accounts.contoller;

import com.bhavesh16281.accounts.constants.AccountsConstants;
import com.bhavesh16281.accounts.dto.CustomerDTO;
import com.bhavesh16281.accounts.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsContoller {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Bhavesh!";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO  customerDTO){


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
