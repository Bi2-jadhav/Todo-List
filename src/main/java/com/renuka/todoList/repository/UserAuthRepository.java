package com.renuka.todoList.repository;

import com.renuka.todoList.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity,Long> {
    Optional<UserAuthEntity>findByEmail(String email); // acts as select * from UserAuthorityEntity where email = ?
}
