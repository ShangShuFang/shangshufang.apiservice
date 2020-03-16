package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.TechnologyKnowledgeExercisesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TechnologyKnowledgeExercisesMapper extends BaseMapper<TechnologyKnowledgeExercisesEntity> {
    int searchTotalCount(int technologyID, int learningPhaseID, int knowledgeID);

    List<TechnologyKnowledgeExercisesEntity> searchList(int startIndex, int pageSize, int technologyID, int learningPhaseID, int knowledgeID);

    List<TechnologyKnowledgeExercisesEntity> searchList4Knowledge(int technologyID, int learningPhaseID, int knowledgeID);

    List<TechnologyKnowledgeExercisesEntity> searchList4CourseKnowledge(int universityCode, int schoolID, int courseID, int courseClass, int knowledgeID);

    int delete (int technologyID, int learningPhaseID, int knowledgeID);
}
