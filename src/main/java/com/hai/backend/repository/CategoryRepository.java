package com.hai.backend.repository;

import com.hai.backend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findAllByStatusTrue();
    List<CategoryEntity> findAllByIsDeleteFalse();
}
