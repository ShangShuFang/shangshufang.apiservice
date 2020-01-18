package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CompanyDTO;
import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CourseService extends BaseService<CourseDTO> {
    UnifiedResponse findList(int pageNumber,
                             int pageSize,
                             int universityCode,
                             int schoolID,
                             int teacherID,
                             String courseTimeBegin,
                             String courseTimeEnd,
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
