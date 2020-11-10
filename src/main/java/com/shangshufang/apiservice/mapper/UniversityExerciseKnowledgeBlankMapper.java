package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ExerciseWarehouseKnowledgeBlankQuestionEntity;
import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeBlankEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityExerciseKnowledgeBlankMapper extends BaseMapper<UniversityExerciseKnowledgeBlankEntity> {
    int searchTotalCount(@Param("technologyID") int technologyID,
                         @Param("knowledgeID") int knowledgeID,
                         @Param("teacherID") int teacherID);

    UniversityExerciseKnowledgeBlankEntity search(@Param("exercisesID") int exercisesID);

    List<UniversityExerciseKnowledgeBlankEntity> searchList(@Param("technologyID") int technologyID,
                                                            @Param("knowledgeID") int knowledgeID,
                                                            @Param("teacherID") int teacherID,
                                                            @Param("startIndex") int startIndex,
                                                            @Param("pageSize") int pageSize);

    List<UniversityExerciseKnowledgeBlankEntity> searchOtherList(@Param("technologyID") int technologyID,
                                                                 @Param("knowledgeID") int knowledgeID,
                                                                 @Param("teacherID") int teacherID,
                                                                 @Param("startIndex") int startIndex,
                                                                 @Param("pageSize") int pageSize);

    int delete(@Param("technologyID") int technologyID,
               @Param("knowledgeID") int knowledgeID,
               @Param("exercisesID") int exercisesID,
               @Param("teacherID") int teacherID);
}
