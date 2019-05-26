package com.github.bartoszpogoda.distmarketcentral.api;

import com.github.bartoszpogoda.distmarketcentral.auth.AuthenticatedSupplierId;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierDataDto;
import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierDataForm;
import com.github.bartoszpogoda.distmarketcentral.mapper.SupplierDataMapper;
import com.github.bartoszpogoda.distmarketcentral.service.SupplierDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplierData")
@PreAuthorize("hasRole('ROLE_SUPPLIER')")
public class SupplierDataController {

    private final SupplierDataService supplierDataService;

    private final SupplierDataMapper supplierDataMapper;

    public SupplierDataController(SupplierDataService supplierDataService, SupplierDataMapper supplierDataMapper) {
        this.supplierDataService = supplierDataService;
        this.supplierDataMapper = supplierDataMapper;
    }

    @PutMapping
    public ResponseEntity<SupplierDataDto> update(@RequestBody SupplierDataForm form, @AuthenticatedSupplierId String supplierId) {
        return this.supplierDataService.updateSupplierData(supplierId, form)
                .map(supplierDataMapper::map).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public void get() {

    }

}
