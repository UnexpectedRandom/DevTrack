package com.Mohammad.devtrack.service;

import com.Mohammad.devtrack.model.ResourceModel;
import com.Mohammad.devtrack.model.UserModel;
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
    public void delete(Long RID, Long currentUsrId) {
        ResourceModel findResource = resourceRepository.findById(RID)
        .orElseThrow(() -> new ResourceExceptions.ResourceNotFoundException("Resource not found with id: " + RID)); 
        
        if (findResource.getUID() == null) {
            throw new SecurityException("This resource doesn't exist");
        }
        
        if (!currentUsrId.equals(findResource.getUID().getUID())) {
            throw new SecurityException("You do not own this resource.");
        }

        resourceRepository.deleteById(RID);
    }

    @Transactional
    public ResourceModel create(ResourceModel rm, Long currentUserId) {
        
        if (rm.getSalary() < 0) {
            throw new ValidationExceptions.ValidationException("Cannot Have Negative Salary");
        }

        UserModel userCreate = new UserModel();
        userCreate.setUID(currentUserId);

        rm.setUID(userCreate);
        rm.setDateCreated(LocalDateTime.now());
        rm.setDateAppliedTo(LocalDateTime.now());

        return resourceRepository.save(rm);
    }

    @Transactional
    public ResourceModel update(ResourceModel rm, Long currentUserId) {
        
        ResourceModel resource = resourceRepository.findById(rm.getRID())
            .orElseThrow(() -> new ResourceExceptions.ResourceNotFoundException("Resource not found with id: " + rm.getRID()));
        
        if (!resource.getUID().getUID().equals(currentUserId)) {
            throw new SecurityException("You do not own this resource.");
        }
        if (rm.getSalary() < 0) {
            throw new ValidationExceptions.ValidationException("Cannot Have Negative Salary");

        }
        
        BeanUtils.copyProperties(rm, resource, "RID", "UID");

        resource.setLastUpdated(LocalDateTime.now());

        return resourceRepository.save(resource);

    }
   
}