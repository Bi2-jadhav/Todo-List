package com.renuka.todoList.service;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.entity.UserAuthEntity;
import com.renuka.todoList.repository.TodoRepository;
import com.renuka.todoList.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserAuthRepository userAuthRepo; // to fetch user by email (from JWT)


    private UserAuthEntity getUserByEmail(String email) {      // helper method
        return userAuthRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }




    public TodoEntry saveEntry(TodoEntry todo, String email) {
        UserAuthEntity user = userAuthRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        todo.setUser(user);
        todo.setCreatedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public List<TodoEntry> getUserTasks(String email) {
        UserAuthEntity user = userAuthRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return todoRepository.findByUser(user);
    }


    public List<TodoEntry> getAllTask(){
        return todoRepository.findAll();
    }


//    public TodoEntry getTaskById(Long id) {
//        return todoRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Task with ID " + id + " not found"));
//    }




    // Update task (only if owner matches)
    public TodoEntry updateTask(Long id, TodoEntry todoDetails, String email) {
        UserAuthEntity user = getUserByEmail(email);

        TodoEntry todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to update this task");
        }

        todo.setTitle(todoDetails.getTitle());
        todo.setDes(todoDetails.getDes());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(todo);
    }

    // Delete task (only if owner matches)
    public void deleteTaskById(Long id, String email) {
        UserAuthEntity user = getUserByEmail(email);

        TodoEntry todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!todo.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to delete this task");
        }

        todoRepository.delete(todo);
    }
}
