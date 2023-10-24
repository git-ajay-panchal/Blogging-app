package com.apgroup.blogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotEmpty // not null and blank
    private String name;

    @Email(message = "Not valid email")
    private String email;

    @NotEmpty
    @Size(min=4 , max = 8 , message = "pwd should be min of 4 char")
    private String password;

    @NotEmpty
    private String about;

}
