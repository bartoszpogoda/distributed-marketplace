package com.github.bartoszpogoda.distmarketcentral.api.client;

import com.github.bartoszpogoda.distmarketcentral.dto.CreateOrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {


        return ResponseEntity.ok().build();
    }


}
