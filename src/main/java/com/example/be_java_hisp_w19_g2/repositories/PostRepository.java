package com.example.be_java_hisp_w19_g2.repositories;

import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.entities.Product;
import com.example.be_java_hisp_w19_g2.handlers.FailedPostCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class PostRepository {
  @Autowired
  UserRepository userRepository;

  Integer countId = 1;
  public List<Post> posts = new ArrayList<>();

  public PostRepository() {
    this.posts = postsLists();
  }

  private List<Post> postsLists() {
    posts.add(new Post(1,  10,  LocalDate.now().minusDays(-12),   456, 60.20));
    posts.add(new Post(2,  10,  LocalDate.now().minusDays(-12),   87,  700.99));
    posts.add(new Post(3,  7,  LocalDate.now().minusDays(-12),   34,  59.60));
    posts.add(new Post(4,  7,  LocalDate.now().minusDays(-12),   34,  26.50));
    posts.add(new Post(5,  7,  LocalDate.now().minusDays(-12),   34,  45.00));
    posts.add(new Post(6,  9,  LocalDate.now().minusDays(-12),   47,  58.80));
    posts.add(new Post(7,  9,  LocalDate.now().minusDays(-20),   56,  73.00));
    posts.add(new Post(8,  9,  LocalDate.now().minusDays(-20),   19,  73.00));
    posts.add(new Post(9,  9,  LocalDate.now().minusDays(-20),   82,  21.10));
    posts.add(new Post(10, 9,  LocalDate.now().minusDays(-20),  19,  25.09));
    posts.add(new Post(11, 10, LocalDate.now(),  93,  43.70));
    posts.add(new Post(12, 10, LocalDate.now(),  8,   156.08));
    posts.add(new Post(13, 9,  LocalDate.now().minusDays(-20),
            new Product("Skate", "Sport",    "Electro", "Apple",     "Grey"),  19,  25.09));
    posts.add(new Post(14, 10,  LocalDate.now().minusDays(-12),
            new Product("Longboard", "Sport",    "Electro", "Apple",     "Grey"),  19,  25.09));

    return posts;
  }

  public Post addPost(Post post) {
    post.setPostId(countId);
    posts.add(post);
    countId++;
    return post;
  }

  public Integer getCountId(){
    return countId;
  }
}
