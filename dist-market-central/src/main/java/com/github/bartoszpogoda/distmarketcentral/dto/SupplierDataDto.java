package com.github.bartoszpogoda.distmarketcentral.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SupplierDataDto {

    private String id;

    private String name;

    private String createOrderHook;

    private boolean active;
}
