package com.Mohammad.devtrack.repository;

import java.util.Optional;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Mohammad.devtrack.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);
}
