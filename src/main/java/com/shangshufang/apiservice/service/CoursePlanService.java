package com.shangshufang.apiservice.service;

import com.shangshufang.apiservice.dto.CoursePlanDTO;
import com.shangshufang.apiservice.vo.UnifiedResponse;

public interface CoursePlanService {
    UnifiedResponse finishCourseClass(CoursePlanDTO dto);
}
