package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityExerciseKnowledgeProgramDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityExerciseKnowledgeProgramService extends BaseService<UniversityExerciseKnowledgeProgramDTO> {
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