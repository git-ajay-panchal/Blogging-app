package com.apgroup.blogapp.controller;

import com.apgroup.blogapp.dto.UserDto;
import com.apgroup.blogapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
       UserDto userDtoCreated = userService.createUser(userDto);
       return new ResponseEntity<>(userDtoCreated, HttpStatus.CREATED);
    }

    @PostMapping("/{uId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer uId){
        UserDto userDto1 = userService.updateUser(userDto,uId);
        return ResponseEntity.ok(userDto1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId){
        userService.deleteUser(uId);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }
    @GetMapping("/{uId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer uId){
       UserDto userDto = userService.getUserById(uId);
       return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userDtoList,HttpStatus.OK);
    }
}
