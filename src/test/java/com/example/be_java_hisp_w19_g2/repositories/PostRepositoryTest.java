package com.example.be_java_hisp_w19_g2.repositories;

import com.example.be_java_hisp_w19_g2.entities.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PostRepositoryTest {
    PostRepository postRepository = new PostRepository();

    @Test
    @DisplayName("Verifica que se agrega correctamente un producto nuevo.")
    public void addProductTest() {
        // Arange
        Post post = new Post(20,  10,  LocalDate.now().minusDays(-12),   456, 60.20);
        Integer expectedCount = postRepository.getCountId() + 1;
        // Act
        postRepository.addPost(post);
        // Assert
        Assertions.assertEquals(expectedCount, postRepository.getCountId());
    }
}
