package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CourseSignUpDTO;
import com.shangshufang.apiservice.entity.CourseSignUpEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CourseSignUpMapper;
import com.shangshufang.apiservice.service.CourseSignUpService;
import com.shangshufang.apiservice.vo.CourseSignUpVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseSignUpServiceImpl implements CourseSignUpService {
    @Autowired
    private CourseSignUpMapper myMapper;
    private Logger logger = LogManager.getLogger(CourseSignUpServiceImpl.class);

    @Override
    public UnifiedResponse findCourseSignUpList(int pageNumber, int pageSize, int universityCode, int schoolID, int courseID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseSignUpVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchCourseSignUpTotalCount(universityCode, schoolID, courseID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CourseSignUpEntity> entityList =  myMapper.searchCourseSignUpList(startIndex, pageSize, universityCode, schoolID, courseID);
            for (CourseSignUpEntity entity : entityList) {
                CourseSignUpVO model = new CourseSignUpVO();
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
    public UnifiedResponse findStudentSignUpList(int pageNumber, int pageSize, int universityCode, int schoolID, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseSignUpVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchStudentSignUpTotalCount(universityCode, schoolID, studentID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CourseSignUpEntity> entityList =  myMapper.searchStudentSignUpList(startIndex, pageSize, universityCode, schoolID, studentID);
            for (CourseSignUpEntity entity : entityList) {
                CourseSignUpVO model = new CourseSignUpVO();
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
    public UnifiedResponse findTechnologyCourseSignUpList(int pageNumber, int pageSize, int technologyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseSignUpVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTechnologyCourseSignUpTotalCount(technologyID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CourseSignUpEntity> entityList =  myMapper.searchTechnologyCourseSignUpList(startIndex, pageSize, technologyID);
            for (CourseSignUpEntity entity : entityList) {
                CourseSignUpVO model = new CourseSignUpVO();
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
    public UnifiedResponse add(CourseSignUpDTO dto) {
        try {
            CourseSignUpEntity entity = new CourseSignUpEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(CourseSignUpDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(CourseSignUpDTO dto) {
        try {
            CourseSignUpEntity entity = new CourseSignUpEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
