package com.github.bartoszpogoda.distmarketcentral.service.impl;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.repository.SupplierRepository;
import com.github.bartoszpogoda.distmarketcentral.request.SupplierRegistrationForm;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> registerSupplier(SupplierRegistrationForm form) {
        Supplier supplier = Supplier.builder()
                .id(form.getId()).name(form.getName()).build();

        return Optional.ofNullable(supplierRepository.save(supplier));
    }

    @Override
    public Optional<Supplier> get(String id) {
        return supplierRepository.findById(id);
    }
}
