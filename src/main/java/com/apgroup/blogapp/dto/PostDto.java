package com.apgroup.blogapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PostDto {

    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String imgName;

    @NotEmpty
    private Date addedDate;

    // in dono dto ke andr post nahi bana hua he
    private CategoryDto category; // name should be same as Post entity variable
    private UserDto user;

    // ye likhne pe  Infinite recursion ho jayega
    // like yaha category ke andr fir se post ki list he same with user
//    private Category category;
//    private User user;

    private Set<CommentDto> comments = new HashSet<>();
}
