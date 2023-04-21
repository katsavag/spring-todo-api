package com.katsadouros.springtodoapi.service;

import com.katsadouros.springtodoapi.exception.ResourceNotFound;
import com.katsadouros.springtodoapi.model.User;
import com.katsadouros.springtodoapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testUserCreation(){
        User expectedUser = new User(0, "john", "john@test.com");
        User givenUser = new User("john", "john@test.com");

        when(userRepository.save(givenUser)).thenReturn(expectedUser);

        User result = userService.createUser(givenUser);

        verify(userRepository, times(1)).save(givenUser);
        Assertions.assertEquals(result, expectedUser);
    }

    @Test
    public void testUserFetching(){
        User expectedUser = new User(1, "john", "john@test.com");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(expectedUser));

        User result = userService.getUserById(1L);

        verify(userRepository, times(1)).findById(1L);

        Assertions.assertEquals(expectedUser, result);
    }

    @Test
    public void testUserFetchingNotFound(){
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(ResourceNotFound.class, ()-> userService.getUserById(1L));

        verify(userRepository, times(1)).findById(1L);
    }

}
