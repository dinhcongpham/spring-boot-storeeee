package com.jokerP.store.services;

import com.jokerP.store.exceptions.NotFoundException;
import com.jokerP.store.dtos.CartDto;
import com.jokerP.store.dtos.CartItemDto;
import com.jokerP.store.dtos.UpdateCartItemRequest;
import com.jokerP.store.entities.Cart;
import com.jokerP.store.mappers.CartMapper;
import com.jokerP.store.repositories.CartRepository;
import com.jokerP.store.repositories.ProductRepository;
import com.jokerP.store.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDto getCart(UUID cardId) {
        var cart = cartRepository.getCartWithItems(cardId).orElse(null);
        if (cart == null) {
            throw new NotFoundException(Constants.Message.CART_NOT_FOUND);
        }

        return cartMapper.toDto(cart);
    }

    public CartDto createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(UUID cartId, Long productId) {
        var cart =  cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new NotFoundException(Constants.Message.CART_NOT_FOUND);
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new NotFoundException(Constants.Message.PRODUCT_NOT_FOUND_IN_CART);
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }

    public CartItemDto updateCart(UUID cartId, Long productId, UpdateCartItemRequest updateCartItemRequest) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new NotFoundException(Constants.Message.CART_NOT_FOUND);
        }

        var cartItem = cart.getItem(productId);

        if (cartItem == null) {
            throw new NotFoundException(Constants.Message.PRODUCT_NOT_FOUND_IN_CART);
        }

        cartItem.setQuantity(updateCartItemRequest.getQuantity());
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItem(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new NotFoundException(Constants.Message.CART_NOT_FOUND);
        }

        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart =  cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new NotFoundException(Constants.Message.CART_NOT_FOUND);
        }

        cart.clear();
        cartRepository.save(cart);
    }
}
