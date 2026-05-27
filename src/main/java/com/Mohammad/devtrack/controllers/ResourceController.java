package com.Mohammad.devtrack.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.ResourceService;
import com.Mohammad.devtrack.model.ResourceModel;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResourceModel createResource(@Valid @RequestBody ResourceModel param) {
        return resourceService.create(param);
    }

    @PutMapping
    public ResourceModel updateResource(@Valid @RequestBody ResourceModel param) {
        return resourceService.update(param);
    }

    @DeleteMapping("/{RID}")
    public void deleteResource(@PathVariable Long RID) {
        resourceService.delete(RID);
    }

    @GetMapping("/user/{UID}")
    public List<ResourceModel> readResource(@PathVariable Long UID) {
        return resourceService.findResourceById(UID);
    }

}
