package com.example.be_java_hisp_w19_g2.entities;

import com.example.be_java_hisp_w19_g2.roles.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    @DisplayName("Verifica si sigue correctamente al Usuario")
    public void followUser() {
        // Arrange
        User user1 = new User(1, "Fran", Roles.USER);
        User user2 = new User(2, "Martin", Roles.SELLER);
        List<User> expectedSeller = List.of(user1);
        List<User> expectedUser = List.of(user2);
        // Act
        user1.followUser(user2);
        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedSeller, user2.getFollowers()),
                () -> Assertions.assertEquals(expectedUser, user1.getFollowed())
        );
    }

    @Test
    @DisplayName("Verifica si deja de seguir correctamente al Usuario")
    public void unfollowUser() {
        // Arrange
        User user1 = new User(1, "Fran", Roles.USER);
        User user2 = new User(2, "Martin", Roles.SELLER);
        user1.setFollowed(List.of(user2));
        user2.setFollowers(List.of(user1));
        List<User> expected = new ArrayList<>();
        // Act
        user1.unfollowUser(user2);
        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(expected, user2.getFollowers()),
                () -> Assertions.assertEquals(expected, user1.getFollowed())
        );
    }

    @Test
    @DisplayName("Verifica que se agreca correctamente el post")
    public void addPost() {
        // Arrange
        User user1 = new User(1, "Fran", Roles.USER);
        Post post1 = new Post();
        List<Post> expected = List.of(post1);
        // Act
        user1.addPost(post1);
        // Assert
        Assertions.assertEquals(expected, user1.getPosts());
    }
}
