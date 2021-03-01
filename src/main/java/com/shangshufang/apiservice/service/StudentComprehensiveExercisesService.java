package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.ibatis.annotations.Param;

public interface StudentComprehensiveExercisesService extends BaseService<StudentComprehensiveExercisesDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int programLanguage,
                             int universityCode,
                             int schoolID,
                             int majorID,
                             String fullName,
                             String dataStatus);

    UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID, int directionCode, int programLanguage, int difficultyLevelCode, String dataStatus);

    UnifiedResponse find(int studentID, int exercisesID);

    UnifiedResponse findComprehensiveExercisesWithTechnology(int studentID, int technologyID);

    UnifiedResponse checkCollected(int studentID, int exercisesID);

    UnifiedResponse delete(int studentID, int collectionID);
}
