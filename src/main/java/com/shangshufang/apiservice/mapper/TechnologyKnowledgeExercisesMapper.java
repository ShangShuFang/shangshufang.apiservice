package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyKnowledgeExercisesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TechnologyKnowledgeExercisesMapper extends BaseMapper<TechnologyKnowledgeExercisesEntity> {
    int searchTotalCount(@Param("technologyID") int technologyID, @Param("knowledgeID") int knowledgeID);

    List<TechnologyKnowledgeExercisesEntity> searchList(@Param("startIndex") int startIndex,
                                                        @Param("pageSize") int pageSize,
                                                        @Param("technologyID") int technologyID,
                                                        @Param("knowledgeID") int knowledgeID);

    List<TechnologyKnowledgeExercisesEntity> searchList4Knowledge(int technologyID, int knowledgeID);

    List<TechnologyKnowledgeExercisesEntity> searchList4CourseKnowledge(int universityCode, int schoolID, int courseID, int courseClass, int knowledgeID);

    int delete (int technologyID, int learningPhaseID, int knowledgeID);
}
