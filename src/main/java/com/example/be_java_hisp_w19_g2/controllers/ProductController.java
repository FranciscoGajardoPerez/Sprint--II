package com.example.be_java_hisp_w19_g2.controllers;

import com.example.be_java_hisp_w19_g2.dtos.PostDTO;
import com.example.be_java_hisp_w19_g2.entities.Post;
import com.example.be_java_hisp_w19_g2.services.IProductService;
import com.example.be_java_hisp_w19_g2.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("products")
public class    ProductController {
    @Autowired
    IProductService productService;
    @Autowired
    PostService postService;

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity getFollowedPost(
           @PathVariable Integer userId,
           @RequestParam(required = false, defaultValue = "") String order) {
        if(order.equals("")){
            return new ResponseEntity<>(postService.getFollowedPost(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(postService.getFollowedPostOrderedByDate(userId, order), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> postProduct(@RequestBody @Valid PostDTO postDTO){
        productService.postProduct(postDTO);
        postService.addPost(postDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
