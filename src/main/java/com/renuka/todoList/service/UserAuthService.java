package com.renuka.todoList.service;

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

    public String register(SignupRequest req) {
        if (!req.getPassword().equals(req.getConfirmPassword()))
            return "Passwords do not match";

        if (userAuthRepo.findByEmail(req.getEmail()).isPresent())
            return "Email already exists";

        UserAuthEntity user = new UserAuthEntity();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userAuthRepo.save(user);

        return "User registered";
    }

    public String login(LoginRequest req) {
        Optional<UserAuthEntity> userOpt = userAuthRepo.findByEmail(req.getEmail());
        if (userOpt.isEmpty())
            return "Invalid email";

        UserAuthEntity user = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            return "Invalid password";

        String token = jwtUtil.generateToken(user.getEmail());
        return token;
    }
}
