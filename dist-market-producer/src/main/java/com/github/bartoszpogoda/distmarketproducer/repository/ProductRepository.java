package com.github.bartoszpogoda.distmarketproducer.repository;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
