package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.CourseStatus;
import com.shangshufang.apiservice.dto.CoursePlanDTO;
import com.shangshufang.apiservice.entity.CoursePlanEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CoursePlanMapper;
import com.shangshufang.apiservice.service.CoursePlanService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {
    @Autowired
    private CoursePlanMapper myMapper;
    private Logger logger = LogManager.getLogger(CoursePlanServiceImpl.class);

    @Override
    public UnifiedResponse finishCourseClass(CoursePlanDTO dto) {
        try {
            CoursePlanEntity entity = new CoursePlanEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setDataStatus(CourseStatus.Finished);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
