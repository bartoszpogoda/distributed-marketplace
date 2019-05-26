package com.github.bartoszpogoda.distmarketcentral.mapper;

import com.github.bartoszpogoda.distmarketcentral.dto.SupplierDataDto;
import com.github.bartoszpogoda.distmarketcentral.entity.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SupplierDataMapper implements Mapper<Supplier, SupplierDataDto> {

    private final ModelMapper modelMapper;

    public SupplierDataMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplierDataDto map(Supplier source) {
        return this.modelMapper.map(source, SupplierDataDto.class);
    }
}
