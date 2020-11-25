package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseExercisesPaperDTO;
import com.shangshufang.apiservice.dto.CourseProgramExercisesMarkDTO;
import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.ibatis.annotations.Param;

public interface UniversityStudentExercisesService extends BaseService<UniversityStudentExercisesDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int technologyID,
                             int universityCode,
                             int schoolID,
                             int courseID,
                             int studentID,
                             String studentName,
                             String dataStatus);

    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int courseID,
                             String dataStatus,
                             String studentName);

    UnifiedResponse findList4Student(int pageNumber,
                                     int pageSize,
                                     int courseID,
                                     int studentID,
                                     String dataStatus);

//    UnifiedResponse findList4Technology(int pageNumber,
//                                        int pageSize,
//                                        int universityCode,
//                                        int schoolID,
//                                        int studentID,
//                                        int technologyID,
//                                        String dataStatus);

    UnifiedResponse findProgramReviewList(int courseExercisesID, int courseExercisesDetailID);

    UnifiedResponse findCourseExercisesDetail(int courseExercisesID);

    UnifiedResponse assign(UniversityStudentExercisesDTO dto);

    UnifiedResponse markCourseExercises(CourseExercisesPaperDTO dto);

    UnifiedResponse correctProgramAnswer(CourseProgramExercisesMarkDTO dto);
}
