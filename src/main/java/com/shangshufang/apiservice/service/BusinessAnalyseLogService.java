package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.BusinessAnalyseLogDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface BusinessAnalyseLogService {
    UnifiedResponse findList(int pageNumber, int pageSize);

    UnifiedResponse add(BusinessAnalyseLogDTO dto);
}
