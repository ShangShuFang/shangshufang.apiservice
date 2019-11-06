package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ChinaRegionService {
    UnifiedResponse findList(int regionParentCode);
}
