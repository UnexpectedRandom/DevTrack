package com.Mohammad.devtrack.controllers;

import org.springframework.web.bind.annotation.*;

import com.Mohammad.devtrack.service.ResourceService;
import com.Mohammad.devtrack.model.ResourceModel;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ResourceController{

    private final ResourceService resourceService;

    @PostMapping("/createResource")
    public ResourceModel createResource(@RequestBody ResourceModel param) {
        return resourceService.create(param);
    }
    

    @PutMapping("/updateResource")
    public ResourceModel updateResource(@RequestBody  ResourceModel param) {
        return resourceService.update(param);
    }


    @DeleteMapping("/deleteResource/{RID}")
    public void deleteResource(@PathVariable Long RID) {
       resourceService.delete(RID);
    }
    
    @GetMapping("/readResource/{UID}")
    public List<ResourceModel> readResource(@PathVariable Long UID) {
        return resourceService.findResourceById(UID);
    }
    
}
 