package com.shangshufang.apiservice.mapper;

import com.shangshufang.apiservice.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentTechnologyDetailAnalysisMapper {
    void searchKnowledgeAnalysisInfo(Map<String, Object> param);

    List<StudentExerciseAnalysisEntity> searchExerciseAnalysisResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    List<StudentExercisePercentAnalysisEntity> searchExercisePercentAnalysisResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);
}
