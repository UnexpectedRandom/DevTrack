package com.Mohammad.devtrack.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.Mohammad.devtrack.service.UserService;
import com.Mohammad.devtrack.model.UserModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @DeleteMapping
    public ResponseEntity<?> deleteUser(HttpSession session) {
        Long currentUsrId = (Long) session.getAttribute("cookie-id");
        if (currentUsrId == null) {
            return ResponseEntity.status(401).body("Authentication Error: who are you?");
        }
        userService.delete(currentUsrId);
        session.invalidate();
        return ResponseEntity.ok("Deleted Account Successfuly");
    }

    @GetMapping
    public ResponseEntity<?> readUser(HttpSession session) {
        Long currentUsrId = (Long) session.getAttribute("cookie-id");
        if (currentUsrId == null) {
            return ResponseEntity.status(401).body("You can't view this user");
        }

        return ResponseEntity.ok().body(userService.findUsr(currentUsrId));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserModel um, HttpSession session) {

        Long currentUsrId = (Long) session.getAttribute("cookie-id");

        if (currentUsrId == null) {
            return ResponseEntity.status(401).body("Authentication Error: Who are you?");
        }

        if (!currentUsrId.equals(um.getUID())){
            return ResponseEntity.status(401).body("");
        }
        userService.update(um);

        return ResponseEntity.ok("Changed profile");
    }

    
    @PostMapping
    public ResponseEntity<?> registerUsr(@Valid @RequestBody UserModel param) {
        return ResponseEntity.ok(userService.create(param));
    }

}
