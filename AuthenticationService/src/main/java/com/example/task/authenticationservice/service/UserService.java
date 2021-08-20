package com.example.task.authenticationservice.service;

import com.example.task.authenticationservice.dto.UserDto;
import com.example.task.authenticationservice.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserEntity getUser(Long userId);
    List<UserEntity> getUsers();
    UserEntity registerUser(UserDto userDto);
    String deleteUser(Long userId);
    UserEntity updateUser(Long userId, UserDto userDto);
    UserEntity saveUser(UserEntity userEntity, UserDto userDto);
    UserEntity getUserDetailsByEmail(String email);
}
