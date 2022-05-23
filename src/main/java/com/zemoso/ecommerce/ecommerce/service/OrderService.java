package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.OrderDetail;

public interface OrderService {

    public void save(OrderDetail orderDetail);

    public OrderDetail findById(int id);
}
