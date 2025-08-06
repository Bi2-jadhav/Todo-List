package com.renuka.todoList.controller;

import com.renuka.todoList.dto.LoginRequest;
import com.renuka.todoList.dto.SignupRequest;
import com.renuka.todoList.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignupRequest req){
        String result = userAuthService.register(req);
        return result.equals("User Registered") ?
                ResponseEntity.ok(result) :
                ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody LoginRequest req){
        String res = userAuthService.login(req);
        return res.startsWith("Login") ?
                ResponseEntity.ok(res) :
                ResponseEntity.badRequest().body(res);
    }
}
