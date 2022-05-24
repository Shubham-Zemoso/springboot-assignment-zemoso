package com.zemoso.ecommerce.ecommerce.sevice;

import com.zemoso.ecommerce.ecommerce.entity.User;
import com.zemoso.ecommerce.ecommerce.jparepository.UserRepository;
import com.zemoso.ecommerce.ecommerce.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findUserByIdTest() {
        String id= "John";

        when(userRepository.findById(id)).thenReturn(
                Optional.of(user)
        );

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", userService.findById(id).getPassword());
    }

    @Test
    void saveUserTest() {
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getCurrentUserTest() {
        Assertions.assertEquals(null, userService.getCurrentUser());
    }
}
