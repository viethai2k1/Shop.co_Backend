package com.hai.backend.repository;

import com.hai.backend.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findAllByProductIdAndIsDeleteFalse(int id);
    List<ReviewEntity> findAllByProductIdAndIsDeleteFalseAndStatusTrue(int id);
    List<ReviewEntity> findAllByIsDeleteFalse();
    ReviewEntity findByIdAndIsDeleteFalse(int id);
}
