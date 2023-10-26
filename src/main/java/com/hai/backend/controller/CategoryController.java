package com.hai.backend.controller;

import com.hai.backend.entity.CategoryEntity;
import com.hai.backend.entity.ProductEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.service.CategoryService;
import com.hai.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ApiReponse<List<CategoryEntity>> getAll() {
        try {
            return new ApiReponse<>(true, categoryService.getAll());
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }

    }

    @PostMapping("/add")
    public ApiReponse<CategoryEntity> saveProduct(@RequestBody CategoryEntity categoryEntity) {
        try {
            return new ApiReponse<>(true, categoryService.saveCategory(categoryEntity));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }

    }

    @GetMapping("/{id}")
    public ApiReponse<CategoryEntity> getCategoryById(@PathVariable int id) {
        try {
            return new ApiReponse<>(true, categoryService.getCategoryById(id));
        } catch (Exception e) {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/update")
    public ApiReponse<CategoryEntity> updateCategory(@RequestBody CategoryEntity categoryEntity) {
        if (categoryService.check(categoryEntity.getId())) {
            return new ApiReponse<>(true, categoryService.updateCategory(categoryEntity));
        } else {
            return new ApiReponse<>(false, null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiReponse<CategoryEntity> delete(@PathVariable int id){
        if (categoryService.check(id)) {
            CategoryEntity category = categoryService.getCategoryById(id);
            category.setDelete(true);
            categoryService.updateCategory(category);

            return new ApiReponse<>(true, category);
        } else {
            return new ApiReponse<>(false, null);
        }
    }

    @PutMapping("/status/{id}")
    public ApiReponse<CategoryEntity> updateStatusCategory(@PathVariable int id) {
        if (categoryService.check(id)) {
            CategoryEntity category = categoryService.getCategoryById(id);
            category.setStatus(!category.isStatus());
            categoryService.saveCategory(category);

            return new ApiReponse<>(true, category);
        } else {
            return new ApiReponse<>(false, null);
        }
    }




}
