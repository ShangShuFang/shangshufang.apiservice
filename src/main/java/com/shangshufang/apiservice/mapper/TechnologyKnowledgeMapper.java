package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyKnowledgeMapper extends BaseMapper<TechnologyKnowledgeEntity>{
    int searchTotalCount(int technologyID, int learningPhaseID, String dataStatus);

    List<TechnologyKnowledgeEntity> searchList(int startIndex, int pageSize, int technologyID, int learningPhaseID, String dataStatus);

    List<TechnologyKnowledgeEntity> search(int technologyID);

    int checkKnowledgeNameExist(int technologyID, String knowledgeName);

    int delete(int technologyID, int learningPhaseID, int knowledgeID);
}
