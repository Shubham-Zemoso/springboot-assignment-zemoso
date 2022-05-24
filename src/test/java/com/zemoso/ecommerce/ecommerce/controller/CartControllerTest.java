package com.zemoso.ecommerce.ecommerce.controller;

import com.zemoso.ecommerce.ecommerce.entity.Cart;
import com.zemoso.ecommerce.ecommerce.entity.CartItem;
import com.zemoso.ecommerce.ecommerce.entity.Product;
import com.zemoso.ecommerce.ecommerce.entity.User;
import com.zemoso.ecommerce.ecommerce.jparepository.CartItemRepository;
import com.zemoso.ecommerce.ecommerce.service.CartItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartControllerTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);
    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartItemRepository cartItemRepository;

    @Test
    void getCartControllerTest()
    {
        user.setCart(cart);

        Assertions.assertEquals(2, user.getCart().getTotalItems());

    }

    @Test
    void addToCartControllerTest()
    {
        cartItem.setQuantity(cartItem.getQuantity()+1);

        Assertions.assertEquals(3, cartItem.getQuantity());


    }

    @Test
    void decreaseInCartControllerTest()
    {
        cartItem.setQuantity(cartItem.getQuantity()-1);

        Assertions.assertEquals(1, cartItem.getQuantity());
    }

    @Test
    void deleteCartItemControllerTest() {
        int id = 1;
        cartItemService.deleteById(id);
        verify(cartItemRepository, times(1)).deleteById(id);
    }
}
