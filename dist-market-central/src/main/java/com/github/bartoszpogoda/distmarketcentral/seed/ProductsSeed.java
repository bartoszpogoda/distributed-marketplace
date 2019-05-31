package com.github.bartoszpogoda.distmarketcentral.seed;

import com.github.bartoszpogoda.distmarketcentral.entity.Product;
import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import com.github.bartoszpogoda.distmarketcentral.exception.SupplierNotFoundException;
import com.github.bartoszpogoda.distmarketcentral.repository.ProductRepository;
import com.github.bartoszpogoda.distmarketcentral.repository.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ProductsSeed implements CommandLineRunner {

    private final ProductRepository productRepository;

    private final SupplierRepository supplierRepository;

    public ProductsSeed(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Supplier nike = supplierRepository.findById("NIKE").orElseThrow(SupplierNotFoundException::new);
        Supplier nb = supplierRepository.findById("NB").orElseThrow(SupplierNotFoundException::new);

        productRepository.save(
                Product.builder()
                        .supplier(nike)
                        .title("Nike 1")
                        .description("Nike 1 desc")
                        .priceMinor(BigInteger.valueOf(40000))
                        .quantity(5)
                        .build()
        );

        productRepository.save(
                Product.builder()
                        .supplier(nike)
                        .title("Nike 2")
                        .description("Nike 2 desc")
                        .priceMinor(BigInteger.valueOf(70000))
                        .quantity(3)
                        .build()
        );

        productRepository.save(
                Product.builder()
                        .supplier(nb)
                        .title("New Balance 1")
                        .description("NB 1 desc")
                        .priceMinor(BigInteger.valueOf(10000))
                        .quantity(1)
                        .build()
        );

        productRepository.save(
                Product.builder()
                        .supplier(nb)
                        .title("New Balance 2")
                        .description("NB 2 desc")
                        .priceMinor(BigInteger.valueOf(20000))
                        .quantity(2)
                        .build()
        );

    }
}
