package com.renuka.todoList.controller;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.service.TodoService;
import com.renuka.todoList.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")

public class TodoEntryController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private JwtUtil jwtUtil;

    private String getEmailFromToken(String token) {
        return jwtUtil.extractUsername(token.substring(7)); // remove "Bearer "
    }

    @PostMapping("/createTask")
    public ResponseEntity<TodoEntry> createTask(
            @RequestBody TodoEntry todo,
            @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractUsername(token.substring(7)); // remove "Bearer "
        TodoEntry createdTodo = todoService.saveEntry(todo, email);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping("/allTask")
    public ResponseEntity<List<TodoEntry>> getAllTasks(
            @RequestHeader("Authorization") String token) {

        String email = getEmailFromToken(token);
        return ResponseEntity.ok(todoService.getUserTasks(email));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TodoEntry> updateTask(
            @PathVariable long id,
            @RequestBody TodoEntry todoDetails,
            @RequestHeader("Authorization") String token) {

        String email = getEmailFromToken(token);
        TodoEntry updatedTodo = todoService.updateTask(id, todoDetails, email);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(
            @PathVariable long id,
            @RequestHeader("Authorization") String token) {

        String email = getEmailFromToken(token);
        todoService.deleteTaskById(id, email);
        return ResponseEntity.ok("Task deleted successfully");
    }









}
