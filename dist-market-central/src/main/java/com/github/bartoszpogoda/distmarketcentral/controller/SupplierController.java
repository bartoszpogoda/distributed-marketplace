package com.github.bartoszpogoda.distmarketcentral.controller;

import java.net.URI;
import java.util.Optional;

import com.github.bartoszpogoda.distmarketcentral.service.SupplierService;
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
import com.github.bartoszpogoda.distmarketcentral.request.SupplierRegistrationForm;

@RestController
@RequestMapping("/suppliers")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SupplierController {

    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Supplier> register(@RequestBody SupplierRegistrationForm form) {
        return this.supplierService.registerSupplier(form)
                .map(sup -> ResponseEntity.created(URI.create("temp")).body(sup))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> get(@PathVariable String id) {
        return this.supplierService.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
