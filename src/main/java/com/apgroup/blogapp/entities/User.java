package com.apgroup.blogapp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")   // need to use table name as users bcoz "User" in postgres is a
public class User {         // reserve word(db reserve word like admin, order,group etc)

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name" , nullable = false)
    private String name;
    private String email;
    private String password;
    private String about;

}
