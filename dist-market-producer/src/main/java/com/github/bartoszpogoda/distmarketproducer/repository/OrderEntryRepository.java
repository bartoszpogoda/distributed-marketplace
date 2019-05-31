package com.github.bartoszpogoda.distmarketproducer.repository;

import com.github.bartoszpogoda.distmarketproducer.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {
}
