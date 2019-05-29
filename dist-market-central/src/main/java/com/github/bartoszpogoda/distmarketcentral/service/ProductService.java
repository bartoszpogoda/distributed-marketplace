package com.github.bartoszpogoda.distmarketcentral.service;

import com.github.bartoszpogoda.distmarketcentral.dto.ProductRegistrationForm;
import com.github.bartoszpogoda.distmarketcentral.dto.ProductUpdateForm;
import com.github.bartoszpogoda.distmarketcentral.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();

    List<Product> getAllForSupplier(String supplierId);

    Optional<Product> getProduct(long productId);

    Optional<Product> getProductOfSupplier(String supplierId, long productId);

    Optional<Product> registerProduct(String supplierId, ProductRegistrationForm form);

    Optional<Product> updateProductOfSupplier(String supplierId, long productId, ProductUpdateForm form);

    Optional<Product> updateProduct(long productId, ProductUpdateForm form);

    boolean unregisterProductOfSupplier(String supplierId, long productId);

    boolean unregisterProduct(long productId);

}
