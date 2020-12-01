package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyKnowledgeMapper extends BaseMapper<TechnologyKnowledgeEntity>{
    int searchTotalCount(@Param("technologyID") int technologyID,
                         @Param("learningPhaseID") int learningPhaseID,
                         @Param("dataStatus") String dataStatus);

    List<TechnologyKnowledgeEntity> searchList(@Param("startIndex") int startIndex,
                                               @Param("pageSize") int pageSize,
                                               @Param("technologyID") int technologyID,
                                               @Param("learningPhaseID") int learningPhaseID,
                                               @Param("dataStatus")  String dataStatus);

    TechnologyKnowledgeEntity search(@Param("technologyID") int technologyID,
                                     @Param("knowledgeID") int knowledgeID);

    List<TechnologyKnowledgeEntity> searchSimpleList(int technologyID);

    int checkKnowledgeNameExist(int technologyID, String knowledgeName);

    int delete(int technologyID, int learningPhaseID, int knowledgeID);
}
