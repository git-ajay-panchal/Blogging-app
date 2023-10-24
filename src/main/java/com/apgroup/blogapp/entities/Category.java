package com.apgroup.blogapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catId;

    @Column(name = "title" , length = 100, nullable = false)
    private String catTitle;

    @Column(name = "description")
    private String catDesc;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}
