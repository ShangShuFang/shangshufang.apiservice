package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExerciseWarehouseKnowledgeBlankQuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExerciseWarehouseKnowledgeBlankQuestionMapper extends BaseMapper<ExerciseWarehouseKnowledgeBlankQuestionEntity> {
    int searchTotalCount(@Param("technologyID") int technologyID,
                         @Param("knowledgeID") int knowledgeID,
                         @Param("dataStatus") String dataStatus);

    List<ExerciseWarehouseKnowledgeBlankQuestionEntity> searchList(@Param("technologyID") int technologyID,
                                                                   @Param("knowledgeID") int knowledgeID,
                                                                   @Param("dataStatus") String dataStatus,
                                                                   @Param("startIndex") int startIndex,
                                                                   @Param("pageSize") int pageSize);

    int delete(@Param("technologyID") int technologyID,
               @Param("knowledgeID") int knowledgeID,
               @Param("exercisesID") int exercisesID);
}
