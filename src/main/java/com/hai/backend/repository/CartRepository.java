package com.hai.backend.repository;

import com.hai.backend.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Boolean existsByUserId(int id);
    CartEntity findByUserId(int id);
}
