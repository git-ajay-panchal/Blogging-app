package com.apgroup.blogapp.services.impl;

import com.apgroup.blogapp.dto.UserDto;
import com.apgroup.blogapp.entities.User;
import com.apgroup.blogapp.exceptions.ResourceNotFoundException;
import com.apgroup.blogapp.repsitory.UserRepo;
import com.apgroup.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

         User user = userRepo.save(userDtoToUser(userDto));
        return userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepo.save(user);
        return userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
//        Optional<User> user = userRepo.findById(userId);
//        User user1 = user.get();
        // findById returns Optional<T> type
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));

        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepo.findAll();
        List<UserDto> userDtos = userList.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id", userId));
        userRepo.delete(user);

    }
    private User userDtoToUser(UserDto userDto){
        return this.modelMapper.map(userDto, User.class);
    }
    private UserDto userToUserDto(User user){
        return this.modelMapper.map(user,UserDto.class);
    }

/*
    private User userDtoToUser(UserDto userDto){
        User user=new User(userDto.getId(), userDto.getName(), userDto.getEmail(),
                userDto.getPassword(), userDto.getAbout());
        return user;
    }
    private UserDto userToUserDto(User user){
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
 */
}
