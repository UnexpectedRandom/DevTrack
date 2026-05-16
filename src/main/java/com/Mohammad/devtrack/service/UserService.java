package com.Mohammad.devtrack.service;


import com.Mohammad.devtrack.model.UserModel;
import com.Mohammad.devtrack.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{
    
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<UserModel> findUsr(long UsrID) {
        return userRepository.findById(UsrID);
    }

    @Transactional
    public void delete(Long UID) {
        if(!userRepository.existsById(UID)) {
            throw new RuntimeException("User With ID: " + UID + " Not Found");
        }

        userRepository.deleteById(UID);
    }

    @Transactional
    public UserModel create(UserModel um) {
        if (userRepository.existsByUEmail(um.getUEmail())) {
            throw new RuntimeException("Emails Already Taken");
        }
        userRepository.save(um);
        return um;
    }

    @Transactional
    public UserModel update(Long id, UserModel um) {
        UserModel user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cannot find user by id: "+ id));

        BeanUtils.copyProperties(um, user, "UID");

        return userRepository.save(user);
    }
}
