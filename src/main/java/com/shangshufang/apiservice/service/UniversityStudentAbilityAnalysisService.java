package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityStudentAbilityAnalysisService {
    UnifiedResponse ability();

    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int technologyID,
                             int studentUniversityCode,
                             int studentSchoolID,
                             int teacherUniversityCode,
                             int teacherSchoolID,
                             int teacherID,
                             String studentCellphone);
}
