package com.github.bartoszpogoda.distmarketproducer.repository;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByMarketplaceId(Long marketplaceId);
}
