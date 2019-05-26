package com.github.bartoszpogoda.distmarketcentral.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Supplier supplier;

    private String title;

    private String description;

    private Integer quantity;

    private BigInteger priceMinor;

}
