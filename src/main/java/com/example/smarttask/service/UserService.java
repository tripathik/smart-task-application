package com.example.smarttask.service;

import com.example.smarttask.dto.UserDTO;
import com.example.smarttask.dto.response.UserResponseDTO;
import com.example.smarttask.entity.User;

import java.util.List;

public interface UserService {
    String createUser(User userData);
    UserDTO getUserById(long userId);
    List<UserResponseDTO> getAllUsers();

}
