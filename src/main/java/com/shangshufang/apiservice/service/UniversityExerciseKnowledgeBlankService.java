package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityExerciseKnowledgeBlankDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityExerciseKnowledgeBlankService extends BaseService<UniversityExerciseKnowledgeBlankDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int technologyID,
                             int knowledgeID,
                             int teacherID);

    UnifiedResponse delete(int technologyID,
                           int knowledgeID,
                           int exercisesID,
                           int teacherID);
}
