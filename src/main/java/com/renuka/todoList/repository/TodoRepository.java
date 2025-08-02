package com.renuka.todoList.repository;

import com.renuka.todoList.entity.TodoEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntry,Long> {

}
