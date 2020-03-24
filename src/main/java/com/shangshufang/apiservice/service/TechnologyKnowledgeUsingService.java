package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeUsingDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyKnowledgeUsingService {
    UnifiedResponse findList(int companyID);

    UnifiedResponse findTechnologyList(int companyID);

    UnifiedResponse findLearningPhaseList(int companyID, int technologyID);

    UnifiedResponse findKnowledgeList(int companyID, int technologyID, int learningPhaseID);

    UnifiedResponse findKnowledgeList4Client(int companyID, int technologyID);

    UnifiedResponse add(TechnologyKnowledgeUsingDTO dto);
}
