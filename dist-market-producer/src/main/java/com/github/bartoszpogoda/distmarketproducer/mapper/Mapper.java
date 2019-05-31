package com.github.bartoszpogoda.distmarketproducer.mapper;

public interface Mapper<T, S> {

    S map(T obj);

}
