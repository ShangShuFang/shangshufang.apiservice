package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyKnowledgeExercisesService extends BaseService<TechnologyKnowledgeExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int knowledgeID);

    UnifiedResponse findCourseAssignList(int universityCode, int schoolID, int courseID);
}
