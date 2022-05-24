package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.jparepository.UserRepository;
import com.zemoso.ecommerce.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public User findById(String username) {
        Optional<User> result =  userRepository.findById(username);
        User user = null;
        if(result.isPresent())
        {
            user = result.get();
        }



        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        if(principal==null) {
            return null;
        }
        User user;
        String username = principal.getName();
        user = findById(username);
        return user;
    }
}
