package com.zemoso.ecommerce.ecommerce.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductTest {

    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);

    @Test
    void setProductIdTest() {
        product1.setId(1);

        Assertions.assertEquals(1, product1.getId());
    }

    @Test
    void setProductNameTest() {
        product1.setProductName("Wallet");

        Assertions.assertEquals("Wallet", product1.getProductName());
    }

    @Test
    void setProductPriceTest() {
        product1.setPrice(100.00);

        Assertions.assertEquals(100.00, product1.getPrice());
    }

    @Test
    void setProductDescriptionTest() {
        product1.setDescription("A Black Wallet");

        Assertions.assertEquals("A Black Wallet", product1.getDescription());
    }

    @Test
    void setProductUserTest() {
        product1.setUser(user);

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", product1.getUser().getPassword());
    }
}