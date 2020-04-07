package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityStudentAbilityAnalysisService {
    UnifiedResponse abilityAnalysis();

    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int technologyID,
                             int studentUniversityCode,
                             int studentSchoolID,
                             int teacherUniversityCode,
                             int teacherSchoolID,
                             int teacherID,
                             String studentCellphone);

    UnifiedResponse findStudentAbilityInfo(int universityCode, int schoolID, int studentID);

    UnifiedResponse findStudentTechnologyAbility(int universityCode, int schoolID, int studentID, int technologyID);

    UnifiedResponse findCodeStandardAnalysis(int universityCode, int schoolID, int studentID, int technologyID);
}
