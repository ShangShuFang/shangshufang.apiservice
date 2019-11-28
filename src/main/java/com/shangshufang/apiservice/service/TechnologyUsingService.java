package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyUsingDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyUsingService {
    UnifiedResponse findUsingTechnologyList(int companyID);

    UnifiedResponse add(TechnologyUsingDTO dto);
}
