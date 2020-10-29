package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.ibatis.annotations.Param;

public interface UniversityStudentExercisesService extends BaseService<UniversityStudentExercisesDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int courseID, String dataStatus, String studentName);

    UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID, int courseID, String dataStatus, String studentName, boolean isSelf);

    UnifiedResponse findList4Technology(int pageNumber,
                                        int pageSize,
                                        int universityCode,
                                        int schoolID,
                                        int studentID,
                                        int technologyID,
                                        String dataStatus);

    UnifiedResponse assign(UniversityStudentExercisesDTO dto);
}
