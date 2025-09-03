package com.renuka.todoList.repository;

import com.renuka.todoList.entity.TodoEntry;
import com.renuka.todoList.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntry,Long> {
    List<TodoEntry> findByUser(UserAuthEntity user);
}
