package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyCodeStandardDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyCodeStandardService extends BaseService<TechnologyCodeStandardDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int technologyID);

    UnifiedResponse checkCodeStandardExist(int technologyID, String codeStandardName);

    UnifiedResponse delete(int technologyID, int codeStandardID);
}
