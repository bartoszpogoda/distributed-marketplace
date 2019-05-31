package com.github.bartoszpogoda.distmarketproducer.seed;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Profile("nike")
public class NikeSeed implements CommandLineRunner {

    private final ProductRepository productRepository;

    public NikeSeed(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {

        productRepository.save(
                Product.builder()
                .marketplaceId(1L)
                .title("Nike 1")
                .description("Nike 1 desc")
                .priceMinor(BigInteger.valueOf(40000))
                .quantity(5)
                .build()
        );

        productRepository.save(
                Product.builder()
                        .marketplaceId(2L)
                        .title("Nike 2")
                        .description("Nike 2 desc")
                        .priceMinor(BigInteger.valueOf(70000))
                        .quantity(3)
                        .build()
        );

    }
}
