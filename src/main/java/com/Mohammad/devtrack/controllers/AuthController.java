package com.Mohammad.devtrack.controllers;

import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<?> loginUsr(@Valid @RequestBody UserModel param, HttpSession session) {
        UserModel loggedInUser = userService.login(param);
        session.setAttribute("cookie-id", loggedInUser.getUID());
        return ResponseEntity.ok(session);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUsr(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

}