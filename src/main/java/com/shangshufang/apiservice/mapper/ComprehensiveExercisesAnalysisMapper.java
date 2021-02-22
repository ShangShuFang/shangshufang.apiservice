package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.ComprehensiveExercisesAnalysisEntity;
import com.shangshufang.apiservice.entity.ComprehensiveExercisesKnowledgeAnalysisEntity;
import com.shangshufang.apiservice.entity.MapEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ComprehensiveExercisesAnalysisMapper {
    List<ComprehensiveExercisesAnalysisEntity> searchComprehensiveExercisesAnalysis(@Param("studentID") int studentID);

    List<ComprehensiveExercisesKnowledgeAnalysisEntity> searchComprehensiveExercisesKnowledgeAnalysis(
            @Param("studentID") int studentID,
            @Param("technologyID") int technologyID);

    List<MapEntity> selectComprehensiveExercisesSubmit(@Param("studentID") int studentID);
}
