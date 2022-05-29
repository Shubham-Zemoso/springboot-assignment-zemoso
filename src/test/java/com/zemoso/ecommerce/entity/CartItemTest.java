package com.zemoso.ecommerce.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartItemTest {
    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);

    @Test
    void setCartItemIdTest() {
        cartItem.setId(1);

        Assertions.assertEquals(1, cartItem.getId());
    }

    @Test
    void setCartItemProductTest() {
        cartItem.setProduct(product1);

        Assertions.assertEquals("A Black Wallet", cartItem.getProduct().getDescription());
    }

    @Test
    void setCartItemCartTest() {
        cartItem.setCart(cart);

        Assertions.assertEquals(200.00, cartItem.getCart().getTotal());
    }

    @Test
    void setCartItemQuantity() {
        cartItem.setQuantity(2);

        Assertions.assertEquals(2, cartItem.getQuantity());
    }
}
