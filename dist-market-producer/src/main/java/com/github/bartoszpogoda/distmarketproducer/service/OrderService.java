package com.github.bartoszpogoda.distmarketproducer.service;

import com.github.bartoszpogoda.distmarketproducer.dto.PrepareOrderDto;
import com.github.bartoszpogoda.distmarketproducer.entity.Order;

import java.util.Optional;

public interface OrderService {

    Optional<Order> prepareOrder(PrepareOrderDto prepareOrderDto);

}
