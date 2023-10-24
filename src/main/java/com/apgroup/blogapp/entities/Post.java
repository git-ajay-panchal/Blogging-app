package com.apgroup.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title" ,length = 100, nullable = false)
    private String title;
    private String content;
    private String imgName;
    private Date addedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
