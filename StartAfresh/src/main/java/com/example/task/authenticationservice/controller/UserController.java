package com.example.task.authenticationservice.controller;

import com.example.task.authenticationservice.dto.UserDto;
import com.example.task.authenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);

    }

    @GetMapping("/users")
    public  ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(userId,userDto), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }
}
