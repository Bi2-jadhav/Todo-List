package com.renuka.todoList.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todolist")
public class TodoEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String des;

    private boolean completed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserAuthEntity getUser() {
        return user;
    }

    public void setUser(UserAuthEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private UserAuthEntity user;




    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDes() { return des; }
    public void setDes(String des) { this.des = des; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
