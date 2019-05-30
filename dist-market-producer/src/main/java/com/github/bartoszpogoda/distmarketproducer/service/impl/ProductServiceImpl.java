package com.github.bartoszpogoda.distmarketproducer.service.impl;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.repository.ProductRepository;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
