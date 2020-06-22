package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.ComprehensiveExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ComprehensiveExercisesService extends BaseService<ComprehensiveExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int directionID, int categoryID, int technologyID, String dataStatus);

    UnifiedResponse delete(int exercisesID);
}
