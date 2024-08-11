package com.apgroup.blogapp.controller;

import com.apgroup.blogapp.dto.CommentDto;
import com.apgroup.blogapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentCont {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable("postId") int postId,
                                                 @RequestBody CommentDto commentDto){
        CommentDto commentDto1 = this.commentService.addComment(commentDto,postId);
        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Integer comId){
        commentService.deleteComment(comId);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }
}
