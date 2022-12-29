package com.example.be_java_hisp_w19_g2.services;

import com.example.be_java_hisp_w19_g2.dtos.FollowedPostsDTO;
import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.entities.User;
import com.example.be_java_hisp_w19_g2.handlers.FailedPostCreation;
import com.example.be_java_hisp_w19_g2.repositories.PostRepository;
import com.example.be_java_hisp_w19_g2.repositories.UserRepository;
import com.example.be_java_hisp_w19_g2.roles.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService{
  ModelMapper mapper = new ModelMapper();

  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;

  @Override
  public PostDTO addPost(PostDTO postDTO){
    User user = userRepository.getUserById(postDTO.getUserId());
    if(user.getUserRol() == Roles.SELLER) {
      Post postReturned = postRepository.addPost(mapper.map(postDTO, Post.class));
      postReturned.setUserId(user.getUserId());
      user.addPost(postReturned);
      return postDTO;
    } else {
      throw new FailedPostCreation("The user is not allowed to post a product");
    }
  }

  @Override
  public FollowedPostsDTO getFollowedPost(Integer userId){
      User user = userRepository.getUserById(userId);
      List<PostDTO> posts = new ArrayList<>();
      for (User u : user.getFollowed()) {
          for (Post p : u.getPosts()) {
              LocalDate postDate = p.getDate();
              Long daysSincePost = ChronoUnit.DAYS.between(postDate, LocalDate.now());
               if (daysSincePost <= 14) {
                   PostDTO postDTO = mapper.map(p, PostDTO.class);
                   posts.add(postDTO);
               }
          }
      }
      Collections.sort(posts, (a, b) -> (b.getDate().compareTo(a.getDate())));
      return new FollowedPostsDTO(userId, posts);
  }

    @Override
    public FollowedPostsDTO getFollowedPostOrderedByDate(Integer userId, String order) {
        List<Post> posts = userRepository.getUserById(userId).getPosts();
        if (order.equals("date_asc")) {
            Collections.sort(posts, Comparator.comparing(Post::getDate));
        } else if (order.equals("date_desc")) {
            Collections.sort(posts, (a, b) -> (b.getDate().compareTo(a.getDate())));
        }

       List<PostDTO> postDTOList = posts.stream()
               .map(p -> mapper.map(p, PostDTO.class))
               .collect(Collectors.toList());

        return new FollowedPostsDTO(userId, postDTOList);
    }
}
