package com.apgroup.blogapp.controller;


import com.apgroup.blogapp.dto.CategoryDto;
import com.apgroup.blogapp.dto.PostDto;
import com.apgroup.blogapp.payload.ImageResponse;
import com.apgroup.blogapp.payload.PostResponse;
import com.apgroup.blogapp.services.FileService;
import com.apgroup.blogapp.services.PostService;
import com.sun.deploy.net.HttpResponse;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @PostMapping("/user/{userId}/category/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId) {
        PostDto createdPostDto = postService.createPost(postDto, userId, catId);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize) {
        PostResponse postResponse = postService.getAllPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer catId) {
        List<PostDto> postDtoList = postService.getAllPostsByCategory(catId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/posts/{pId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer pId) {
        PostDto postDto = postService.getPost(pId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // @RequestParam for query parameter
    // api?pageNumber=1&pageSize=10&sortBy=postId(string)
    // if you dont pass ?pageNumber=1&pageSize=10&sortBy=postId in URL it'll work.
    // pageNumber starts from 0
    //    required = false  if value is not there use default value
    // eg. total record=5 , pn=1 ps=3 means on page 0 record=3 and on page 1 you have remaining 2 record
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy) {
        PostResponse postResponse = postService.getPosts(pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/all")
    public ResponseEntity<List<PostDto>> getAllPost() {
        List<PostDto> postDtoList = postService.getAllPosts();
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{pId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer pId) {
        postService.deletePost(pId);
        return new ResponseEntity<>("post has been deleted", HttpStatus.OK);
    }

    @PostMapping("/posts/update/{pId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer pId) {
        PostDto updatedPost = postService.updatePost(postDto, pId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Search post by title
    @GetMapping("posts/search/{title}")
    public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("title") String title) {
        List<PostDto> postDtoList = postService.searchPostsByTitle(title);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    // Search post by keyword
    // like "food" - it will select posts with title containing food
    @GetMapping("posts/search/v2/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostsByKeyword(@PathVariable("keyword") String keyword) {
        List<PostDto> postDtoList = postService.searchPostsByKeywordTitle(keyword);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    // upload post images
    @PostMapping("post/imageupload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable("postId") int postId,
                                                         @RequestParam("image") MultipartFile image) throws IOException {

        PostDto postDto = postService.getPost(postId);
        String filename = fileService.uploadImage(image);
        postDto.setImgName(filename);
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    // method to serve files
    // localhost:8081//post/downloadimage/ac.png
    // Serving image with HttpServletResponse
    @GetMapping(value = "post/downloadimage/{image}" , produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("image") String image, HttpServletResponse response) throws IOException {
        InputStream is = fileService.getResource(image);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is,response.getOutputStream());

    }

}