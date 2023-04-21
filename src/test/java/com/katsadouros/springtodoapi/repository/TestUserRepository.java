package com.katsadouros.springtodoapi.repository;

import com.katsadouros.springtodoapi.model.User;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;


@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class TestUserRepository {

    @Autowired
    UserRepository userRepository;

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("test")
            .withUsername("sa")
            .withPassword("sa");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Test
    @Sql("classpath:user_insert.sql")
    void testUserFetch() {
        User user = userRepository.findByUsername("alice").get();
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), 1L);
    }

    @Test
    void testUserCreation() {
        User user = new User("testUser", "test@test.com");

        user = userRepository.save(user);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(1L, user.getId());

    }

    @Test
    void testInvalidUsername() {
        User user = new User("testUs**er", "test@test.com");

        Assertions.assertThrows(ConstraintViolationException.class, ()->userRepository.save(user));
    }

    @Test
    @Sql("classpath:user_insert.sql")
    void testAllUsers(){
        List<User> users = userRepository.findAll();

        Assertions.assertEquals(3, users.size());
    }

}
