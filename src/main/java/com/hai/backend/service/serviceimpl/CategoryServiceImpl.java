package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.CategoryEntity;
import com.hai.backend.repository.CategoryRepository;
import com.hai.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryEntity> getAll() {
        return categoryRepository.findAllByIsDeleteFalse();
    }

    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity getCategoryById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public CategoryEntity updateCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public Boolean check(int id) {
        return categoryRepository.existsById(id);
    }


}
