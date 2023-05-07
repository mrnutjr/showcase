package com.example.showcase.controller;

import com.example.showcase.model.LoginRequest;
import com.example.showcase.model.LoginResponse;
import com.example.showcase.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return loginService.login(loginRequest);
    }

    @GetMapping("/logout")
    ResponseEntity<String> logout(@RequestHeader("cookie") String token) {
        loginService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
