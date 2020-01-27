package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CourseService extends BaseService<CourseDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int universityCode,
                             int schoolID,
                             int teacherID,
                             String courseTimeBegin,
                             String dataStatus);

    UnifiedResponse find(int universityCode,
                         int schoolID,
                         int teacherID,
                         int courseID);

    UnifiedResponse checkCourseExist(int universityCode,
                                      int schoolID,
                                      String courseName,
                                      String courseTimeBegin,
                                      String courseTimeEnd);
}
