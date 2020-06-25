package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface StudentComprehensiveExercisesService extends BaseService<StudentComprehensiveExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int studentID, int directionID, int categoryID, int technologyID, String dataStatus);

    UnifiedResponse checkCollected(int studentID, int exercisesID);

    UnifiedResponse delete(int studentID, int collectionID);
}
