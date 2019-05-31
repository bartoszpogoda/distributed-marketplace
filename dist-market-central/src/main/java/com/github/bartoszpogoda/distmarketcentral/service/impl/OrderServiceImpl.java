package com.github.bartoszpogoda.distmarketcentral.service.impl;

import com.github.bartoszpogoda.distmarketcentral.dto.CreateOrderDto;
import com.github.bartoszpogoda.distmarketcentral.dto.OrderEntryDto;
import com.github.bartoszpogoda.distmarketcentral.dto.producer.OrderDto;
import com.github.bartoszpogoda.distmarketcentral.dto.producer.PrepareOrderDto;
import com.github.bartoszpogoda.distmarketcentral.entity.Product;
import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.exception.ProductNotFoundException;
import com.github.bartoszpogoda.distmarketcentral.exception.SupplierNotActiveException;
import com.github.bartoszpogoda.distmarketcentral.exception.SupplierNotFoundException;
import com.github.bartoszpogoda.distmarketcentral.service.OrderService;
import com.github.bartoszpogoda.distmarketcentral.service.ProductService;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierAdministrationService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.*;

@Service
@Log
public class OrderServiceImpl implements OrderService {

    private final RestTemplate rest;

    private final ProductService productService;

    private final SupplierAdministrationService supplierService;

    public OrderServiceImpl(RestTemplate rest, ProductService productService, SupplierAdministrationService supplierService) {
        this.rest = rest;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    /**
     * Two-phase commit pattern
     *
     * @param createOrderDto
     */
    @Override
    public void processNewOrder(CreateOrderDto createOrderDto) {
        List<OrderEntryDto> entries = createOrderDto.getEntries();

        Map<String, PrepareOrderDto> suppliersToOrdersMap = new HashMap<>();

        for (OrderEntryDto entry : entries) {
            Product product = productService.getProduct(entry.getProductId()).orElseThrow(ProductNotFoundException::new);

            PrepareOrderDto orderDto = suppliersToOrdersMap.getOrDefault(product.getSupplier().getId(), buildPrepareOrderDto(createOrderDto));

            orderDto.getEntries().add(entry);

            suppliersToOrdersMap.put(product.getSupplier().getId(), orderDto);
        }

        Map<String, ResponseEntity<OrderDto>> responses = new HashMap<>();

        // prepare phase
        for (String producerId : suppliersToOrdersMap.keySet()) {
            PrepareOrderDto prepareOrderDto = suppliersToOrdersMap.get(producerId);

            Supplier supplier = supplierService.get(producerId).orElseThrow(SupplierNotFoundException::new);

            if (!supplier.isActive()) {
                throw new SupplierNotActiveException();
            }

            try {
                ResponseEntity<OrderDto> response = rest.postForEntity(supplier.getOrderHook(), prepareOrderDto,
                        OrderDto.class);

                if (!response.getStatusCode().is2xxSuccessful()) {
                    // abort phase
                    log.info("Sub-order for " + producerId + " failed");
                    abortAll(responses);
                    log.info("All sub-orders aborted");

                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Denied by " + producerId);
                }

                log.info("Sub-order for " + producerId + " prepared");
                responses.put(producerId, response);
            } catch (Exception e) {
                // abort phase
                log.info("Sub-order for " + producerId + " failed");
                abortAll(responses);
                log.info("All sub-orders aborted");

                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Denied by " + producerId);
            }


        }

        // commit phase
        commitAll(responses);
        log.info("All sub-orders committed");
    }

    private void abortAll(Map<String, ResponseEntity<OrderDto>> responses) {
        for (String supplierId : responses.keySet()) {
            ResponseEntity<OrderDto> response = responses.get(supplierId);
            Supplier supplier = this.supplierService.get(supplierId).orElseThrow(SupplierNotFoundException::new);

            long preparedOrderId = response.getBody().getId();

            rest.delete(supplier.getOrderHook() + "/" + preparedOrderId);
        }
    }

    private void commitAll(Map<String, ResponseEntity<OrderDto>> responses) {
        for (String supplierId : responses.keySet()) {
            ResponseEntity<OrderDto> response = responses.get(supplierId);
            Supplier supplier = this.supplierService.get(supplierId).orElseThrow(SupplierNotFoundException::new);

            long preparedOrderId = response.getBody().getId();

            rest.postForEntity(supplier.getOrderHook() + "/" + preparedOrderId + "/commit", null, OrderDto.class);
        }
    }

    private PrepareOrderDto buildPrepareOrderDto(CreateOrderDto dto) {

        return PrepareOrderDto.builder()
                .address(dto.getAddress())
                .email(dto.getEmail())
                .entries(new ArrayList<>())
                .familyName(dto.getFamilyName())
                .firstName(dto.getFirstName())
                .totalMinor(dto.getTotalMinor())
                .build();

    }


}
