package com.github.bartoszpogoda.distmarketproducer.service.impl;

import com.github.bartoszpogoda.distmarketproducer.dto.PrepareOrderDto;
import com.github.bartoszpogoda.distmarketproducer.entity.Order;
import com.github.bartoszpogoda.distmarketproducer.entity.OrderEntry;
import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.exception.ProductNotFoundException;
import com.github.bartoszpogoda.distmarketproducer.repository.OrderEntryRepository;
import com.github.bartoszpogoda.distmarketproducer.repository.OrderRepository;
import com.github.bartoszpogoda.distmarketproducer.service.OrderService;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductService productService;

    private final OrderRepository orderRepository;

    private final OrderEntryRepository orderEntryRepository;

    public OrderServiceImpl(ProductService productService, OrderRepository orderRepository, OrderEntryRepository orderEntryRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.orderEntryRepository = orderEntryRepository;
    }

    @Override
    @Transactional
    public Optional<Order> prepareOrder(PrepareOrderDto prepareOrderDto) {

        // TODO calculate total price for verification

        // also reduce quantity of products

        // extract some functions

        Order order = Order.builder()
                .clientAddress(prepareOrderDto.getAddress())
                .clientEmail(prepareOrderDto.getEmail())
                .clientFamilyName(prepareOrderDto.getFamilyName())
                .clientFirstName(prepareOrderDto.getFirstName())
                .confirmed(false)
                .totalMinor(prepareOrderDto.getTotalMinor())
                .build();

        order.setEntries(new ArrayList<>());

        prepareOrderDto.getEntries().forEach(orderEntryDto -> {
            Product product = this.productService.getByMarketplaceId(orderEntryDto.getProductId()).orElseThrow(ProductNotFoundException::new);

            OrderEntry orderEntry = OrderEntry.builder()
                    .order(order)
                    .product(product)
                    .quantity(orderEntryDto.getQuantity())
                    .build();

            order.getEntries().add(orderEntry);
        });

        return Optional.ofNullable(this.orderRepository.save(order));
    }

}
