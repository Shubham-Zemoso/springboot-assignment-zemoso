package com.zemoso.ecommerce.ecommerce.controller;

import com.zemoso.ecommerce.ecommerce.entity.Product;
import com.zemoso.ecommerce.ecommerce.entity.User;
import com.zemoso.ecommerce.ecommerce.service.ProductService;
import com.zemoso.ecommerce.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

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
}
