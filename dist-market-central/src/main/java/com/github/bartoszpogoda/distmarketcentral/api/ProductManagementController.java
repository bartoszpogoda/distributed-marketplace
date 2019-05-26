package com.github.bartoszpogoda.distmarketcentral.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@PreAuthorize("hasRole('ROLE_SUPPLIER')")
public class ProductManagementController {



}
