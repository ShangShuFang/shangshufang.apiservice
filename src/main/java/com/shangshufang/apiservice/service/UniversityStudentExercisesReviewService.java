package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesReviewDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityStudentExercisesReviewService extends BaseService<UniversityStudentExercisesReviewDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int studentExercisesID);
}
