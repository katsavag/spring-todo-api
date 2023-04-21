package com.katsadouros.springtodoapi.service;

import com.katsadouros.springtodoapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    Page<User> getAllUsers(Pageable pageable);
}
