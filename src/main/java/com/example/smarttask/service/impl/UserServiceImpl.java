package com.example.smarttask.service.impl;

import com.example.smarttask.dto.UserDTO;
import com.example.smarttask.dto.response.UserResponseDTO;
import com.example.smarttask.entity.User;
import com.example.smarttask.exception.ResourceNotFoundException;
import com.example.smarttask.repository.UserRepository;
import com.example.smarttask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();

        return userList.stream().map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build()
        ).toList();
    }

    @Override
    public String createUser(User userData) {
        User userToBeSave = User.builder()
                .username(userData.getUsername())
                .role(userData.getRole())
                .tasks(userData.getTasks())
                .password(passwordEncoder.encode(userData.getPassword()))
                .build();

        userRepository.save(userToBeSave);

        return "User have been created Successfully !";

    }

    @Override
    public UserDTO getUserById(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return modelMapper.map(user, UserDTO.class);
        }
        throw new ResourceNotFoundException("User with ID " + userId + " not found");
    }
}
