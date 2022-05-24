package com.zemoso.ecommerce.ecommerce.sevice;

import com.zemoso.ecommerce.ecommerce.entity.*;
import com.zemoso.ecommerce.ecommerce.jparepository.OrderRepository;
import com.zemoso.ecommerce.ecommerce.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    Product product2 = new Product("Watch", "A Wrist watch", 500.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);

    OrderDetail order = new OrderDetail(cart, user, LocalDateTime.now());

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void findOrderByIdTest() {
        int id= 1;

        when(orderRepository.findById(id)).thenReturn(
                Optional.of(order)
        );

        Assertions.assertEquals(2, orderService.findById(id).getCart().getTotalItems());
    }

    @Test
    void saveOrderTest() {
        orderService.save(order);
        verify(orderRepository, times(1)).save(order);
    }
}
