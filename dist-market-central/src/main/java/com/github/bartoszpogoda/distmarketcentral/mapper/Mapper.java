package com.github.bartoszpogoda.distmarketcentral.mapper;

/**
 *
 * @param <S> source entity
 * @param <T> target dto
 */
public interface Mapper<S, T> {
    public T map(S source);
}
