package com.github.bartoszpogoda.distmarketproducer.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class PrepareOrderDto {

    // order data
    private List<OrderEntryDto> entries;

    private BigInteger totalMinor;

    // client data
    private String firstName;

    private String familyName;

    private String email;

    private String address;
}
