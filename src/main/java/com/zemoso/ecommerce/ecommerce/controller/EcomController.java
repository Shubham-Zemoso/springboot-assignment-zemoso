package com.zemoso.ecommerce.ecommerce.controller;

import com.zemoso.ecommerce.ecommerce.entity.*;
import com.zemoso.ecommerce.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
public class EcomController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderService orderService;

    public EcomController() {
    }


    @GetMapping("/")
    public String getAllProducts(Model theModel) {
        List<Product> products = productService.findAll();

        theModel.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/getProduct")
    public String getProduct(@RequestParam("productId") int pId, Model theModel) {
        Product product = productService.findById(pId);

        theModel.addAttribute("product", product);
        return "view-details";
    }

    @GetMapping("/showFormAdd")
    public String showFormAdd(Model theModel) {

        Product product = new Product();
        theModel.addAttribute(product);
        return "product-form";
    }

    @GetMapping("/showFormUpdate")
    public String showFormUpdate(@RequestParam("productId") int pId, Model theModel) {
        Product product = productService.findById(pId);

        theModel.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") @Valid Product theProduct, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product-form";
        }
        User user = userService.getCurrentUser();
        theProduct.setUser(user);

        user.getProducts().add(theProduct);

        // save the employee
        productService.save(theProduct);

        // use a redirect to prevent duplicate submissions
        return "redirect:/admin-products";
    }

    @GetMapping("/admin-products")
    public String getAdminProducts(Model theModel) {
        User user = userService.getCurrentUser();

        if(user==null)
        {
            return "access-denied";
        }

        theModel.addAttribute("adminProducts", user.getProducts());
        return "admin-home";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int pId) {



        productService.deleteById(pId);

        return "redirect:/admin-products";
    }

    @GetMapping("/getCart")
    public String getCart(Model theModel) {
        User user = userService.getCurrentUser();

        if(user==null)
        {
            return "access-denied";
        }
        Cart cart = user.getCart();
        if (cart == null || cart.getTotal() == 0) {
            theModel.addAttribute("noItem", true);
        } else {
            theModel.addAttribute("cartItems", cart.getCartItems());
            theModel.addAttribute("total", cart.getTotal());
        }

        return "cart";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("productId") int pId) {
        Product product = productService.findById(pId);

        User user = userService.getCurrentUser();

        if(product.getUser().equals(user))
        {
            return "redirect:/";
        }

        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        List<CartItem> cartItems = cart.getCartItems();


        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        boolean found = false;
        double total = cart.getTotal();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cart.setTotalItems(cart.getTotalItems()+1);
                found = true;
                break;
            }

        }

        if (!found) {
            CartItem newItem = new CartItem(product, 1);
            cart.setTotalItems(cart.getTotalItems()+1);
            newItem.setCart(cart);
            cartItems.add(newItem);

        }

        total += product.getPrice();
        cart.setTotal(total);
        cart.setCartItems(cartItems);
        user.setCart(cart);
        cartService.save(cart);
        return "redirect:/getCart";
    }

    @GetMapping("decreaseInCart")
    public String decreaseInCart(@RequestParam("productId") int pId) {
        Product product = productService.findById(pId);

        User user = userService.getCurrentUser();

        Cart cart = user.getCart();

        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cart.setTotalItems(cart.getTotalItems()-1);
                if (cartItem.getQuantity() == 0) {
                    cartItems.remove(cartItem);
                    cartItemService.deleteById(cartItem.getId());
                }
                break;
            }

        }

        double total = cart.getTotal();
        total -= product.getPrice();

        cart.setTotal(total);
        cart.setCartItems(cartItems);
        user.setCart(cart);
        userService.save(user);

        return "redirect:/getCart";
    }

    @GetMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam("productId") int pId) {
        Product product = productService.findById(pId);

        User user = userService.getCurrentUser();

        Cart cart = user.getCart();

        List<CartItem> cartItems = cart.getCartItems();

        int qty = 0;

        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                qty = cartItem.getQuantity();
                cartItems.remove(cartItem);
                cartItemService.deleteById(cartItem.getId());
                break;
            }

        }

        double total = cart.getTotal();
        total -= product.getPrice() * qty;

        cart.setTotal(total);
        cart.setCartItems(cartItems);
        user.setCart(cart);
        userService.save(user);

        return "redirect:/getCart";

    }

    @GetMapping("/checkout")
    public String checkout() {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setCart(cart);

        orderDetail.setUser(user);
        LocalDateTime localDateTime = LocalDateTime.now();

        orderDetail.setOrderedOn(localDateTime);
        user.getOrderDetails().add(orderDetail);

        orderService.save(orderDetail);
        user.setCart(null);
        userService.save(user);

        return "redirect:/getOrders";
    }

    @GetMapping("/getOrders")
    public String getOrders(Model theModel) {

        User user = userService.getCurrentUser();

        if(user==null)
        {
            return "access-denied";
        }
        List<OrderDetail> orders = user.getOrderDetails();

        if(orders==null || orders.isEmpty())
        {
            theModel.addAttribute("noOrders", true);
        }
        else {
            theModel.addAttribute("orders", orders);
        }


        return "orders";

    }

    @GetMapping("/viewInvoice")
    public String getInvoice(@RequestParam("orderId") int oId, Model theModel) {

        OrderDetail order = orderService.findById(oId);
        System.out.println(order);
        theModel.addAttribute("items", order.getCart().getCartItems());
        theModel.addAttribute("total", order.getCart().getTotal());
        return "invoice";
    }
}