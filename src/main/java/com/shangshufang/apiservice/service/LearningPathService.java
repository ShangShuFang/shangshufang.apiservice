package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface LearningPathService{

    UnifiedResponse findTechnology();

    UnifiedResponse findLearningPhase(int technologyID);

    UnifiedResponse findKnowledge(int technologyID, int learningPhase);
}
