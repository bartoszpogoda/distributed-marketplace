package com.github.bartoszpogoda.distmarketproducer.api;

import com.github.bartoszpogoda.distmarketproducer.dto.PrepareOrderDto;
import com.github.bartoszpogoda.distmarketproducer.mapper.OrderMapper;
import com.github.bartoszpogoda.distmarketproducer.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<?> prepareOrder(@RequestBody PrepareOrderDto prepareOrderDto) {
        return this.orderService.prepareOrder(prepareOrderDto)
                .map(this.orderMapper::map)
                .map(dto -> ResponseEntity.created(URI.create("/orders/" + dto.getId())).body(dto))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/{id}/commit")
    public ResponseEntity<?> commitOrder(@PathVariable Long id) {
        return this.orderService.commitOrder(id)
                .map(this.orderMapper::map)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> rollbackOrder(@PathVariable Long id) {
        this.orderService.rollbackOrder(id);

        return ResponseEntity.noContent().build();
    }

}
