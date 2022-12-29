package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.FollowerCountDTO;
import com.example.be_java_hisp_w19_g2.dtos.FollowersDTO;
import com.example.be_java_hisp_w19_g2.dtos.UserDTO;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.handlers.InvalidParamException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotFoundException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotSeller;
import com.example.be_java_hisp_w19_g2.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.UserDTOFactory;
import utils.UserFactory;

import java.util.ArrayList;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("Devuelve el cálculo correcto del total de la cantidad de seguidores que posee un usuario.")
    public void getCountFollowersTest(){
        // Arrange
        User user = UserFactory.createUserWithRoleSellerMati();
        Integer id = user.getUserId();
        FollowerCountDTO expect = UserDTOFactory.createFollowersCountDTO();
        Mockito.when(userRepository.getUserById(id)).thenReturn(user);
        // Act
        FollowerCountDTO result = userService.getCountFollowers(id);
        // Assert
        Assertions.assertEquals(expect,result);
    }

    @Test
    @DisplayName("Verifica que el usuario a seguir sea un Seller")
    void followSellerTest() {
        // Arrange
        User user = UserFactory.createUserWithRoleUserFran();
        Integer id = user.getUserId();

        User userSeller = UserFactory.createUserWithRoleSellerCele();
        Integer userTofollowId = userSeller.getUserId();

        UserDTO expect = UserDTOFactory.createUserWithRoleSellerDTOCele();
        Mockito.when(userRepository.getUserById(id)).thenReturn(user);
        Mockito.when(userRepository.getUserById(userTofollowId)).thenReturn(userSeller);
        //Act
        UserDTO result = userService.followSeller(user.getUserId(),userSeller.getUserId());

        //Assert
        Assertions.assertEquals(expect, result);
    }

    @Test
    @DisplayName("Verifica que si el usuario a seguir no es un vendedor, lance una excepcion")
    void followSellerExceptionTest() {
        User user = UserFactory.createUserWithRoleUserFran();
        Integer id = user.getUserId();
        User userToFollow = UserFactory.createUserWithRoleUserBrahi();
        Integer userToFollowId = userToFollow.getUserId();

        Mockito.when(userRepository.getUserById(id)).thenReturn(user);
        Mockito.when(userRepository.getUserById(userToFollowId)).thenReturn(userToFollow);

        Assertions.assertThrowsExactly(
                UserNotSeller.class,
                () -> userService.followSeller(id,2)
        );
    }

    @Test
    @DisplayName("Verifica que el usuario a dejar de seguir sea un Seller")
    void unfollowSellerTest() {
        User user = UserFactory.createUserWithRoleUserFran();
        Integer id = user.getUserId();

        User userSeller = UserFactory.createUserWithRoleSellerCele();
        Integer userToUnfollowId = userSeller.getUserId();

        UserDTO expect = UserDTOFactory.createUserWithRoleSellerDTOCele();
        //Act

        Mockito.when(userRepository.getUserById(id)).thenReturn(user);
        Mockito.when(userRepository.getUserById(userToUnfollowId)).thenReturn(userSeller);

        UserDTO result = userService.unfollowSeller(user.getUserId(),userSeller.getUserId());

        //Assert
        Assertions.assertEquals(expect, result);
    }

    @Test
    @DisplayName("Verifica que si el usuario a dejar de seguir no es un Seller, lance una expcecion")
    void unfollowSellerExceptionTest() {
        // Arrange
        User userSeller = UserFactory.createUserWithRoleSellerMati();
        Integer sellerId = userSeller.getUserId();

        User user = UserFactory.createUserWithRoleUserFran();
        Integer userId = user.getUserId();

        Mockito.when(userRepository.getUserById(userId)).thenReturn(user);
        Mockito.when(userRepository.getUserById(sellerId)).thenReturn(userSeller);
        // Act & Assert
        Assertions.assertThrowsExactly(
                UserNotFoundException.class,
                () -> userService.unfollowSeller(userId, sellerId)
        );
    }

    @Test
    @DisplayName("Verifica que se obtenga la lista de seguidores ordenados de forma ascendente")
    void getFollowedOrderedAscByNameTest() {
        //Arrange
        FollowersDTO expectedFollowers = UserDTOFactory.createFollowerDTO();

        //Act
        Integer id = UserFactory.createUserWithRoleSellerMati().getUserId();
        User user = UserFactory.createUserWithRoleSellerMati();

        Mockito.when(userRepository.getUserById(id)).thenReturn(user);

        FollowersDTO result = userService.getFollowedOrderedByName(id,"name_asc");

        //Assert
        Assertions.assertEquals(expectedFollowers, result);
    }


    @Test
    @DisplayName("Verifica que se obtenga la lista de seguidores ordenados de forma descendente")
    void getFollowedOrderedDescByNameTest() {
        //Arrange
        FollowersDTO expectedFollowers = UserDTOFactory.createFollowerDTO();
        Collections.reverse(expectedFollowers.getFollowed());

        //Act
        Integer id = UserFactory.createUserWithRoleSellerMati().getUserId();
        User user = UserFactory.createUserWithRoleSellerMati();

        Mockito.when(userRepository
                .getUserById(id))
                .thenReturn(user);

        FollowersDTO result = userService.getFollowedOrderedByName(id,"name_desc");

        //Assert
        Assertions.assertEquals(expectedFollowers, result);
    }

    @Test
    @DisplayName("Verifica que se el parametro de orden sea correcto, sino lanza excepcion")
    void getFollowedOrderedByNameExceptionTest() {
        // Arrange
        Integer id = UserFactory.createUserWithRoleSellerMati().getUserId();
        User user = UserFactory.createUserWithRoleSellerMati();

        Mockito.when(userRepository.getUserById(id)).thenReturn(user);

        // Act & Assert
        Assertions.assertThrowsExactly(
                InvalidParamException.class,
                () -> userService.getFollowedOrderedByName(id,"order")
        );
    }

}