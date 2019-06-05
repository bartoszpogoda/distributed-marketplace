package com.github.bartoszpogoda.distmarketproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditProductFormDto {

    private Long id;

    private Long marketplaceId;

    private String title;

    private String description;

    private Integer quantity;

    private BigInteger priceMinor;

    private BigInteger priceMajor;

}
