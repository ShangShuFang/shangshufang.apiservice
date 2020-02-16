package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.dto.CourseQuestionLeaveMessageDTO;
import com.shangshufang.apiservice.entity.CourseQuestionLeaveMessageEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CourseQuestionLeaveMessageMapper;
import com.shangshufang.apiservice.service.BaseService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseQuestionLeaveMessageServiceImpl implements BaseService<CourseQuestionLeaveMessageDTO> {
    @Autowired
    private CourseQuestionLeaveMessageMapper leaveMessageMapper;
    private Logger logger = LogManager.getLogger(CourseQuestionLeaveMessageServiceImpl.class);

    @Override
    public UnifiedResponse add(CourseQuestionLeaveMessageDTO dto) {
        try {
            CourseQuestionLeaveMessageEntity entity = new CourseQuestionLeaveMessageEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = leaveMessageMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(CourseQuestionLeaveMessageDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(CourseQuestionLeaveMessageDTO dto) {
        try {
            CourseQuestionLeaveMessageEntity entity = new CourseQuestionLeaveMessageEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = leaveMessageMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
