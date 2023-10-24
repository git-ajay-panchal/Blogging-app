package com.apgroup.blogapp.services;

import com.apgroup.blogapp.dto.CategoryDto;
import com.apgroup.blogapp.dto.PostDto;
import com.apgroup.blogapp.entities.Category;
import com.apgroup.blogapp.entities.Post;
import com.apgroup.blogapp.entities.User;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer catId);

    PostDto updatePost(PostDto postDto, int postId);

    void deletePost(int postId);

    PostDto getPost(int postId);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByUser(Integer userId);
    List<PostDto> getAllPostsByCategory(Integer cartId);
}
