package com.github.bartoszpogoda.distmarketcentral.repository;

import com.github.bartoszpogoda.distmarketcentral.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
