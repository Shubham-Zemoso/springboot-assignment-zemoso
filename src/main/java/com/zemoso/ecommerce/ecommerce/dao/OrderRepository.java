package com.zemoso.ecommerce.ecommerce.dao;

import com.zemoso.ecommerce.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Integer> {
}
