package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExerciseWarehouseKnowledgeChoiceQuestionOptionMapper extends BaseMapper<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> {
    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> searchList(@Param("exercisesID") int exercisesID);

    int delete(@Param("exercisesID") int exercisesID);
}
