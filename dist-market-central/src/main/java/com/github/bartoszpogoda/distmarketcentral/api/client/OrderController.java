package com.github.bartoszpogoda.distmarketcentral.api.client;

import com.github.bartoszpogoda.distmarketcentral.dto.CreateOrderDto;
import com.github.bartoszpogoda.distmarketcentral.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {

        this.orderService.processNewOrder(createOrderDto);

        return ResponseEntity.ok().build();
    }


}
