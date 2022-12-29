package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.FollowedPostsDTO;
import com.example.be_java_hisp_w19_g2.dtos.FollowersDTO;
import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.entities.Post;

import java.util.List;

public interface IPostService {
  public PostDTO addPost(PostDTO postDTO);
  public FollowedPostsDTO getFollowedPost(Integer userId);
  public FollowedPostsDTO getFollowedPostOrderedByDate(Integer userId, String order);
}
