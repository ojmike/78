package com.example.task.authenticationservice.service;

import com.example.task.authenticationservice.dto.UserDto;
import com.example.task.authenticationservice.model.User;

import java.util.List;

public interface UserService {
    User getUser(Long userId);
    List<User> getUsers();
    User registerUser(UserDto userDto);
    String deleteUser(Long userId);
    User updateUser(Long userId,UserDto userDto);
    User saveUser(User user, UserDto userDto);
}
