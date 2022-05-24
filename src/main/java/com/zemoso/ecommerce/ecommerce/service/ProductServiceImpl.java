package com.zemoso.ecommerce.ecommerce.service;

import com.zemoso.ecommerce.ecommerce.jparepository.ProductRepository;
import com.zemoso.ecommerce.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository) {
        productRepository = theProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        Optional<Product> result =  productRepository.findById(id);

        Product product = null;

        if(result.isPresent())
        {
            product = result.get();
        }
        return product;
    }

    @Override
    public void save(Product product) {

        productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
