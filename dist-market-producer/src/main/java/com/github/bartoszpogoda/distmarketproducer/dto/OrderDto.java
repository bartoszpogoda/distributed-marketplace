package com.github.bartoszpogoda.distmarketproducer.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class OrderDto {

    private long id;

    // order data
    private List<OrderEntryDto> entries;

    private BigInteger totalMinor;

    private boolean confirmed;

    // client data
    private String firstName;

    private String familyName;

    private String email;

    private String address;


}
