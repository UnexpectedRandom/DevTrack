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

    @DeleteMapping("/{usrId}")
    public void deleteUser(@PathVariable Long usrId) {
        userService.delete(usrId);
    }

    @GetMapping("/{usrId}")
    public UserModel readUser(@PathVariable Long usrId) {
        Optional<UserModel> findusrId = userService.findUsr(usrId);
        if (findusrId.isEmpty()) {
            return null;
        }
        return findusrId.get();
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @Valid @RequestBody UserModel user) {
        return userService.update(id, user);
    }

}
