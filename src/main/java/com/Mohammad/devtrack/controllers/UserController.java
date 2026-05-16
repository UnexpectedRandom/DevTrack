package com.Mohammad.devtrack.controllers;


import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.UserService;
import com.Mohammad.devtrack.model.UserModel;

import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    
    private final UserService userService;

    @PostMapping("/createUser")
    public UserModel createUsr(@RequestBody UserModel param) {
        return userService.create(param);
    }

    @DeleteMapping("/deleteUser/{usrId}")
    public void deleteUser(@PathVariable Long usrId) {
        userService.delete(usrId);
    }

    @GetMapping("/findUser/{usrId}")
    public Optional<UserModel> readUser(@PathVariable Long usrId) {
        return userService.findUsr(usrId);
    }

    @PutMapping("/updateUser/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.update(id, user);
    }


}
