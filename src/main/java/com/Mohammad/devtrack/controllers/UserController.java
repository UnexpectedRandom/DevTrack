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

    @DeleteMapping("/delete-profile")
    public ResponseEntity<?> deleteUser(HttpSession session) {
        Long currentUsrId = (Long) session.getAttribute("cookie-id");
        if (currentUsrId == null) {
            return ResponseEntity.status(403).body("Forbidden: You can only delete your own account");
        }
        userService.delete(currentUsrId);
        return ResponseEntity.ok("Deleted Account Successfuly");
    }

    @GetMapping("/view-profile")
    public ResponseEntity<?> readUser(HttpSession session) {
        Long currentUsrId = (Long) session.getAttribute("cookie-id");
        if (currentUsrId == null) {
            return ResponseEntity.status(401).body("You can't view this user");
        }

        return ResponseEntity.ok().body(userService.findUsr(currentUsrId));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateUser(@RequestBody customDto customDTO, HttpSession session) {

        Long currentUsrId = (Long) session.getAttribute("cookie-id");

        if (currentUsrId == null) {
            return ResponseEntity.status(401).body("Can't update this user's profile");
        }

        userService.update(customDTO.id(), customDTO.user());

        return ResponseEntity.status(301).body("Changed profile");
    }

    public record customDto(Long id, UserModel user) {
    };

}
