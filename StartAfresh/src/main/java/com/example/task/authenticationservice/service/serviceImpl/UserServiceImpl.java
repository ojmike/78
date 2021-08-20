package com.example.task.authenticationservice.service.serviceImpl;

import com.example.task.authenticationservice.dto.UserDto;
import com.example.task.authenticationservice.exception.ApiRequestException;
import com.example.task.authenticationservice.model.UserEntity;
import com.example.task.authenticationservice.repository.UserRepository;
import com.example.task.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity getUser(Long userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            return userEntity;
        }
        else{
            throw new ApiRequestException("User does not exist");
        }
    }

    @Override
    public List<UserEntity> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        if(userEntities.isEmpty()){
            throw new ApiRequestException("No user found in the database");
        }
        return userEntities;
    }

    @Override
    public UserEntity registerUser(UserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userDto.getEmail());
        UserEntity userEntity = new UserEntity();
        if(optionalUser.isEmpty()) {
            saveUser(userEntity,userDto);
            return userRepository.save(userEntity);
        }else{
            throw new ApiRequestException("User with email "+ userDto.getEmail()+" already exist");
        }
    }



    @Override
    public String deleteUser(Long userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            userRepository.delete(userEntity);
            return "User deleted successfully";
             }
        else{
            throw new ApiRequestException("User does not exist");
        }
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long userId, UserDto userDto) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            UserEntity userEntity = userOptional.get();
            return saveUser(userEntity,userDto);
        }else {
            throw new ApiRequestException("User does not exist");
        }

    }

    @Override
    public UserEntity saveUser(UserEntity userEntity, UserDto userDto) {
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return userEntity;
    }

    @Override
    public UserEntity getUserDetailsByEmail(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            return userEntity;
             }
        else{
            throw new ApiRequestException("User does not exist");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(s);
        if(optionalUser.isPresent()){
            UserEntity userEntity = optionalUser.get();
            return new User(userEntity.getEmail(), userEntity.getPassword(),true,true,true,true,new ArrayList<>()) ;
        }
        else{
            throw new ApiRequestException("User does not exist");
        }
    }
}
