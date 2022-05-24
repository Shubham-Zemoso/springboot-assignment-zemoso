package com.zemoso.ecommerce.ecommerce.sevice;

import com.zemoso.ecommerce.ecommerce.jparepository.CartItemRepository;
import com.zemoso.ecommerce.ecommerce.service.CartItemService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartItemRepository cartItemRepository;

    @Test
    void deleteCartItemTest() {
        int id = 1;
        cartItemRepository.deleteById(id);
        verify(cartItemRepository, times(1)).deleteById(id);
    }
}
