package com.hai.backend.service;

import com.hai.backend.entity.CategoryEntity;

import java.util.List;

public interface CategoryService  {
    List<CategoryEntity> getAll();

    CategoryEntity saveCategory(CategoryEntity category);

    CategoryEntity getCategoryById(int id);

    CategoryEntity updateCategory(CategoryEntity categoryEntity);

    Boolean check(int id);
 }
