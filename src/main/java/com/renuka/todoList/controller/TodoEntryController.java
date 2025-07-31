package com.renuka.todoList.controller;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoEntryController {
    //create a new todo
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String home() {
        return "Todo App is running!";
    }

    @PostMapping
    public TodoEntry createTodo(@RequestBody TodoEntry todoEntry){
        return todoService.saveEntry(todoEntry);

    }
}
