package com.katsadouros.springtodoapi.service;

import com.katsadouros.springtodoapi.exception.ResourceNotFound;
import com.katsadouros.springtodoapi.model.User;
import com.katsadouros.springtodoapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
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
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return null;
    }
}
