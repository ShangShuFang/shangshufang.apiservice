package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeBlankEntity;
import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeProgramEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UniversityExerciseKnowledgeProgramMapper extends BaseMapper<UniversityExerciseKnowledgeProgramEntity> {
    int searchTotalCount(@Param("technologyID") int technologyID,
                         @Param("knowledgeID") int knowledgeID,
                         @Param("teacherID") int teacherID);

    List<UniversityExerciseKnowledgeProgramEntity> searchList(@Param("technologyID") int technologyID,
                                                            @Param("knowledgeID") int knowledgeID,
                                                            @Param("teacherID") int teacherID,
                                                            @Param("startIndex") int startIndex,
                                                            @Param("pageSize") int pageSize);

    int delete(@Param("technologyID") int technologyID,
               @Param("knowledgeID") int knowledgeID,
               @Param("exercisesID") int exercisesID,
               @Param("teacherID") int teacherID);
}
