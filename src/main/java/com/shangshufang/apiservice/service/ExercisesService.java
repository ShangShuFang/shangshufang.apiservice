package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.ExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface ExercisesService extends BaseService<ExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, String exercisesType, int technologyID, int learningPhaseID);

    UnifiedResponse findCourseExercisesList(int universityCode, int schoolID, int courseID);

    UnifiedResponse find(int exercisesID);

    UnifiedResponse checkExercisesCodeExist(String exercisesCode);

    UnifiedResponse delete(int exercisesID);
}
