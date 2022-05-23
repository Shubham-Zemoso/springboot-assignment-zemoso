package com.zemoso.ecommerce.ecommerce.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cart_item")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(cascade= {CascadeType.ALL})
    @JoinColumn(name="cart_id")
    private Cart cart;

    @Column(name="quantity")
    private int quantity;

    public CartItem() {
        quantity = 0;
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
