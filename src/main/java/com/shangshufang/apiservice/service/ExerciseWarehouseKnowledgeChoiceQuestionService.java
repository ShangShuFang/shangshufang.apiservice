package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.ExerciseWarehouseKnowledgeChoiceQuestionDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ExerciseWarehouseKnowledgeChoiceQuestionService extends BaseService<ExerciseWarehouseKnowledgeChoiceQuestionDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int technologyID,
                             int knowledgeID,
                             String dataStatus);

    UnifiedResponse delete(int technologyID,
                           int knowledgeID,
                           int exercisesID);
}
