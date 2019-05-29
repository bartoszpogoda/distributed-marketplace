package com.github.bartoszpogoda.distmarketcentral.mapper;

import com.github.bartoszpogoda.distmarketcentral.dto.ProductDto;
import com.github.bartoszpogoda.distmarketcentral.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto map(Product source) {
        return modelMapper.map(source, ProductDto.class);
    }
}
