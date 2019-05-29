package com.github.bartoszpogoda.distmarketcentral.api.client;


import com.github.bartoszpogoda.distmarketcentral.auth.AuthenticatedSupplierId;
import com.github.bartoszpogoda.distmarketcentral.dto.ProductDto;
import com.github.bartoszpogoda.distmarketcentral.mapper.ProductMapper;
import com.github.bartoszpogoda.distmarketcentral.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductsController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> listOfProductDtos = this.productService
                .getAll()
                .stream().map(productMapper::map).collect(Collectors.toList());

        return ResponseEntity.ok(listOfProductDtos);
    }

}
