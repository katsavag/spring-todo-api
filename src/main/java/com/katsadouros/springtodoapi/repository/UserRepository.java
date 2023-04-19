package com.katsadouros.springtodoapi.repository;

import com.katsadouros.springtodoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * This interface defines all the required operations
 * for retrieving and creating user entries at the database.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findAllByOrderByUsernameAsc();
}
