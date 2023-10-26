package com.hai.backend.service;

import com.hai.backend.entity.CartEntity;
import com.hai.backend.modal.CartModal;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Object addToCart(CartModal cartModal);

    CartEntity getCartByUserId(int id);
}
