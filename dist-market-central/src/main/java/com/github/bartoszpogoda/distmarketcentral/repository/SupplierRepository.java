package com.github.bartoszpogoda.distmarketcentral.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;

import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, String>{

    public Optional<Supplier> findFirstByApiKey(String apiKey);

}
