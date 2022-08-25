package com.apgroup.blogapp.services;

import com.apgroup.blogapp.dto.UserDto;

import java.util.List;

public interface UserService {

    //Methods
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
