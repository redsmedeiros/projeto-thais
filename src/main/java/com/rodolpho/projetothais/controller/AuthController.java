package com.rodolpho.projetothais.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodolpho.projetothais.payload.LoginDto;
import com.rodolpho.projetothais.payload.RegisterDto;
import com.rodolpho.projetothais.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService; 

    public AuthController(AuthService authService){

        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        String response = authService.login(loginDto);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }


    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        String response = authService.register(registerDto);

        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }
    
}
