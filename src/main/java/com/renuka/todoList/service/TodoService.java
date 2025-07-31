package com.renuka.todoList.service;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    public TodoEntry saveEntry(TodoEntry todoEntry){
        if (todoEntry.getCreatedAt() == null) {
            todoEntry.setCreatedAt(LocalDateTime.now());
        }
        TodoEntry saved = todoRepository.save(todoEntry);
        System.out.println("Saved: " + saved);
        return saved;
    }
}
