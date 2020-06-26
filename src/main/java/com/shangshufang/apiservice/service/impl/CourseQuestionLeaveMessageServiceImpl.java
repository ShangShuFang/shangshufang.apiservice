package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CourseQuestionLeaveMessageDTO;
import com.shangshufang.apiservice.entity.CourseQuestionEntity;
import com.shangshufang.apiservice.entity.CourseQuestionLeaveMessageEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CourseQuestionLeaveMessageMapper;
import com.shangshufang.apiservice.service.BaseService;
import com.shangshufang.apiservice.service.CourseQuestionLeaveMessageService;
import com.shangshufang.apiservice.vo.CourseQuestionLeaveMessageVO;
import com.shangshufang.apiservice.vo.CourseQuestionVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseQuestionLeaveMessageServiceImpl implements CourseQuestionLeaveMessageService {
    @Autowired
    private CourseQuestionLeaveMessageMapper leaveMessageMapper;
    private final Logger logger = LogManager.getLogger(CourseQuestionLeaveMessageServiceImpl.class);

    @Override
    public UnifiedResponse findList(int questionID) {
        try {
            List<CourseQuestionLeaveMessageVO> modelList = new ArrayList<>();
            List<CourseQuestionLeaveMessageEntity> entityList =  leaveMessageMapper.searchList(questionID);
            for (CourseQuestionLeaveMessageEntity entity : entityList) {
                CourseQuestionLeaveMessageVO model = new CourseQuestionLeaveMessageVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseQuestionLeaveMessageVO> modelList = new ArrayList<>();
            int totalCount = leaveMessageMapper.searchTotalCount4Student(studentID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<CourseQuestionLeaveMessageEntity> entityList = leaveMessageMapper.searchList4Student(startIndex, pageSize, studentID);
            for (CourseQuestionLeaveMessageEntity entity : entityList) {
                CourseQuestionLeaveMessageVO model = new CourseQuestionLeaveMessageVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

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
