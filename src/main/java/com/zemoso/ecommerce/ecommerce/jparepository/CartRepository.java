package com.zemoso.ecommerce.ecommerce.jparepository;

import com.zemoso.ecommerce.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
