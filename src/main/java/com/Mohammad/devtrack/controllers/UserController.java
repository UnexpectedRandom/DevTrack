package com.Mohammad.devtrack.controllers;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.UserService;
import com.Mohammad.devtrack.model.UserModel;

import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    private final UserService userService;

    @PostMapping
    public UserModel createUsr(@Valid @RequestBody UserModel param) {
        return userService.create(param);
    }

    @DeleteMapping("/{usrId}")
    public void deleteUser(@PathVariable Long usrId) {
        userService.delete(usrId);
    }

    @GetMapping("/{usrId}")
    public Optional<UserModel> readUser(@PathVariable Long usrId) {
        return userService.findUsr(usrId);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @Valid @RequestBody UserModel user) {
        return userService.update(id, user);
    }


}
