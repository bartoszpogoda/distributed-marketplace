package com.github.bartoszpogoda.distmarketcentral.api;

import java.net.URI;

import com.github.bartoszpogoda.distmarketcentral.service.SupplierAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.dto.SupplierRegistrationForm;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/suppliers")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SupplierAdministrationController {

    private SupplierAdministrationService supplierAdministrationService;

    @Autowired
    public SupplierAdministrationController(SupplierAdministrationService supplierAdministrationService) {
        this.supplierAdministrationService = supplierAdministrationService;
    }

    @PostMapping
    public ResponseEntity<Supplier> register(@RequestBody @Valid  SupplierRegistrationForm form) {
        return this.supplierAdministrationService.registerSupplier(form)
                .map(sup -> ResponseEntity.created(URI.create("temp")).body(sup))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> get(@PathVariable String id) {
        return this.supplierAdministrationService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
