package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyKnowledgeUsingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechnologyKnowledgeUsingMapper extends BaseMapper<TechnologyKnowledgeUsingEntity> {
    List<TechnologyKnowledgeUsingEntity> searchList(int companyID);

    List<TechnologyKnowledgeUsingEntity> searchTechnologyList(int companyID);

    List<TechnologyKnowledgeUsingEntity> searchLearningPhaseList(int companyID, int technologyID);

    List<TechnologyKnowledgeUsingEntity> searchKnowledgeList(int companyID, int technologyID, int learningPhaseID);

    List<TechnologyKnowledgeUsingEntity> searchKnowledgeList4Client(int companyID, int technologyID);

    int delete(int companyID, int technologyID, int learningPhaseID);
}
