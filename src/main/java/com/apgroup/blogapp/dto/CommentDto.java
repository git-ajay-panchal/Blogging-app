package com.apgroup.blogapp.dto;

import com.apgroup.blogapp.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private int id;
    private String content;
}
