package com.github.bartoszpogoda.distmarketcentral.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductUpdateForm {

    private String title;

    private String description;

    private Integer quantity;

    private BigInteger priceMinor;

}
