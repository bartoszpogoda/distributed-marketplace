package com.github.bartoszpogoda.distmarketcentral.service.impl;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.repository.SupplierRepository;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierDataForm;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierDataService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierDataServiceImpl implements SupplierDataService {

    private final SupplierRepository supplierRepository;

    public SupplierDataServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> updateSupplierData(String supplierId, SupplierDataForm form) {
        return get(supplierId).flatMap(loggedInSupplier -> {
            if(form.getActive() != null) {
                loggedInSupplier.setActive(form.getActive());
            }

            if(form.getCreateOrderHook() != null) {
                loggedInSupplier.setCreateOrderHook(form.getCreateOrderHook());
            }

            return Optional.ofNullable(this.supplierRepository.save(loggedInSupplier));
        });
    }

    @Override
    public Optional<Supplier> get(String supplierId) {
        return this.supplierRepository.findById(supplierId);
    }

}
