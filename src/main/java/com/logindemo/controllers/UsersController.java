package com.logindemo.controllers;

import com.logindemo.models.User;
import com.logindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserRepository user_repository;

    @GetMapping("/hello")
    public String allAccess() {
        return "hello";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsers() {
        return user_repository.findAll();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "user access";
    }



}
