package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface AnalysisAbilityService {
    UnifiedResponse analyse();

    UnifiedResponse findList(int pageNumber, int pageSize, int directionID, int categoryID, int technologyID, int studentUniversityCode, int studentSchoolID, int teacherUniversityCode, int teacherSchoolID, int teacherID, String studentName);

    UnifiedResponse findStudentSummaryResult(int universityCode, int schoolID, int studentID);

    UnifiedResponse findLearningTechnologyResultList(int universityCode, int schoolID, int studentID);

    UnifiedResponse findKnowledgeSummaryResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    UnifiedResponse findCodeGuidelineResult(int studentUniversityCode, int studentSchoolID, int studentID, int languageID);

    UnifiedResponse findExerciseNumberResultList(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);

    UnifiedResponse findExercisePercentResultList(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID);


}
