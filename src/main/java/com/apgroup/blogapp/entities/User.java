package com.apgroup.blogapp.entities;


import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")   // need to use table name as users bcoz "User" in postgres is a
public class User {         // reserve word(db reserve word like admin, order,group etc)

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name" , nullable = false)
    private String name;
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

}
