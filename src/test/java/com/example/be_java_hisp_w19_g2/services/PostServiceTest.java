package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.FollowedPostsDTO;
import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.handlers.FailedPostCreation;
import com.example.be_java_hisp_w19_g2.repositories.PostRepository;
import com.example.be_java_hisp_w19_g2.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import utils.PostFactory;
import utils.UserFactory;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Test
    @DisplayName("Add post ")
    public  void addPostTest(){
        User user = UserFactory.createUserWithRoleSellerMati();
        Integer userId = user.getUserId();
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(userId);
        Post post = new Post();
        post.setPostId(userId);
        post.setUserId(userId);

        Mockito.when(userRepository.getUserById(userId)).thenReturn(user);
        Mockito.when(postRepository.addPost(post)).thenReturn(post);

        // Act
        postService.addPost(postDTO);
        // Assert
        Mockito.verify(postRepository, Mockito.atLeastOnce()).addPost(post);
    }

    @Test
    @DisplayName("Add post Error")
    public  void addPostTestError(){
        User user = UserFactory.createUserWithRoleUserFran();
        Integer userId = user.getUserId();
        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(userId);

        Mockito.when(userRepository.getUserById(userId)).thenReturn(user);
        // Act & Assert
        Assertions.assertThrowsExactly(
                FailedPostCreation.class,
                () -> postService.addPost(postDTO)
        );
    }

    @Test
    @DisplayName("Verificar el correcto ordenamiento ascendente or fecha")
    public void getFollowedPostOrderedByDateASCTest(){
        // Arr
        FollowedPostsDTO expect = PostFactory.createFollowedPostDTOAsc();
        // Mock
        Mockito.when(userRepository.getUserById(12)).thenReturn(UserFactory.createUserWithPost(12));
        // Act
        FollowedPostsDTO result = postService.getFollowedPostOrderedByDate(12,"date_asc");
        // Assert
        Assertions.assertEquals(expect,result);
    }

    @Test
    @DisplayName("Verificar el correcto ordenamiento ascendente or fecha")
    public void getFollowedPostOrderedByDateDESCTest(){
        FollowedPostsDTO expect = PostFactory.createFollowedPostDTODESC(12);
        // Mock
        Mockito.when(userRepository.getUserById(12)).thenReturn(UserFactory.createUserWithPost(12));
        // Act
        FollowedPostsDTO result = postService.getFollowedPostOrderedByDate(12,"date_desc");
        // Assert
        Assertions.assertEquals(expect,result);
    }

    @Test
    @DisplayName("Obtener Followed de Post")
    public void getFollowedPostTest(){
        // Arr
        FollowedPostsDTO expect = PostFactory.createFollowedPostDTODESC(11);
        // Mock
        Mockito.when(userRepository.getUserById(11)).thenReturn(UserFactory.createUserWithFollowedWithPosts(11));
        // Act
        FollowedPostsDTO result = postService.getFollowedPost(11);
        // Assert
        Assertions.assertEquals(expect,result);
    }
}