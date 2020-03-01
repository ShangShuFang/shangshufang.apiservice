package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.SuggestDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface SuggestService extends BaseService<SuggestDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, String portal, String cellphone, String dataStatus);
}
