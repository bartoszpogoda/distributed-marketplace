package com.github.bartoszpogoda.distmarketproducer.mapper;

import com.github.bartoszpogoda.distmarketproducer.dto.OrderDto;
import com.github.bartoszpogoda.distmarketproducer.dto.OrderEntryDto;
import com.github.bartoszpogoda.distmarketproducer.entity.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements Mapper<Order, OrderDto> {

    private final ModelMapper modelMapper;

    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto map(Order obj) {
        OrderDto dto = modelMapper.map(obj, OrderDto.class);

        List<OrderEntryDto> entries = obj.getEntries().stream()
                .map(ent -> modelMapper.map(ent, OrderEntryDto.class))
                .collect(Collectors.toList());
        dto.setEntries(entries);

        return dto;
    }
}
