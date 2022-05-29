package com.zemoso.ecommerce.service;

import com.zemoso.ecommerce.entity.User;

public interface UserService {

    User findById(String id);

    void save(User user);

    User getCurrentUser();
}
