package com.zemoso.ecommerce.sevice;

import com.zemoso.ecommerce.entity.Product;
import com.zemoso.ecommerce.entity.User;
import com.zemoso.ecommerce.jparepository.ProductRepository;
import com.zemoso.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    Product product2 = new Product("Watch", "A Wrist watch", 500.00);


    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Mock
    private Model model;


    @Test
    void findAllProductsTest() {
        when(productRepository.findAll()).thenReturn(
                Stream.of(
                        product1,
                        product2
                ).collect(Collectors.toList()));

        Assertions.assertEquals(2, productRepository.findAll().size());

    }

    @Test
    void findProductByIdTest() {
        int id=1;

        when(productRepository.findById(id)).thenReturn(
                Optional.of(product1)
        );

        Assertions.assertEquals("Wallet", productService.findById(id).getProductName());
    }

    @Test
    void saveProductTest() {
        productRepository.save(product1);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    void deleteProductTest() {
        int id = 1;
        productService.deleteById(id);
        verify(productRepository, times(1)).deleteById(id);
    }
}
