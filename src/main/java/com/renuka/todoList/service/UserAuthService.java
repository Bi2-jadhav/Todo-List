package com.renuka.todoList.service;

import com.renuka.todoList.dto.JwtResponse;
import com.renuka.todoList.dto.LoginRequest;
import com.renuka.todoList.dto.SignupRequest;
import com.renuka.todoList.entity.UserAuthEntity;
import com.renuka.todoList.repository.UserAuthRepository;
import com.renuka.todoList.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(SignupRequest req) {
        // Check empty fields
        if (req.getEmail() == null || req.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (req.getPassword() == null || req.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (req.getConfirmPassword() == null || req.getConfirmPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Confirm Password cannot be empty");
        }

        // Check password match
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Check email uniqueness
        if (userAuthRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Save user
        UserAuthEntity user = new UserAuthEntity();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userAuthRepo.save(user);
    }



    public JwtResponse login(LoginRequest req) {
        Optional<UserAuthEntity> userOpt = userAuthRepo.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) throw new RuntimeException("Invalid email");

        UserAuthEntity user = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        String token = jwtUtil.generateToken(user.getEmail());
        JwtResponse response = new JwtResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        return response;
    }


}
