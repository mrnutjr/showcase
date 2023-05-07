package com.example.showcase.controller;

import com.example.showcase.entity.Person;
import com.example.showcase.repository.PersonRepository;
import com.example.showcase.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    public class PersonController {

        private final PersonRepository repository;
        private final LoginService loginService;

        PersonController(PersonRepository repository, LoginService loginService) {
            this.repository = repository;
            this.loginService = loginService;
        }

    @GetMapping("/persons")
    ResponseEntity<List<Person>> getAll(@RequestHeader("cookie") String token) {
        loginService.checkToken(token);
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}
