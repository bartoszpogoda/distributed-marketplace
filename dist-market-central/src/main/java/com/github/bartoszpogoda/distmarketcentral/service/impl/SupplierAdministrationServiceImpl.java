package com.github.bartoszpogoda.distmarketcentral.service.impl;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.repository.SupplierRepository;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierRegistrationForm;
import com.github.bartoszpogoda.distmarketcentral.service.ApiKeyService;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierAdministrationServiceImpl implements SupplierAdministrationService {

    private final SupplierRepository supplierRepository;

    private final ApiKeyService apiKeyService;

    @Autowired
    public SupplierAdministrationServiceImpl(SupplierRepository supplierRepository, ApiKeyService apiKeyService) {
        this.supplierRepository = supplierRepository;
        this.apiKeyService = apiKeyService;
    }

    @Override
    public Optional<Supplier> registerSupplier(SupplierRegistrationForm form) {
        String apiKey = this.apiKeyService.generate();

        Supplier supplier = Supplier.builder()
                .id(form.getId()).name(form.getName()).apiKey(apiKey).build();

        return Optional.ofNullable(supplierRepository.save(supplier));
    }

    @Override
    public Optional<Supplier> get(String id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<Supplier> getByApiKey(String apiKey) {
        return this.supplierRepository.findFirstByApiKey(apiKey);
    }
}
