package com.Mohammad.devtrack.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.ResourceService;
import com.Mohammad.devtrack.model.ResourceModel;

import lombok.RequiredArgsConstructor;

//import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<?> createResource(@Valid @RequestBody ResourceModel param, HttpSession session) {
        Long currentUsrId = (Long) session.getAttribute("cookie-id");

        if (currentUsrId == null) {
            return ResponseEntity.status(401).body("Authentication Error: Who are you?");
        }

        resourceService.create(param, currentUsrId);
        return ResponseEntity.ok("Successfully Created Resource");
    }

    @PutMapping
    public ResponseEntity<?> updateResource(@Valid @RequestBody ResourceModel param, HttpSession session) {

        Long currentUsrId = (Long) session.getAttribute("cookie-id");

        if(currentUsrId == null){
            return ResponseEntity.status(401).body("Authentication Error: who are you?");
        }
        
        resourceService.update(param, currentUsrId);
        
        return ResponseEntity.ok("Saved Changes");
    }

    @DeleteMapping("/{RID}")
    public ResponseEntity<?> deleteResource(@PathVariable Long RID, HttpSession session) {
        Long currentUsrId = (Long) session.getAttribute("cookie-id");

        if(currentUsrId == null){
            return ResponseEntity.status(401).body("Authentication Error: who are you?");
        }

        resourceService.delete(RID, currentUsrId);

        return ResponseEntity.ok("Successfully deleted resource");
    }

    @GetMapping
    public ResponseEntity<?> readResource(HttpSession session) {
        
        Long currentUsrId = (Long) session.getAttribute("cookie-id");

        if (currentUsrId==null) {
            return ResponseEntity.status(401).body("Authentication Error: Who are you?");
        }


        return ResponseEntity.ok(resourceService.findResourceById(currentUsrId));
    }
}
