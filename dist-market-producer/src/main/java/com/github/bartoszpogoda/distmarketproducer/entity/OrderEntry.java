package com.github.bartoszpogoda.distmarketproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntry {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;

}
