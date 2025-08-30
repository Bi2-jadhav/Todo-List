package com.renuka.todoList.controller;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.service.TodoService;
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

    //create a new todo
    @PostMapping("/createTask")
    public ResponseEntity<TodoEntry> createTask(@RequestBody TodoEntry todo) {
        TodoEntry createdTodo = todoService.saveEntry(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED); // 201 Created
    }
    @GetMapping("/allTask")
    public ResponseEntity<List<TodoEntry>> getAllTask(){
        return ResponseEntity.ok(todoService.getAllTask());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoEntry> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(todoService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoEntry> updateTask(@PathVariable long id, @RequestBody TodoEntry todoDetails){
        TodoEntry updatedTodo = todoService.updateTodo(id,todoDetails);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoEntry> deleteTaskById(@PathVariable long id){
        TodoEntry deleted = todoService.deleteTaskById(id);
        return ResponseEntity.ok(deleted);
    }

}
