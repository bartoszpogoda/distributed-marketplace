package com.github.bartoszpogoda.distmarketproducer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateSupplierActiveDto {
    private boolean active;
}
