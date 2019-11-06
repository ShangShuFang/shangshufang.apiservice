package com.shangshufang.apiservice.mapper;

public interface BaseMapper<E> {
    int insert(E entity);

    int update(E entity);

    int updateDataStatus(E entity);
}
