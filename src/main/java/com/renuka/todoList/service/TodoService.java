package com.renuka.todoList.service;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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

    public List<TodoEntry> getAllTask(){
        return todoRepository.findAll();
    }

    public TodoEntry getTaskById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
    }

    public TodoEntry updateTodo(Long id, TodoEntry todoDetails){
        TodoEntry existingTask =  todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with this id " + id));
        existingTask.setTitle(todoDetails.getTitle());
        existingTask.setDes(todoDetails.getDes());
        existingTask.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(existingTask);
    }

    public TodoEntry deleteTaskById(Long id){
        TodoEntry existingTask = todoRepository.findById(id).orElseThrow(()-> new RuntimeException("task not found with this id " + id));
        todoRepository.deleteById(id); //deleteById returns void
        return existingTask;
    }

}
