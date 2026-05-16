package com.Mohammad.devtrack.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Mohammad.devtrack.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    public boolean existsByUEmail(String uEmail);
}