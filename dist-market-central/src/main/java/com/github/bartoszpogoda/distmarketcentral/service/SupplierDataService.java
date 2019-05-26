package com.github.bartoszpogoda.distmarketcentral.service;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierDataForm;

import java.util.Optional;

public interface SupplierDataService {

    public Optional<Supplier> updateSupplierData(String supplierId, SupplierDataForm form);

}
