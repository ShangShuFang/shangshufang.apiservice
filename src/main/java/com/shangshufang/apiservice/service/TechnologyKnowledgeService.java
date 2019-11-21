package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.TechnologyKnowledgeDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface TechnologyKnowledgeService extends BaseService<TechnologyKnowledgeDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int technologyID);

    UnifiedResponse find(int technologyID);

    UnifiedResponse checkKnowledgeNameExist(int technologyID, String knowledgeName);

    UnifiedResponse delete(int technologyID, int knowledgeID);
}
