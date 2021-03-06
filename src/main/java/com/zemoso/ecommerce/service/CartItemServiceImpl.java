package com.zemoso.ecommerce.service;

import com.zemoso.ecommerce.jparepository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService{
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository theCartItemRepository) {
        cartItemRepository = theCartItemRepository;
    }

    @Override
    public void deleteById(int id) {
        cartItemRepository.deleteById(id);
    }
}
