package com.github.bartoszpogoda.distmarketcentral.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Data
public class ProductRegistrationForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private Integer quantity;

    @NotEmpty
    private BigInteger priceMinor;

}
