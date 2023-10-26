package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.CartEntity;
import com.hai.backend.entity.CartItemEntity;
import com.hai.backend.entity.ProductEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.CartItemModal;
import com.hai.backend.modal.CartModal;
import com.hai.backend.repository.CartItemRepository;
import com.hai.backend.repository.CartRepository;
import com.hai.backend.repository.UserRepository;
import com.hai.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Object addToCart(CartModal cartModal) {
        int userId = cartModal.getUserId();
        boolean userExist = userRepository.existsById(userId);
        if (!userExist) {
            return false;
        }

        boolean cartExist = cartRepository.existsByUserId(userId);
        if (!cartExist) {
            CartEntity cart = new CartEntity();
            UserEntity user = new UserEntity();
            user.setId(userId);
            cart.setUser(user);

            cartRepository.save(cart);
        }

        CartEntity cart = cartRepository.findByUserId(userId);
        int cartId = cart.getId();

        List<CartItemModal> products = cartModal.getItems();
        for (CartItemModal product: products){
            boolean cartItemExist = cartItemRepository.existsByCartIdAndProductId(cartId, product.getProductId());
            if(cartItemExist){
                CartItemEntity cartItem = cartItemRepository.findByCartIdAndProductId(cartId, product.getProductId());
                cartItem.setQuantity(product.getQuantity());
                cartItemRepository.save(cartItem);
            }else{
                CartItemEntity cartItem = new CartItemEntity();
                CartEntity cartEntity = new CartEntity();
                cartEntity.setId(cartId);

                ProductEntity productEntity = new ProductEntity();
                productEntity.setId(product.getProductId());

                cartItem.setQuantity(product.getQuantity());
                cartItem.setCart(cartEntity);
                cartItem.setProduct(productEntity);
                cartItemRepository.save(cartItem);
            }
        }
        return null;
    }

    @Override
    public CartEntity getCartByUserId(int id) {
        CartEntity cart = cartRepository.findByUserId(id);

        List<CartItemEntity> cartItems = new ArrayList<>();
        for (CartItemEntity cartItem: cart.getCartItem()){
            if(cartItem.getQuantity() > 0){
                cartItems.add(cartItem);
            }
        }

        cart.setCartItem(cartItems);

        return cart;
    }
}
