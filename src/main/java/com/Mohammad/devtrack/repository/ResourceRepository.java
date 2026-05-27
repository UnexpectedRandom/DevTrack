package com.Mohammad.devtrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Mohammad.devtrack.model.ResourceModel;

import java.util.List;

public interface ResourceRepository extends JpaRepository<ResourceModel, Long> {
    List<ResourceModel> findByUID_UID(Long UID);

    List<ResourceModel> findByRIDAndUID_UID(Long rid, Long UID);

}