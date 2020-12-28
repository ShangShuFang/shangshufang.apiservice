package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface StudentComprehensiveExercisesService extends BaseService<StudentComprehensiveExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int programLanguage, String dataStatus);

    UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID, int directionCode, int programLanguage, int difficultyLevelCode, String dataStatus);

    UnifiedResponse find(int studentID, int exercisesID);

    UnifiedResponse checkCollected(int studentID, int exercisesID);

    UnifiedResponse delete(int studentID, int collectionID);
}
