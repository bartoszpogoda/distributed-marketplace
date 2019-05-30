package com.github.bartoszpogoda.distmarketcentral.service.impl;

import com.github.bartoszpogoda.distmarketcentral.dto.CreateOrderDto;
import com.github.bartoszpogoda.distmarketcentral.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public void processNewOrder(CreateOrderDto createOrderDto) {

        // order processing

        // Two-phase commit pattern

        // for each producer contained in order notify them with suborder -- prepare

        // also should be abort

        // for each producer .. -- submit

    }


}
