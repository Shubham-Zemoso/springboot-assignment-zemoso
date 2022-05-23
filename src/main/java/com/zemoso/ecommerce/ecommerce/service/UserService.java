package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.User;

public interface UserService {

    public User findById(String id);

    public void save(User user);

    public User getCurrentUser();
}
