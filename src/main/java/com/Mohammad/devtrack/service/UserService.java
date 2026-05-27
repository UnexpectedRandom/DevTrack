package com.Mohammad.devtrack.service;

import com.Mohammad.devtrack.model.UserModel;
import com.Mohammad.devtrack.repository.UserRepository;
import com.Mohammad.devtrack.exceptions.UserExceptions;
import com.Mohammad.devtrack.exceptions.EmailExceptions;
import com.Mohammad.devtrack.exceptions.ValidationExceptions;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<UserModel> findUsr(long UsrID) {
        return userRepository.findById(UsrID);
    }

    @Transactional
    public void delete(Long UID) {
        if (!userRepository.existsById(UID)) {
            throw new UserExceptions.UserNotFoundException("User With ID: " + UID + " Not Found");
        }

        userRepository.deleteById(UID);

    }

    @Transactional
    public UserModel create(UserModel um) {

        if (um.getUHashedPassword() == null || um.getUHashedPassword().length() < 8) {
            throw new ValidationExceptions.ValidationException("Password Must Be At Least 8 Characters Long");
        }

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (!um.getUHashedPassword().matches(passwordRegex)) {
            throw new ValidationExceptions.ValidationException(
                    "Password Must Contain At Least One Uppercase Letter, One Lowercase Letter, One Number, And One Special Character");
        }

        um.setUHashedPassword(passwordEncoder.encode(um.getUHashedPassword()));

        if (userRepository.existsByEmail(um.getEmail())) {
            throw new EmailExceptions.EmailAlreadyTakenException("Email Already Taken");
        }

        userRepository.save(um);

        return um;

    }

    @Transactional
    public UserModel update(Long id, UserModel um) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new UserExceptions.UserNotFoundException("User With ID: " + id + " Not Found"));

        BeanUtils.copyProperties(um, user, "UID", "UHashedPassword");

        if (um.getUHashedPassword() != null && !um.getUHashedPassword().isBlank()) {
            if (um.getUHashedPassword().length() < 8) {
                throw new ValidationExceptions.ValidationException("Password Must Be At Least 8 Characters Long");
            }

            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
            if (!um.getUHashedPassword().matches(passwordRegex)) {
                throw new ValidationExceptions.ValidationException(
                        "Password Must Contain At Least One Uppercase Letter, One Lowercase Letter, One Number, And One Special Character");
            }
            user.setUHashedPassword(passwordEncoder.encode(um.getUHashedPassword()));
        }

        return userRepository.save(user);
    }

}