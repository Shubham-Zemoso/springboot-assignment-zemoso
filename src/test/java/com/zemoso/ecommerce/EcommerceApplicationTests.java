package com.zemoso.ecommerce;
import com.zemoso.ecommerce.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EcommerceApplicationTests {

    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);

    @Test void applicationContextTest() {

        EcommerceApplication.main(new String[] {});
        Assertions.assertEquals("Wallet", product1.getProductName());
    }

}
