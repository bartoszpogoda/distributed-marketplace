package com.github.bartoszpogoda.distmarketproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private Long marketplaceId;

    private String title;

    private String description;

    private Integer quantity;

    private BigInteger priceMinor;

}
