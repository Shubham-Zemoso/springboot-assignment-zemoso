package com.zemoso.ecommerce.ecommerce.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="order_detail")
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="username")
    private User user;

    @Column(name="ordered_on")
    LocalDateTime orderedOn;

    public OrderDetail() {
    }

    public OrderDetail(Cart cart, User user, LocalDateTime orderedOn) {
        this.cart = cart;
        this.user = user;
        this.orderedOn = orderedOn;
    }


}
