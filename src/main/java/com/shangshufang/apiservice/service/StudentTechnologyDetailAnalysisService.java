package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface StudentTechnologyDetailAnalysisService {
    UnifiedResponse findKnowledgeAnalysisInfo(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    UnifiedResponse findExerciseAnalysisResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    UnifiedResponse findExercisePercentAnalysisResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);
}
