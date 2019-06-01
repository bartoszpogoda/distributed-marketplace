package com.github.bartoszpogoda.distmarketproducer.service.impl;

import com.github.bartoszpogoda.distmarketproducer.dto.ProductDto;
import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.exception.ProductNotFoundException;
import com.github.bartoszpogoda.distmarketproducer.repository.OrderEntryRepository;
import com.github.bartoszpogoda.distmarketproducer.repository.ProductRepository;
import com.github.bartoszpogoda.distmarketproducer.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final OrderEntryRepository orderEntryRepository;

    private final RestTemplate restTemplate;

    @Value("${marketplace.api.productManagementUrl}")
    private String productManagementUrl;

    @Value("${marketplace.api.key}")
    private String apiKey;

    public ProductServiceImpl(ProductRepository productRepository, OrderEntryRepository orderEntryRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.orderEntryRepository = orderEntryRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Product> save(Product product) {
        return Optional.ofNullable(productRepository.save(product));
    }

    @Transactional
    public void saveOrUpdate(Product product) {

        if (product.getMarketplaceId() != null) {
            restTemplate.put(productManagementUrl + "/" + product.getMarketplaceId(),
                    new HttpEntity<>(product, buildHeaders()));

            save(product);
        } else {
            ResponseEntity<ProductDto> response = restTemplate.postForEntity(productManagementUrl,
                    new HttpEntity<>(product, buildHeaders()), ProductDto.class);

            product.setMarketplaceId(response.getBody().getId());
            save(product);
        }

    }

    @Transactional
    public void unregister(Long productId) {
        Product product = getById(productId).orElseThrow(ProductNotFoundException::new);

        if(product.getMarketplaceId() != null) {
            HttpEntity<?> request = new HttpEntity<>(buildHeaders());
            restTemplate.exchange(productManagementUrl + "/" + product.getMarketplaceId(), HttpMethod.DELETE, request, String.class);
            product.setMarketplaceId(null);
        }

        if(this.orderEntryRepository.findAllByProduct(product).size() == 0) {
            // no order references - can be deleted
            this.productRepository.delete(product);
        }
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-KEY", apiKey);
        return headers;
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> getByMarketplaceId(long id) {
        return this.productRepository.findByMarketplaceId(id);
    }


}
