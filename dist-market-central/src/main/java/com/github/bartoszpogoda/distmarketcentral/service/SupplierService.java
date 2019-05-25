package com.github.bartoszpogoda.distmarketcentral.service;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.request.SupplierRegistrationForm;

import java.util.Optional;

public interface SupplierService {

    public Optional<Supplier> registerSupplier(SupplierRegistrationForm form);

    public Optional<Supplier> get(String id);
}
