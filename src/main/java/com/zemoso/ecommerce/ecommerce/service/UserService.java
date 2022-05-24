package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.User;

public interface UserService {

    User findById(String id);

    void save(User user);

    User getCurrentUser();
}
