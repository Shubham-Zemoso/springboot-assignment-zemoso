package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.jparepository.CartRepository;
import com.zemoso.ecommerce.ecommerce.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository theCartRepository) {
        cartRepository = theCartRepository;
    }

    @Override
    public Cart findById(int id) {
        Optional<Cart> result = cartRepository.findById(id);
        Cart cart = null;
        if(result.isPresent()) {
            cart = result.get();
        }
        return cart;
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }
}
