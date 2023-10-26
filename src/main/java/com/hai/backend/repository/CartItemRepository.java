package com.hai.backend.repository;

import com.hai.backend.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
    Boolean existsByCartIdAndProductId(int cartId, int productId);

    CartItemEntity findByCartIdAndProductId(int cartId, int productId);
}
