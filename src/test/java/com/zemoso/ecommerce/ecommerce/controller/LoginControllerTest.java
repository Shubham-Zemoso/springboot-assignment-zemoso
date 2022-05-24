package com.zemoso.ecommerce.ecommerce.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginControllerTest {

    @Test
    void helloTest()
    {
        LoginController loginController=new LoginController();
        String response=loginController.hello();
        Assertions.assertEquals("hello",response);
    }

    @Test
    void showMyLoginPageTest()
    {
        LoginController loginController=new LoginController();
        String response=loginController.showMyLoginPage();
        Assertions.assertEquals("login-page",response);
    }

    @Test
    void accessDeniedTest()
    {
        LoginController loginController=new LoginController();
        String response=loginController.accessDenied();
        Assertions.assertEquals("access-denied",response);
    }

}
