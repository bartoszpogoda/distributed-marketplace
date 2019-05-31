package com.github.bartoszpogoda.distmarketcentral.service.impl;

import com.github.bartoszpogoda.distmarketcentral.dto.ProductRegistrationForm;
import com.github.bartoszpogoda.distmarketcentral.dto.ProductUpdateForm;
import com.github.bartoszpogoda.distmarketcentral.entity.Product;
import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.repository.ProductRepository;
import com.github.bartoszpogoda.distmarketcentral.service.ProductService;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierAdministrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final SupplierAdministrationService supplierService;

    public ProductServiceImpl(ProductRepository productRepository, SupplierAdministrationService supplierService) {
        this.productRepository = productRepository;
        this.supplierService = supplierService;
    }


    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> getAllForSupplier(String supplierId) {
        return this.productRepository.findAllBySupplierId(supplierId);
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public Optional<Product> getProductOfSupplier(String supplierId, long productId) {
        return this.productRepository.findByIdAndSupplierId(productId, supplierId);
    }

    @Override
    public Optional<Product> registerProduct(String supplierId, ProductRegistrationForm form) {
        return supplierService.get(supplierId)
                .flatMap(supplier -> {
                    return Optional.ofNullable(
                            this.productRepository.save(
                                    Product.builder().title(form.getTitle())
                                            .description(form.getDescription())
                                            .priceMinor(form.getPriceMinor())
                                            .quantity(form.getQuantity())
                                            .supplier(supplier)
                                            .build()
                            ));
                });
    }

    @Override
    public Optional<Product> updateProductOfSupplier(String supplierId, long productId, ProductUpdateForm form) {
        return this.productRepository.findByIdAndSupplierId(productId, supplierId)
                .flatMap(product -> updateProductInternal(form, product));
    }


    @Override
    public Optional<Product> updateProduct(long productId, ProductUpdateForm form) {
        return getProduct(productId).flatMap(product -> updateProductInternal(form, product));
    }

    private Optional<Product> updateProductInternal(ProductUpdateForm form, Product product) {
        if(form.getDescription() != null) {
            product.setDescription(form.getDescription());
        }

        if(form.getPriceMinor() != null) {
            product.setPriceMinor(form.getPriceMinor());
        }

        if(form.getQuantity() != null) {
            product.setQuantity(form.getQuantity());
        }

        if(form.getTitle() != null) {
            product.setTitle(form.getTitle());
        }

        return Optional.ofNullable(this.productRepository.save(product));
    }

    @Override
    @Transactional
    public boolean unregisterProductOfSupplier(String supplierId, long productId) {
        return this.productRepository.removeByIdAndSupplierId(productId, supplierId) > 0;
    }

    @Override
    @Transactional
    public boolean unregisterProduct(long productId) {
        return this.productRepository.removeById(productId) > 0;
    }

    @Override
    public void save(Product product) {
        this.productRepository.save(product);
    }

}
