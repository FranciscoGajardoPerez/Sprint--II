package com.example.be_java_hisp_w19_g2.controllers;

import com.example.be_java_hisp_w19_g2.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {
  @Autowired
  IUserService userService;

  @GetMapping("/{userId}/followers/count")
  public ResponseEntity<?> getCountFollowers(@PathVariable Integer userId) {
    return new ResponseEntity<>(userService.getCountFollowers(userId), HttpStatus.OK);
  }

  @GetMapping("/{userId}/followed/list")
  public ResponseEntity<?> getFollowedList(
          @PathVariable Integer userId,
          @RequestParam(required = false, defaultValue = "") String order) {
    if(order.equals("")){
      return new ResponseEntity<>(userService.getFollowed(userId), HttpStatus.OK);
    }
    return new ResponseEntity<>(userService.getFollowedOrderedByName(userId, order), HttpStatus.OK);
  }

  @GetMapping("/{userId}/followers/list")
  public ResponseEntity<?> getFollowersList(
          @PathVariable Integer userId,
          @RequestParam(required = false, defaultValue = "") String order) {
    if(order.equals("")){
      return new ResponseEntity<>(userService.getFollowers(userId), HttpStatus.OK);
    }
    return new ResponseEntity<>(userService.getFollowersOrderedByName(userId, order), HttpStatus.OK);
  }

  @PostMapping("/{userId}/follow/{userIdToFollow}")
  public ResponseEntity<?> followSeller(
          @PathVariable Integer userId,
          @PathVariable Integer userIdToFollow) {
    return new ResponseEntity<>(userService.followSeller(userId, userIdToFollow), HttpStatus.OK);
  }

  @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
  public ResponseEntity<?> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
    return new ResponseEntity<>(userService.unfollowSeller(userId, userIdToUnfollow), HttpStatus.OK);
  }
}