package com.zemoso.ecommerce.ecommerce.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    Product product2 = new Product("Watch", "A Wrist watch", 500.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);

    OrderDetail order = new OrderDetail(cart, user, LocalDateTime.now());

    @Test
    void setUserUserNameTest() {
        user.setUsername("john");

        Assertions.assertEquals("john", user.getUsername());
    }

    @Test
    void setUserPasswordTest() {
        user.setPassword("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K");

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", user.getPassword());
    }

    @Test
    void setUserEnabledTest() {
        user.setEnabled(1);

        Assertions.assertEquals(1, user.getEnabled());
    }

    @Test
    void setUserProductsTest() {
        user.setProducts(Arrays.asList(product1, product2));

        Assertions.assertEquals(2, user.getProducts().size());
    }

    @Test
    void setUserUserCartTest() {
        user.setCart(cart);

        Assertions.assertEquals(200.00, user.getCart().getTotal());
    }

    @Test
    void setUserOrderDetailsTest() {
        user.setOrderDetails(Arrays.asList(order));

        Assertions.assertEquals(1, user.getOrderDetails().size());
    }

}
