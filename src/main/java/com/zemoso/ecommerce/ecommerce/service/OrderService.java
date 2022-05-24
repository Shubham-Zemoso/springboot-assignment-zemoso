package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.OrderDetail;

public interface OrderService {

    void save(OrderDetail orderDetail);

    OrderDetail findById(int id);
}
