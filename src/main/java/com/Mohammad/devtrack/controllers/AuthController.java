package com.Mohammad.devtrack.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.UserService;
import com.Mohammad.devtrack.model.UserModel;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> loginUsr(@Valid @RequestBody LoginRequest param, HttpSession session) {
        UserModel userModel = new UserModel();
        userModel.setEmail(param.email());
        userModel.setUHashedPassword(param.UHashedPassword());

        UserModel loggedInUser = userService.login(userModel);
        session.setAttribute("cookie-id", loggedInUser.getUID());
        return ResponseEntity.ok(loggedInUser);
    }

    @DeleteMapping
    public ResponseEntity<?> logoutUsr(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }

    public record LoginRequest(
            @NotBlank(message = "Email is required") @Email(message = "Email is not valid") String email,

            @NotBlank(message = "Password is required") String UHashedPassword) {
    }
}