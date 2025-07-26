package com.example.smarttask.controller.auth;

import com.example.smarttask.dto.UserDTO;
import com.example.smarttask.entity.User;
import com.example.smarttask.repository.UserRepository;
import com.example.smarttask.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtService.generateToken(userDetails.getUsername());
    }



}
