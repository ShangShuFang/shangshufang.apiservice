package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.LearningPathDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface LearningPathService extends BaseService<LearningPathDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int learningPhase);

    UnifiedResponse findUsingTechnology();

    UnifiedResponse findUsingLearningPhase(int technologyID);

    UnifiedResponse findUsingKnowledge(int technologyID, int learningPhase);

    UnifiedResponse delete(int technologyID, int learningPhase);
}
