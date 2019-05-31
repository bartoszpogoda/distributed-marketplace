package com.github.bartoszpogoda.distmarketproducer.seed;

import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import com.github.bartoszpogoda.distmarketproducer.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Profile("nb")
public class NewBalanceSeed implements CommandLineRunner {

    private final ProductRepository productRepository;

    public NewBalanceSeed(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {

        productRepository.save(
                Product.builder()
                .marketplaceId(3L)
                .title("New Balance 1")
                .description("NB 1 desc")
                .priceMinor(BigInteger.valueOf(10000))
                .quantity(1)
                .build()
        );

        productRepository.save(
                Product.builder()
                        .marketplaceId(4L)
                        .title("New Balance 2")
                        .description("NB 2 desc")
                        .priceMinor(BigInteger.valueOf(20000))
                        .quantity(2)
                        .build()
        );

    }
}
