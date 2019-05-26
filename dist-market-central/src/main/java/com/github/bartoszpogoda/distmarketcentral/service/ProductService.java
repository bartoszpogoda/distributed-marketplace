package com.github.bartoszpogoda.distmarketcentral.service;

import com.github.bartoszpogoda.distmarketcentral.dto.ProductRegistrationForm;

public interface ProductService {

    public void registerProduct(ProductRegistrationForm form);

    public void updateProduct();

    public void unregisterProduct();

}
