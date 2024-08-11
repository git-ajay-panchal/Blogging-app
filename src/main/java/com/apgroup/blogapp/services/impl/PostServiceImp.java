package com.apgroup.blogapp.services.impl;

import com.apgroup.blogapp.dto.CategoryDto;
import com.apgroup.blogapp.dto.PostDto;
import com.apgroup.blogapp.dto.UserDto;
import com.apgroup.blogapp.entities.Category;
import com.apgroup.blogapp.entities.Post;
import com.apgroup.blogapp.entities.User;
import com.apgroup.blogapp.exceptions.ResourceNotFoundException;
import com.apgroup.blogapp.payload.PostResponse;
import com.apgroup.blogapp.repsitory.CategoryRepo;
import com.apgroup.blogapp.repsitory.PostRepo;
import com.apgroup.blogapp.repsitory.UserRepo;
import com.apgroup.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {

        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));

        Category category = categoryRepo.findById(catId)
                .orElseThrow(()->new ResourceNotFoundException("Category","id",catId));

        postDto.setImgName("default.img");
        postDto.setAddedDate(new Date());
        postDto.setUser(modelMapper.map(user, UserDto.class));
        postDto.setCategory(modelMapper.map(category, CategoryDto.class));
        Post post = postRepo.save(postDtoToPostEn(postDto));
        return postEnToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post","id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImgName(postDto.getImgName());
        Post updatedPost = postRepo.save(post);
        return postEnToPostDto(updatedPost);
    }

    @Override
    public void deletePost(int postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPost(int postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        return postEnToPostDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepo.findAll();
        return postList.stream().map(this::postEnToPostDto).collect(Collectors.toList());
        //map(x->postEnToPostDto(x))
    }

    @Override
    public PostResponse getPosts(int pN, int pS,String sortBy) {
        //Adding pagination
        String sortDir = "desc"; // could be arg.
        Sort sort = (sortDir.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pN,pS,sort);//Sort.by(sortBy).descending()
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> postList = pagePost.getContent();
        List<PostDto> postDtoList = postList.stream().map(this::postEnToPostDto).collect(Collectors.toList()); //map(x->postEnToPostDto(x))
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNum(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getAllPostsByUser(Integer userId, int pN , int pS) {
        User user = userRepo.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        Pageable pageable = PageRequest.of(pN,pS);
        List<Post> postList = postRepo.findByUser(user,pageable);
        Page<Post> postPage = new PageImpl<Post>(postList,pageable,postList.size());
        List<PostDto> postDtoList = postList.stream().map(this::postEnToPostDto).collect(Collectors.toList());
        System.out.println("========="+postDtoList);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNum(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer cartId) {
        Category category = categoryRepo.findById(cartId).
                orElseThrow(()->new ResourceNotFoundException("category","id",cartId));
        List<Post> postList = postRepo.findByCategory(category);
        return postList.stream().map(this::postEnToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPostsByTitle(String title) {
         List<Post> postList = postRepo.findByTitle(title);
         return postList.stream().map(this::postEnToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPostsByKeywordTitle(String keyword) {
        List<Post> postList = postRepo.searchByTitle("%"+keyword+"%"); // ****
        return postList.stream().map(this::postEnToPostDto).collect(Collectors.toList());
    }

    public Post postDtoToPostEn(PostDto postDto){
        return modelMapper.map(postDto,Post.class);// (source,destination)
    }
    public PostDto postEnToPostDto(Post post){
        return modelMapper.map(post,PostDto.class);// (source,destination)
    }

}
