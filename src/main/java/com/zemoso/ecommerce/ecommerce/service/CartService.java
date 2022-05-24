package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.Cart;

public interface CartService {

    Cart findById(int id);

    void save(Cart cart);

    void deleteById(int id);
}
