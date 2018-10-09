package com.example.web3jservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class AccountController {

    @PostMapping("/accounts/balance")
    @ApiOperation(value = "Get Ether Balance for a single Address")
    public ResponseEntity<String> getAddress(String address){
        return ResponseEntity.ok(address);
    }
}
