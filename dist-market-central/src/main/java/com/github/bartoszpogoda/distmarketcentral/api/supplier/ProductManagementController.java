package com.github.bartoszpogoda.distmarketcentral.api.supplier;

import com.github.bartoszpogoda.distmarketcentral.auth.AuthenticatedSupplierId;
import com.github.bartoszpogoda.distmarketcentral.dto.ProductDto;
import com.github.bartoszpogoda.distmarketcentral.dto.ProductRegistrationForm;
import com.github.bartoszpogoda.distmarketcentral.dto.ProductUpdateForm;
import com.github.bartoszpogoda.distmarketcentral.mapper.ProductMapper;
import com.github.bartoszpogoda.distmarketcentral.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/managedProducts")
@PreAuthorize("hasRole('ROLE_SUPPLIER')")
public class ProductManagementController {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductManagementController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> register(@RequestBody ProductRegistrationForm form, @AuthenticatedSupplierId String supplierId) {
        return this.productService.registerProduct(supplierId, form)
                .map(productMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts(@AuthenticatedSupplierId String supplierId) {
        List<ProductDto> listOfProductDtos = this.productService
                .getAllForSupplier(supplierId)
                .stream().map(productMapper::map).collect(Collectors.toList());

        return ResponseEntity.ok(listOfProductDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id, @AuthenticatedSupplierId String supplierId) {
        return this.productService.getProductOfSupplier(supplierId, id)
                .map(productMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody @Valid ProductUpdateForm form,
                                                    @AuthenticatedSupplierId String supplierId) {

        return this.productService.updateProductOfSupplier(supplierId, id, form)
                .map(productMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> unregisterProduct(@PathVariable Long id, @AuthenticatedSupplierId String supplierId) {
        boolean unregistered = this.productService.unregisterProductOfSupplier(supplierId, id);
        return unregistered ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
