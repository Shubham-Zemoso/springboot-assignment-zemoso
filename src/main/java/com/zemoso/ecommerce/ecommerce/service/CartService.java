package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.Cart;

public interface CartService {

    public Cart findById(int id);

    public void save(Cart cart);

    public void deleteById(int id);
}
