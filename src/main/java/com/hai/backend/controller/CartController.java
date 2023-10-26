package com.hai.backend.controller;

import com.hai.backend.entity.CartEntity;
import com.hai.backend.modal.ApiReponse;
import com.hai.backend.modal.CartModal;
import com.hai.backend.modal.ProductCart;
import com.hai.backend.repository.CartRepository;
import com.hai.backend.service.CartService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("")
    public ApiReponse<List<CartEntity>> getAll(){
        return null;
    }

    @PostMapping("/add")
    public ApiReponse<Object> addToCart(@RequestBody CartModal cartModal){
        return new ApiReponse<>(true, cartService.addToCart(cartModal));
    }

    @GetMapping("/{id}")
    public ApiReponse<CartEntity> getCartByUserId(@PathVariable int id){
        try{
            return new ApiReponse<>(true, cartService.getCartByUserId(id));
        }catch (Exception e){
            return new ApiReponse<>(false, null);
        }
    }
}
