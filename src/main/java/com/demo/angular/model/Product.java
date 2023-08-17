package com.demo.angular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;
    @Column(name="name")
    String name;
    @Column(name="price")
    Double price;
    @Column(name="amount")
    Integer amount;
    @Column(name="color")
    String color;
    @Column(name="brand")
    String brand;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
