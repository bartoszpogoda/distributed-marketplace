package com.github.bartoszpogoda.distmarketproducer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    public ResponseEntity<?> prepareOrder() {
        return null;
    }

    public ResponseEntity<?> commitOrder() {
        return null;
    }

    public ResponseEntity<?> rollbackOrder() {
        return null;
    }

}
