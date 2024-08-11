package com.apgroup.blogapp.services.impl;

import com.apgroup.blogapp.dto.CommentDto;
import com.apgroup.blogapp.entities.Comment;
import com.apgroup.blogapp.entities.Post;
import com.apgroup.blogapp.exceptions.ResourceNotFoundException;
import com.apgroup.blogapp.repsitory.CommentRepo;
import com.apgroup.blogapp.repsitory.PostRepo;
import com.apgroup.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = dtoToEnt(commentDto);
        comment.setPost(post);
        Comment comment1 = commentRepo.save(comment);
        return entToDto(comment1);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = commentRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("comment", "id", id));
        commentRepo.delete(comment);
    }

    public Comment dtoToEnt(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }

    public CommentDto entToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
}
