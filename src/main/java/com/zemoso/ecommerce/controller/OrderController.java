package com.zemoso.ecommerce.controller;

import com.zemoso.ecommerce.entity.OrderDetail;
import com.zemoso.ecommerce.entity.User;
import com.zemoso.ecommerce.service.OrderService;
import com.zemoso.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


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
