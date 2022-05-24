package com.zemoso.ecommerce.ecommerce.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartTest {
    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);

    @Test
    void setCartIdTest() {
        cart.setId(1);

        Assertions.assertEquals(1, cart.getId());
    }

    @Test
    void setUserTest() {
        cart.setUser(user);
        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", cart.getUser().getPassword());

    }

    @Test
    void setCartCartItemsTest() {
        cart.setCartItems(Arrays.asList(cartItem));

        Assertions.assertEquals(1, cart.getCartItems().size());
    }

    @Test
    void setCartTotalTest() {
        cart.setTotal(200.00);

        Assertions.assertEquals(200.00, cart.getTotal());
    }

    @Test
    void setCartTotalItems() {
        cart.setTotalItems(2);

        Assertions.assertEquals(2, cart.getTotalItems());
    }
}
