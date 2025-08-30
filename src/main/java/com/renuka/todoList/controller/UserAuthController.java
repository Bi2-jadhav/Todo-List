package com.renuka.todoList.controller;

import com.renuka.todoList.dto.LoginRequest;
import com.renuka.todoList.dto.SignupRequest;
import com.renuka.todoList.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;


    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest req) {
        try {
            userAuthService.register(req);
            return ResponseEntity.ok("User Registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            return ResponseEntity.ok(userAuthService.login(req));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
