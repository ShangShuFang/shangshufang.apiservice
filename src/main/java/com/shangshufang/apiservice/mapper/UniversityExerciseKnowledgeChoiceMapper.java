package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeChoiceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityExerciseKnowledgeChoiceMapper extends BaseMapper<UniversityExerciseKnowledgeChoiceEntity> {
    int searchTotalCount(@Param("technologyID") int technologyID,
                         @Param("knowledgeID") int knowledgeID,
                         @Param("teacherID") int teacherID);

    List<UniversityExerciseKnowledgeChoiceEntity> searchList(@Param("technologyID") int technologyID,
                                                             @Param("knowledgeID") int knowledgeID,
                                                             @Param("teacherID") int teacherID,
                                                             @Param("startIndex") int startIndex,
                                                             @Param("pageSize") int pageSize);

    List<UniversityExerciseKnowledgeChoiceEntity> searchOtherList(@Param("technologyID") int technologyID,
                                                                  @Param("knowledgeID") int knowledgeID,
                                                                  @Param("teacherID") int teacherID,
                                                                  @Param("startIndex") int startIndex,
                                                                  @Param("pageSize") int pageSize);

    int delete(@Param("technologyID") int technologyID,
               @Param("knowledgeID") int knowledgeID,
               @Param("exercisesID") int exercisesID,
               @Param("teacherID") int teacherID);
}
