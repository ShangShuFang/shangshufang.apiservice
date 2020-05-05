package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyCategoryDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyCategoryService extends BaseService<TechnologyCategoryDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int directionID, String dataStatus);

    UnifiedResponse checkNameExist(int directionID, String categoryName);

    UnifiedResponse delete(int directionID, int technologyCategoryID);
}
