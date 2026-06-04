package com.Mohammad.devtrack.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.UserService;
import com.Mohammad.devtrack.model.UserModel;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserModel registerUsr(@Valid @RequestBody UserModel param) {
        return userService.create(param);
    }

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody UserModel param) {
        UserModel loggedInUser = userService.login(param);
        return ResponseEntity.ok(loggedInUser);
    }

}