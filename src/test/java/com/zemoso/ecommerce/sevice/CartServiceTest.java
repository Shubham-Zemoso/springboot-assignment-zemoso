package com.zemoso.ecommerce.sevice;

import com.zemoso.ecommerce.entity.Cart;
import com.zemoso.ecommerce.entity.CartItem;
import com.zemoso.ecommerce.entity.Product;
import com.zemoso.ecommerce.entity.User;
import com.zemoso.ecommerce.jparepository.CartRepository;
import com.zemoso.ecommerce.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartServiceTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    Product product2 = new Product("Watch", "A Wrist watch", 500.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);


    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Test
    void findCartByIdTest() {
        int id=1;

        when(cartRepository.findById(id)).thenReturn(
                Optional.of(cart)
        );

        Assertions.assertEquals(200, cartService.findById(id).getTotal());
    }

    @Test
    void saveCartTest() {
        cartService.save(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void deleteCartTest() {
        int id = 1;
        cartService.deleteById(id);
        verify(cartRepository, times(1)).deleteById(id);
    }

}
