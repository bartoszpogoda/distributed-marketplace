package com.github.bartoszpogoda.distmarketproducer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.*;


@Builder
@Data
@Entity
@Table(name = "Orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderEntry> entries;

    private String clientFirstName;

    private String clientFamilyName;

    private String clientAddress;

    private String clientEmail;

    private boolean confirmed;

    private BigInteger totalMinor;
}
