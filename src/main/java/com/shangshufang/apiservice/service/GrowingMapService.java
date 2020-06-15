package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.GrowingMapDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface GrowingMapService {
    UnifiedResponse findMapList(int pageNumber, int pageSize);

    UnifiedResponse findMap(int growingID);

    UnifiedResponse findLearningPhaseList(int growingID);

    UnifiedResponse findMapDetailList(int growingID);

    UnifiedResponse checkGrowingTargetExist(String growingTarget);

    UnifiedResponse save(GrowingMapDTO dto);

    UnifiedResponse delete(int growingID);
}
