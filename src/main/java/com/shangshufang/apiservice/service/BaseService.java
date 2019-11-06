package com.shangshufang.apiservice.service;


import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface BaseService<T> {
    UnifiedResponse add(T dto);

    UnifiedResponse change(T dto);

    UnifiedResponse changeDataStatus(T dto);
}
