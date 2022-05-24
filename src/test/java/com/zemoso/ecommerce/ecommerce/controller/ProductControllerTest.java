package com.zemoso.ecommerce.ecommerce.controller;

import com.zemoso.ecommerce.ecommerce.entity.Product;
import com.zemoso.ecommerce.ecommerce.entity.User;
import com.zemoso.ecommerce.ecommerce.jparepository.ProductRepository;
import com.zemoso.ecommerce.ecommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductControllerTest {

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
    void getAllProductsTest()
    {
        when(productRepository.findAll()).thenReturn(
                Stream.of(
                        product1,
                        product2
                ).collect(Collectors.toList()));

        Assertions.assertEquals(2, productRepository.findAll().size());
    }

    @Test
    void getProductTest()
    {
        int id=1;

        when(productRepository.findById(id)).thenReturn(
                Optional.of(product1)
        );

        Assertions.assertEquals("Wallet", productService.findById(id).getProductName());
    }


    @Test
    void getAdminProductsTest()
    {
        user.setProducts(new ArrayList<>());
        user.getProducts().add(product1);

        Assertions.assertEquals(1, user.getProducts().size());
    }

    @Test
    void showFormAddTest()
    {
        ProductController productController = new ProductController();
        String response = productController.showFormAdd(model);
        Assertions.assertEquals("product-form",response);
    }

    @Test
    void showFormUpdateTest()
    {
        int id=1;

        when(productRepository.findById(id)).thenReturn(
                Optional.of(product1)
        );

        Product product = productService.findById(id);
        product.setPrice(500.00);
        Assertions.assertEquals(500.00, product.getPrice());
    }

    @Test
    void saveProductControllerTest()
    {
        productService.save(product1);
        verify(productRepository, times(1)).save(product1);
    }
}
