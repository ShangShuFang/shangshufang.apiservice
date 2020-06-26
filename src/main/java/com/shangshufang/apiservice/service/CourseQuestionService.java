package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseQuestionDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CourseQuestionService extends BaseService<CourseQuestionDTO> {
    UnifiedResponse findList(int pageNumber, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID);

    UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID);
}
