package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.FollowerCountDTO;
import com.example.be_java_hisp_w19_g2.dtos.FollowersDTO;
import com.example.be_java_hisp_w19_g2.dtos.UserDTO;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.handlers.InvalidParamException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotFoundException;
import com.example.be_java_hisp_w19_g2.handlers.UserNotSeller;
import com.example.be_java_hisp_w19_g2.repositories.UserRepository;
import com.example.be_java_hisp_w19_g2.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService implements IUserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public FollowerCountDTO getCountFollowers(Integer userId) {
    User foundUser = userRepository.getUserById(userId);
    return new FollowerCountDTO(userId, foundUser.getUserName(), foundUser.getFollowers().size());
  }

  @Override
  public UserDTO followSeller(Integer userId, Integer userToFollowId) {
    User user = userRepository.getUserById(userId);
    User userToFollow = userRepository.getUserById(userToFollowId);

    if (!userToFollow.getUserRol().equals(Roles.SELLER)) {
      throw new UserNotSeller("Function Invalid: " + userToFollow + " not Seller");
    }
    user.followUser(userToFollow);

    return new UserDTO(userToFollow.getUserId(), userToFollow.getUserName());
  }

  @Override
  public UserDTO unfollowSeller(Integer userId, Integer userToUnfollowId) {
    User user = userRepository.getUserById(userId);
    User userToUnfollow = userRepository.getUserById(userToUnfollowId);

    if(user.getFollowers().contains(userToUnfollow)){
      user.unfollowUser(userToUnfollow);
      return new UserDTO(userToUnfollow.getUserId(), userToUnfollow.getUserName());
    }
    throw new UserNotFoundException("You can`t unfollow a user you are not following");
  }

  private FollowersDTO orderByName(FollowersDTO followed, String order) {
    if (order.equals("name_asc")) {
      followed.getFollowed().sort(Comparator.comparing(UserDTO::getUserName));
    } else if (order.equals("name_desc")) {
      followed.getFollowed().sort((u1, u2) -> u2.getUserName().compareTo(u1.getUserName()));
    } else {
      throw new InvalidParamException("There is no ordering method for " + order + " better try name_asc/name_desc");
    }
    return followed;
  }

  @Override
  public FollowersDTO getFollowed(Integer userId) {
    User user = userRepository.getUserById(userId);
    List<UserDTO> usersDTO = new ArrayList<>();
    for (User followed : user.getFollowed()) {
      usersDTO.add(new UserDTO(followed.getUserId(), followed.getUserName()));
    }
    return new FollowersDTO(user.getUserId(), user.getUserName(), usersDTO);
  }

  @Override
  public FollowersDTO getFollowers(Integer userId) {
    User user = userRepository.getUserById(userId);
    List<UserDTO> usersDTO = new ArrayList<>();
    for (User follower : user.getFollowers()) {
      usersDTO.add(new UserDTO(follower.getUserId(), follower.getUserName()));
    }
    return new FollowersDTO(user.getUserId(), user.getUserName(), usersDTO);
  }

  @Override
  public FollowersDTO getFollowedOrderedByName(Integer userID, String order) {
    return orderByName(getFollowed(userID), order);
  }

  @Override
  public FollowersDTO getFollowersOrderedByName(Integer userID, String order) {
    return orderByName(getFollowers(userID), order);
  }
}
