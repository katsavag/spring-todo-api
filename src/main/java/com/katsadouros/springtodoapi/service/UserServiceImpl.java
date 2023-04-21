package com.katsadouros.springtodoapi.service;

import com.katsadouros.springtodoapi.exception.ResourceNotFound;
import com.katsadouros.springtodoapi.mapper.UserMapper;
import com.katsadouros.springtodoapi.model.User;
import com.katsadouros.springtodoapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public final class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new ResourceNotFound("User", "id", id.toString());
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new ResourceNotFound("User", "username", username);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else{
            throw new ResourceNotFound("User", "email", email);
        }
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByUsernameAsc(pageable);
    }
}
