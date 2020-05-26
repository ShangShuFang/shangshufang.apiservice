package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface UniversityStudentExercisesService extends BaseService<UniversityStudentExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID, String dataStatus);

    UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID, int courseID, String dataStatus, String studentName, boolean isSelf);

    UnifiedResponse assign(UniversityStudentExercisesDTO dto);
}
