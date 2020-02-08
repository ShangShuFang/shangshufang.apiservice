package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseSignUpDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CourseSignUpService extends BaseService<CourseSignUpDTO> {
    UnifiedResponse findCourseSignUpList(int pageNumber, int pageSize, int universityCode, int schoolID, int courseID);

    UnifiedResponse findStudentSignUpList(int pageNumber, int pageSize, int universityCode, int schoolID, int studentID);

    UnifiedResponse findTechnologyCourseSignUpList(int pageNumber, int pageSize, int technologyID);
}
