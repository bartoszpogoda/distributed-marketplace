package com.github.bartoszpogoda.distmarketcentral.repository;

import com.github.bartoszpogoda.distmarketcentral.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySupplierId(String supplierId);

    Optional<Product> findByIdAndSupplierId(long id, String supplierId);

    Long removeById(long id);

    Long removeByIdAndSupplierId(long id, String supplierId);

    Long removeBySupplierId(String supploerId);

}
