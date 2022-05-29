package com.zemoso.ecommerce.service;

import com.zemoso.ecommerce.jparepository.OrderRepository;
import com.zemoso.ecommerce.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements  OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository theOrderRepository)
    {
        orderRepository = theOrderRepository;
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderRepository.save(orderDetail);
    }

    @Override
    public OrderDetail findById(int id) {
        Optional<OrderDetail> result = orderRepository.findById(id);

        OrderDetail order = null;
        if(result.isPresent())
        {
            order = result.get();
        }
        return order;

    }
}
