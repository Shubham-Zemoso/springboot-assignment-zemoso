package com.zemoso.ecommerce.service;

import com.zemoso.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(int id);

    void save(Product product);

    void deleteById(int id);
}
