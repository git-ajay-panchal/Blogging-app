package com.apgroup.blogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String about;

}
