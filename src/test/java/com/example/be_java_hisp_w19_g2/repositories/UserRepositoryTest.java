package com.example.be_java_hisp_w19_g2.repositories;

import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.handlers.UserNotFoundException;
import com.example.be_java_hisp_w19_g2.roles.Roles;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();

    @Test
    @DisplayName("Verifica que el usuario exista")
    void getUserByIdTest() {
        // Arrange
        User expectedUser = new User(1, "Fran", Roles.USER);
        // Act
        User resultedUser = userRepository.getUserById(1);
        // Assert
        Assertions.assertEquals(expectedUser, resultedUser);
    }

    @Test
    @DisplayName("Verifica que el usuario no exista")
    void getUserByIdErrorTest() {
        // Act
        Exception exception = Assertions.assertThrows(
                Exception.class, () -> userRepository.getUserById(100)
        );
        // Assert
        Assertions.assertEquals("User not found: 100", exception.getMessage());
    }

}