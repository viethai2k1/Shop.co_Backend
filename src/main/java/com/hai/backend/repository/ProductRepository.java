package com.hai.backend.repository;

import com.hai.backend.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findAllByIsDeleteIsFalse();

    List<ProductEntity> findByCategories_Id(int id);

    @Query(value = "SELECT * from product where (name like %?1%  or description like %?1%)              and is_delete = false", nativeQuery = true)
    List<ProductEntity> searchByName(String query);

}
