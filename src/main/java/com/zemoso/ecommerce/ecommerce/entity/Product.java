package com.zemoso.ecommerce.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotBlank(message="is required")
    @Column(name="product_name")
    private String productName;
    @NotBlank(message="is required")
    @Column(name="description")
    private String description;
    @DecimalMin( value="10.00", message="must be greater than 10.00")
    @Column(name="price")
    private Double price;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="username")
    private User user;

    public Product() {
    }

    public Product(String productName, String description, Double price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

}
