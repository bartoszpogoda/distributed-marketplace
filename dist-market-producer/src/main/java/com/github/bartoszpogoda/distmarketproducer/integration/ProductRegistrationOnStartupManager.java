package com.github.bartoszpogoda.distmarketproducer.integration;

import com.github.bartoszpogoda.distmarketproducer.dto.ProductDto;
import com.github.bartoszpogoda.distmarketproducer.dto.RegisterProductDto;
import com.github.bartoszpogoda.distmarketproducer.dto.UpdateSupplierActiveDto;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Order(2)
public class ProductRegistrationOnStartupManager implements CommandLineRunner {

    @Value("${marketplace.api.productManagementUrl}")
    private String productManagementUrl;

    @Value("${marketplace.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private final ProductService productService;

    public ProductRegistrationOnStartupManager(RestTemplate restTemplate, ProductService productService) {
        this.restTemplate = restTemplate;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.unregisterAll();
        this.registerAll();
    }

    private void unregisterAll() {
        HttpEntity<?> request = new HttpEntity<>(buildHeaders());
        restTemplate.exchange(productManagementUrl, HttpMethod.DELETE, request, String.class);
    }

    private void registerAll() {
        this.productService.getAll().forEach(product -> {
            RegisterProductDto registerDto = RegisterProductDto.builder()
                    .title(product.getTitle())
                    .description(product.getDescription())
                    .priceMinor(product.getPriceMinor())
                    .quantity(product.getQuantity())
                    .build();

            ResponseEntity<ProductDto> response = restTemplate
                    .postForEntity(productManagementUrl, new HttpEntity<>(registerDto, buildHeaders()), ProductDto.class);

            product.setMarketplaceId(response.getBody().getId());

            productService.save(product);
        });
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-KEY", apiKey);
        return headers;
    }
}
