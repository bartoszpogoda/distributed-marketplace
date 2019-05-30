package com.github.bartoszpogoda.distmarketcentral.dto;

import java.math.BigInteger;
import java.util.List;
import lombok.Data;

@Data
public class CreateOrderDto {

    // order data
    private List<OrderEntryDto> entries;

    private BigInteger totalMinor;

    // client data
    private String firstName;

    private String familyName;

    private String email;

    private String address;
}
