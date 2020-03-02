package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CourseService extends BaseService<CourseDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int universityCode,
                             int schoolID,
                             int teacherID,
                             int technologyID,
                             String courseTimeBegin,
                             String dataStatus,
                             boolean isSelf,
                             String searchType);

    UnifiedResponse find(int universityCode,
                         int schoolID,
                         int courseID,
                         String dataStatus);

    UnifiedResponse checkCourseExist(int universityCode,
                                      int schoolID,
                                      String courseName,
                                      String courseTimeBegin,
                                      String courseTimeEnd);

    UnifiedResponse changeCourseBaseInfo(CourseDTO dto);

    UnifiedResponse changeCourseSchedule(CourseDTO dto);

    UnifiedResponse changeCoursePlan(CourseDTO dto);
}
