package com.github.bartoszpogoda.distmarketproducer.service;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> save(Product product);

    List<Product> getAll();

    Optional<Product> getById(long id);

    Optional<Product> getByMarketplaceId(long id);

}
