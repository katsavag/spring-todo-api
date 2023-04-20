package com.katsadouros.springtodoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * This class represents a User in a ToDo platform.
 */

@Entity
@Table(name = "TODO_USER")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true)
    @Pattern(regexp = "^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
    private String username;

    @Column(name = "email", unique = true)
    @Email
    private String email;
}
