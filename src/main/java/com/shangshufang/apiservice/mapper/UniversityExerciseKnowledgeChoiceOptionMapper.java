package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeChoiceOptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityExerciseKnowledgeChoiceOptionMapper extends BaseMapper<UniversityExerciseKnowledgeChoiceOptionEntity> {
    List<UniversityExerciseKnowledgeChoiceOptionEntity> searchList(@Param("technologyID") int technologyID,
                                                                   @Param("knowledgeID") int knowledgeID,
                                                                   @Param("exercisesID") int exercisesID);

    int delete(@Param("technologyID") int technologyID,
               @Param("knowledgeID") int knowledgeID,
               @Param("exercisesID") int exercisesID);
}
