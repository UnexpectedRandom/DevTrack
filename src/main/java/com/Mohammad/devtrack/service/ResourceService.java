package com.Mohammad.devtrack.service;

import com.Mohammad.devtrack.model.ResourceModel;
import com.Mohammad.devtrack.repository.ResourceRepository;
import com.Mohammad.devtrack.exceptions.ResourceExceptions;
import com.Mohammad.devtrack.exceptions.ValidationExceptions;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {
    
    private final ResourceRepository resourceRepository;

    @Transactional(readOnly = true)
    public List<ResourceModel> findResourceById(Long UID) {

        return resourceRepository.findByUID_UID(UID);
    
    }

    @Transactional
    public void delete(Long RID) {

        if (!resourceRepository.existsById(RID)) {
            throw new ResourceExceptions.ResourceNotFoundException("Resource not found with id: "+ RID);
        }
        resourceRepository.deleteById(RID);
    }

    @Transactional
    public ResourceModel create(ResourceModel rm) {

        if (rm.getSalary() < 0) {
            throw new ValidationExceptions.ValidationException("Cannot Have Negative Salary");
        }

        return resourceRepository.save(rm);
    }

    @Transactional
    public ResourceModel update(ResourceModel rm) {
        ResourceModel resource = resourceRepository.findById(rm.getRID())
            .orElseThrow(() -> new ResourceExceptions.ResourceNotFoundException("Resource not found with id: " + rm.getRID()));
        
        if (rm.getSalary() < 0) {
            throw new ValidationExceptions.ValidationException("Cannot Have Negative Salary");

        }
        
        BeanUtils.copyProperties(rm, resource, "RID", "UID");

        resource.setLastUpdated(LocalDateTime.now());

        return resourceRepository.save(resource);

    }
   
}