package com.github.bartoszpogoda.distmarketproducer.service.impl;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.repository.ProductRepository;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository produtRepository;

    public ProductServiceImpl(ProductRepository produtRepository) {
        this.produtRepository = produtRepository;
    }

    @Override
    public List<Product> getAll() {
        return this.produtRepository.findAll();
    }

    @Override
    public Optional<Product> getById(long id) {
        return this.produtRepository.findById(id);
    }

    @Override
    public Optional<Product> getByMarketplaceId(long id) {
        return this.produtRepository.findByMarketplaceId(id);
    }


}
