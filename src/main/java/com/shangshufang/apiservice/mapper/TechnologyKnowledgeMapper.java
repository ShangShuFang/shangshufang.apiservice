package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyKnowledgeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyKnowledgeMapper extends BaseMapper<TechnologyKnowledgeEntity>{
    int searchTotalCount(@Param("technologyID") int technologyID);

    List<TechnologyKnowledgeEntity> searchList(int startIndex, int pageSize, int technologyID);

    List<TechnologyKnowledgeEntity> search(int technologyID);

    int checkKnowledgeNameExist(int technologyID, String knowledgeName);

    int delete(int technologyID, int knowledgeID);
}
