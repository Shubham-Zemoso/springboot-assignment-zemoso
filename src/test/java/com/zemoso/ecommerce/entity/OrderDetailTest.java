package com.zemoso.ecommerce.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);

    OrderDetail order = new OrderDetail(cart, user, LocalDateTime.now());

    @Test
    void setOrderIdTest() {
        order.setId(1);

        Assertions.assertEquals(1, order.getId());
    }

    @Test
    void setOrderCartTest() {
        order.setCart(cart);

        Assertions.assertEquals(200.00, order.getCart().getTotal());
    }

    @Test
    void setOrderUserTest() {
        order.setUser(user);

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", order.getUser().getPassword());
    }

    @Test
    void setOrderOrderedOn() {
        order.setOrderedOn(LocalDateTime.now());

        Assertions.assertEquals(LocalDateTime.now().getDayOfWeek(), order.getOrderedOn().getDayOfWeek());
    }
}
