package com.zemoso.ecommerce.service;

import com.zemoso.ecommerce.entity.OrderDetail;

public interface OrderService {

    void save(OrderDetail orderDetail);

    OrderDetail findById(int id);
}
