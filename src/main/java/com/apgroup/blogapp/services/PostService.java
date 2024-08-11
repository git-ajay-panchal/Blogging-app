package com.apgroup.blogapp.services;

import com.apgroup.blogapp.dto.PostDto;
import com.apgroup.blogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer catId);

    PostDto updatePost(PostDto postDto, int postId);

    void deletePost(int postId);

    PostDto getPost(int postId);

    PostResponse getPosts(int pN , int pS, String sortBy);

    List<PostDto> getAllPosts();

    PostResponse getAllPostsByUser(Integer userId,int pN , int pS);
    List<PostDto> getAllPostsByCategory(Integer cartId);

    List<PostDto> searchPostsByTitle(String title);

    List<PostDto> searchPostsByKeywordTitle(String keyword);
}
