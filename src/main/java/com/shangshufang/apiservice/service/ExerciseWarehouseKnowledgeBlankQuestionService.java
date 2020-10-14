package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.ExerciseWarehouseKnowledgeBlankQuestionDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ExerciseWarehouseKnowledgeBlankQuestionService extends BaseService<ExerciseWarehouseKnowledgeBlankQuestionDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int technologyID,
                             int knowledgeID,
                             String dataStatus);

    UnifiedResponse delete(int technologyID,
                           int knowledgeID,
                           int exercisesID);
}
