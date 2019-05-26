package com.github.bartoszpogoda.distmarketcentral.service;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierRegistrationForm;

import java.util.Optional;

public interface SupplierAdministrationService {

    public Optional<Supplier> registerSupplier(SupplierRegistrationForm form);

    public Optional<Supplier> get(String id);

    public Optional<Supplier> getByApiKey(String apiKey);
}
