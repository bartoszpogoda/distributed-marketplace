package com.github.bartoszpogoda.distmarketproducer.service.impl;

import com.github.bartoszpogoda.distmarketproducer.dto.PrepareOrderDto;
import com.github.bartoszpogoda.distmarketproducer.entity.Order;
import com.github.bartoszpogoda.distmarketproducer.entity.OrderEntry;
import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.exception.NotEnoughItemsException;
import com.github.bartoszpogoda.distmarketproducer.exception.ProductNotFoundException;
import com.github.bartoszpogoda.distmarketproducer.repository.OrderEntryRepository;
import com.github.bartoszpogoda.distmarketproducer.repository.OrderRepository;
import com.github.bartoszpogoda.distmarketproducer.service.OrderService;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

            product.setQuantity(product.getQuantity() - orderEntry.getQuantity());

            if(product.getQuantity() < 0) {
                throw new NotEnoughItemsException();
            }

            productService.save(product);
            order.getEntries().add(orderEntry);
        });

        return Optional.ofNullable(this.orderRepository.save(order));
    }

    @Override
    public Optional<Order> commitOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(RuntimeException::new); // TODO exception
        order.setConfirmed(true);
        return Optional.ofNullable(this.orderRepository.save(order));
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public void rollbackOrder(Long orderId) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(RuntimeException::new); // TODO exception

        if(order.isConfirmed()) {
            throw new RuntimeException("Order already confirmed");
        }

        order.getEntries().forEach(orderEntry -> {
            Product product = orderEntry.getProduct();
            product.setQuantity(product.getQuantity() + orderEntry.getQuantity());
            productService.save(product);
        });

        this.orderRepository.delete(order);
    }

    @Override
    public void removeOrder(Long orderId) {
        this.orderRepository.deleteById(orderId);
    }

}
