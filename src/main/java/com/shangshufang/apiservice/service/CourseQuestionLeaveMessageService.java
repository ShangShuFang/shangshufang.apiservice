package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CourseQuestionLeaveMessageDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CourseQuestionLeaveMessageService extends BaseService<CourseQuestionLeaveMessageDTO> {
    UnifiedResponse findList(int questionID);

    UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID);
}
