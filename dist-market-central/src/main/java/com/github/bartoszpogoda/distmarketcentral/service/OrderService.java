package com.github.bartoszpogoda.distmarketcentral.service;

import com.github.bartoszpogoda.distmarketcentral.dto.CreateOrderDto;

public interface OrderService {

    void processNewOrder(CreateOrderDto createOrderDto);

}
