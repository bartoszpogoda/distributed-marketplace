package com.github.bartoszpogoda.distmarketcentral.dto;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigInteger;

@Data
public class ProductDto {

    private Long id;

    private String supplierId;

    private String supplierName;

    private String title;

    private String description;

    private Integer quantity;

    private BigInteger priceMinor;

    private boolean supplierActive;
}
