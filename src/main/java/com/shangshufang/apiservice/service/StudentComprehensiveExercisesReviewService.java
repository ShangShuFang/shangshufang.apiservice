package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesReviewDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface StudentComprehensiveExercisesReviewService {
    UnifiedResponse findList(int pageNumber, int pageSize, int studentID, int exercisesID);

    UnifiedResponse add(StudentComprehensiveExercisesReviewDTO dto);
}
