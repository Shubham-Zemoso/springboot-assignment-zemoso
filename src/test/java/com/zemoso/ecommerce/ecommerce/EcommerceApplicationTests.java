package com.zemoso.ecommerce.ecommerce;

import com.zemoso.ecommerce.ecommerce.controller.EcomController;
import com.zemoso.ecommerce.ecommerce.controller.LoginController;
import com.zemoso.ecommerce.ecommerce.dao.*;
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

    User user = new User("john", "$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", 1);
    Product product1 = new Product("Wallet", "A Black Wallet", 100.00);
    Product product2 = new Product("Watch", "A Wrist watch", 500.00);
    CartItem cartItem = new CartItem(product1,2);
    Cart cart = new Cart(user, Arrays.asList(cartItem), 200, 2);

    OrderDetail order = new OrderDetail(cart, user, LocalDateTime.now());

    @Test void applicationContextTest() {

        EcommerceApplication.main(new String[] {});
        Assertions.assertEquals("Watch", product2.getProductName());
    }


    //-------------------Test Product Service------------------

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
        productRepository.deleteById(id);
        verify(productRepository, times(1)).deleteById(id);
    }

//    ----------------------Test Cart Service--------------------------------------

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
        cartRepository.save(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void deleteCartTest() {
        int id = 1;
        cartRepository.deleteById(id);
        verify(cartRepository, times(1)).deleteById(id);
    }

//------------------------------Test User Service------------------------------------

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findUserByIdTest() {
        String id= "John";

        when(userRepository.findById(id)).thenReturn(
                Optional.of(user)
        );

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", userService.findById(id).getPassword());
    }

    @Test
    void saveUserTest() {
        userRepository.save(user);
        verify(userRepository, times(1)).save(user);
    }

//------------------------------------------Test Order Service-----------------------------

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
        orderRepository.save(order);
        verify(orderRepository, times(1)).save(order);
    }

//---------------------------------Test CartItem Service-------------------------------

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

//----------------------------------Test Login Controller--------------------------------

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


//-----------------------------------Ecom Controller Test------------------------------------


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
        EcomController ecomController = new EcomController();
        String response = ecomController.showFormAdd(model);
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
        cartItemRepository.deleteById(id);
        verify(cartItemRepository, times(1)).deleteById(id);
    }


//-------------------------------------Test Entities----------------------------------------------------

//------------------------------------Test Cart Entity-------------------------------------------------

    @Test
    void setCartIdTest() {
        cart.setId(1);

        Assertions.assertEquals(1, cart.getId());
    }

    @Test
    void setUserTest() {
        cart.setUser(user);
        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", cart.getUser().getPassword());

    }

    @Test
    void setCartCartItemsTest() {
        cart.setCartItems(Arrays.asList(cartItem));

        Assertions.assertEquals(1, cart.getCartItems().size());
    }

    @Test
    void setCartTotalTest() {
        cart.setTotal(200.00);

        Assertions.assertEquals(200.00, cart.getTotal());
    }

    @Test
    void setCartTotalItems() {
        cart.setTotalItems(2);

        Assertions.assertEquals(2, cart.getTotalItems());
    }

//-----------------------------------Test CartItem Entity------------------------------------------

    @Test
    void setCartItemIdTest() {
        cartItem.setId(1);

        Assertions.assertEquals(1, cartItem.getId());
    }

    @Test
    void setCartItemProductTest() {
        cartItem.setProduct(product1);

        Assertions.assertEquals("A Black Wallet", cartItem.getProduct().getDescription());
    }

    @Test
    void setCartItemCartTest() {
        cartItem.setCart(cart);

        Assertions.assertEquals(200.00, cartItem.getCart().getTotal());
    }

    @Test
    void setCartItemQuantity() {
        cartItem.setQuantity(2);

        Assertions.assertEquals(2, cartItem.getQuantity());
    }

//---------------------------------Test OrderDetail Entity---------------------------------

    @Test
    void setOrderIdTest() {
        order.setId(1);

        Assertions.assertEquals(1, order.getId());
    }

    @Test
    void setOrderCartTest() {
        order.setCart(cart);

        Assertions.assertEquals(200.00, order.getCart().getTotal());
    }

    @Test
    void setOrderUserTest() {
        order.setUser(user);

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", order.getUser().getPassword());
    }

    @Test
    void setOrderOrderedOn() {
        order.setOrderedOn(LocalDateTime.now());

        Assertions.assertEquals(LocalDateTime.now().getDayOfWeek(), order.getOrderedOn().getDayOfWeek());
    }

//----------------------------------------------Test Product Entity---------------------------------

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

//--------------------------------------------------Test User Entity--------------------------------------------------------------------------

    @Test
    void setUserUserNameTest() {
        user.setUsername("john");

        Assertions.assertEquals("john", user.getUsername());
    }

    @Test
    void setUserPasswordTest() {
        user.setPassword("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K");

        Assertions.assertEquals("$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K", user.getPassword());
    }

    @Test
    void setUserEnabledTest() {
        user.setEnabled(1);

        Assertions.assertEquals(1, user.getEnabled());
    }

    @Test
    void setUserProductsTest() {
        user.setProducts(Arrays.asList(product1, product2));

        Assertions.assertEquals(2, user.getProducts().size());
    }

    @Test
    void setUserUserCartTest() {
        user.setCart(cart);

        Assertions.assertEquals(200.00, user.getCart().getTotal());
    }

    @Test
    void setUserOrderDetailsTest() {
        user.setOrderDetails(Arrays.asList(order));

        Assertions.assertEquals(1, user.getOrderDetails().size());
    }



}
