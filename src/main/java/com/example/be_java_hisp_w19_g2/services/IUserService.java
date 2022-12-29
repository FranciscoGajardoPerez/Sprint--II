package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.FollowerCountDTO;
import com.example.be_java_hisp_w19_g2.dtos.FollowersDTO;
import com.example.be_java_hisp_w19_g2.dtos.UserDTO;

public interface IUserService {

  public FollowerCountDTO getCountFollowers(Integer userId);

  public UserDTO followSeller(Integer userId, Integer userToFollow);

  public UserDTO unfollowSeller(Integer userId , Integer userToUnfollowId);

  public FollowersDTO getFollowers(Integer userID);

  public FollowersDTO getFollowersOrderedByName(Integer userID, String order);

  public FollowersDTO getFollowed(Integer userID);

  public FollowersDTO getFollowedOrderedByName(Integer userID, String order);
}