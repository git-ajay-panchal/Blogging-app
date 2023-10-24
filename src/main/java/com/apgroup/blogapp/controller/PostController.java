package com.apgroup.blogapp.controller;


import com.apgroup.blogapp.dto.CategoryDto;
import com.apgroup.blogapp.dto.PostDto;
import com.apgroup.blogapp.services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId){
        PostDto createdPostDto = postService.createPost(postDto,userId,catId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList = postService.getAllPostsByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer catId){
        List<PostDto> postDtoList = postService.getAllPostsByCategory(catId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/posts/{pId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer pId){
        PostDto postDto = postService.getPost(pId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/posts/all")
    public ResponseEntity<List<PostDto>> getAllPost(){
        List<PostDto> postDtoList = postService.getAllPosts();
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{pId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer pId){
        postService.deletePost(pId);
        return new ResponseEntity<>("post has been deleted",HttpStatus.OK);
    }

    @PostMapping("/posts/update/{pId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer pId){
        PostDto updatedPost = postService.updatePost(postDto,pId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

}
