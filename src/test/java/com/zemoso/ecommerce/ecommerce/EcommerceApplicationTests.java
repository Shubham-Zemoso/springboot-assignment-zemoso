package com.zemoso.ecommerce.ecommerce;

import com.zemoso.ecommerce.ecommerce.controller.LoginController;
import com.zemoso.ecommerce.ecommerce.controller.ProductController;
import com.zemoso.ecommerce.ecommerce.jparepository.*;
import com.zemoso.ecommerce.ecommerce.entity.*;
import com.zemoso.ecommerce.ecommerce.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EcommerceApplicationTests {

    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);

    @Test void applicationContextTest() {

        EcommerceApplication.main(new String[] {});
        Assertions.assertEquals("Wallet", product1.getProductName());
    }

}
