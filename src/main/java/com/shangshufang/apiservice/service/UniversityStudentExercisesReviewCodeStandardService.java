package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesReviewCodeStandardDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityStudentExercisesReviewCodeStandardService extends BaseService<UniversityStudentExercisesReviewCodeStandardDTO> {
    UnifiedResponse findList(int studentUniversityCode,
                             int studentSchoolID,
                             int studentID,
                             int technologyID,
                             int reviewID);
}
