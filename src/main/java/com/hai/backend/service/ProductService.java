package com.hai.backend.service;

import com.hai.backend.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductEntity> getAll();
    List<ProductEntity> getAllByCategory(int id);

    List<ProductEntity> search(String query);

    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProductById(int id);

    Boolean check(int id);
}
