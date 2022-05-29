package com.zemoso.ecommerce.service;

import com.zemoso.ecommerce.entity.Cart;

public interface CartService {

    Cart findById(int id);

    void save(Cart cart);

    void deleteById(int id);
}
