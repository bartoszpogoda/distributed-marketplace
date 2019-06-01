package com.github.bartoszpogoda.distmarketproducer.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class RegisterProductDto {
    private String title;
    private String description;

    private int quantity;
    private BigInteger priceMinor;
}
