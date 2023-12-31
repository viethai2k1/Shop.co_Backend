package com.hai.backend.repository;

import com.hai.backend.entity.CategoryEntity;
import com.hai.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByEmail(String email);

    List<UserEntity> findAllByIsDeleteFalse();

    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
