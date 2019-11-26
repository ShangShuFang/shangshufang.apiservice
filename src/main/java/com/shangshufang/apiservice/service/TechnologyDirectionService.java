package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyDirectionService {
    UnifiedResponse findList4Technology(int technologyID);

    UnifiedResponse findList4Direction(int directionID);
}
