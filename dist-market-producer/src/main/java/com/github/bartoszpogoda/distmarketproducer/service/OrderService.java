package com.github.bartoszpogoda.distmarketproducer.service;

import com.github.bartoszpogoda.distmarketproducer.dto.PrepareOrderDto;
import com.github.bartoszpogoda.distmarketproducer.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> prepareOrder(PrepareOrderDto prepareOrderDto);

    Optional<Order> commitOrder(Long orderId);

    List<Order> getAll();

    void rollbackOrder(Long orderId);
}
