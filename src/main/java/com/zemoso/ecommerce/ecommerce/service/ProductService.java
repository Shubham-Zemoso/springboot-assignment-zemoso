package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(int id);

    void save(Product product);

    void deleteById(int id);
}
