package com.github.bartoszpogoda.distmarketcentral.dto.producer;

import com.github.bartoszpogoda.distmarketcentral.dto.OrderEntryDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
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
