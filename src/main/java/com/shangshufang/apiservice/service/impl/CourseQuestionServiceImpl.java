package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CourseQuestionDTO;
import com.shangshufang.apiservice.entity.CourseQuestionEntity;
import com.shangshufang.apiservice.entity.CourseQuestionLeaveMessageEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CourseQuestionLeaveMessageMapper;
import com.shangshufang.apiservice.mapper.CourseQuestionMapper;
import com.shangshufang.apiservice.service.CourseQuestionService;
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
public class CourseQuestionServiceImpl implements CourseQuestionService {
    @Autowired
    private CourseQuestionMapper courseQuestionMapper;
    @Autowired
    private CourseQuestionLeaveMessageMapper leaveMessageMapper;
    private Logger logger = LogManager.getLogger(CourseQuestionServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseQuestionVO> modelList = new ArrayList<>();
            int totalCount = courseQuestionMapper.searchTotalCount(courseUniversityCode, courseSchoolID, courseID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<CourseQuestionEntity> entityList =  courseQuestionMapper.searchList(startIndex, pageSize, courseUniversityCode, courseSchoolID, courseID);
            for (CourseQuestionEntity entity : entityList) {
                CourseQuestionVO model = new CourseQuestionVO();
                List<CourseQuestionLeaveMessageVO> leaveMessageList = new ArrayList<>();

                List<CourseQuestionLeaveMessageEntity> leaveMessageEntityList = leaveMessageMapper.searchList(entity.getQuestionID());
                for (CourseQuestionLeaveMessageEntity leaveMessageEntity : leaveMessageEntityList) {
                    CourseQuestionLeaveMessageVO leaveMessageModel = new CourseQuestionLeaveMessageVO();
                    ObjectConvertUtils.toBean(leaveMessageEntity, leaveMessageModel);
                    leaveMessageList.add(leaveMessageModel);
                }

                ObjectConvertUtils.toBean(entity, model);
                model.setLeaveMessageList(leaveMessageList);

                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseQuestionVO> modelList = new ArrayList<>();
            int totalCount = courseQuestionMapper.searchTotalCount4Student(studentID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<CourseQuestionEntity> entityList = courseQuestionMapper.searchList4Student(startIndex, pageSize, studentID);
            for (CourseQuestionEntity entity : entityList) {
                CourseQuestionVO model = new CourseQuestionVO();
                int answerTotalCount = leaveMessageMapper.searchTotalCount(entity.getQuestionID());
                ObjectConvertUtils.toBean(entity, model);
                model.setLeaveMessageCount(answerTotalCount);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(CourseQuestionDTO dto) {
        try {
            CourseQuestionEntity entity = new CourseQuestionEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = courseQuestionMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(CourseQuestionDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(CourseQuestionDTO dto) {
        try {
            CourseQuestionEntity entity = new CourseQuestionEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = courseQuestionMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
