package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyKnowledgeService extends BaseService<TechnologyKnowledgeDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int learningPhaseID, String dataStatus);

    UnifiedResponse findList(int technologyID);

    UnifiedResponse find(int technologyID, int knowledgeID);

    UnifiedResponse checkKnowledgeNameExist(int technologyID, String knowledgeName);

    UnifiedResponse delete(int technologyID, int learningPhaseID, int knowledgeID);
}
