package com.github.bartoszpogoda.distmarketproducer.repository;

import com.github.bartoszpogoda.distmarketproducer.entity.OrderEntry;
import com.github.bartoszpogoda.distmarketproducer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {

    List<OrderEntry> findAllByProduct(Product product);

}
