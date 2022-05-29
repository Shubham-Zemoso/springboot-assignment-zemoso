package com.zemoso.ecommerce.controller;

import com.zemoso.ecommerce.entity.Cart;
import com.zemoso.ecommerce.entity.CartItem;
import com.zemoso.ecommerce.service.CartItemService;
import com.zemoso.ecommerce.service.CartService;
import com.zemoso.ecommerce.service.ProductService;
import com.zemoso.ecommerce.service.UserService;
import com.zemoso.ecommerce.entity.Product;
import com.zemoso.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;

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

}
