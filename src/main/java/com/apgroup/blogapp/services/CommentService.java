package com.apgroup.blogapp.services;

import com.apgroup.blogapp.dto.CommentDto;

public interface CommentService {
    CommentDto addComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer id);
}
