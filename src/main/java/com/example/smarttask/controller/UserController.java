package com.example.smarttask.controller;

import com.example.smarttask.dto.UserDTO;
import com.example.smarttask.dto.response.UserResponseDTO;
import com.example.smarttask.entity.User;
import com.example.smarttask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("getAllUsers")
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("register-user")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);
    }


    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        return userService.getUserById(userId);
    }
}
