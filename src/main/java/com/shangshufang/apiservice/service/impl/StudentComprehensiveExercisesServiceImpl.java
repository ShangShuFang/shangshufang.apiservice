package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesDTO;
import com.shangshufang.apiservice.entity.StudentComprehensiveExercisesEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.StudentComprehensiveExercisesMapper;
import com.shangshufang.apiservice.service.StudentComprehensiveExercisesService;
import com.shangshufang.apiservice.vo.StudentComprehensiveExercisesVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentComprehensiveExercisesServiceImpl implements StudentComprehensiveExercisesService {
    @Autowired
    private StudentComprehensiveExercisesMapper myMapper;
    private final Logger logger = LogManager.getLogger(StudentComprehensiveExercisesServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int studentID, int directionID, int categoryID, int technologyID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentComprehensiveExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(studentID, directionID, categoryID, technologyID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentComprehensiveExercisesEntity> entityList =  myMapper.searchList(startIndex, pageSize, studentID, directionID, categoryID, technologyID, dataStatus);
            for (StudentComprehensiveExercisesEntity entity : entityList) {
                StudentComprehensiveExercisesVO model = new StudentComprehensiveExercisesVO();
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
    public UnifiedResponse checkCollected(int studentID, int exercisesID) {
        try {
            int totalCount = myMapper.checkCollected(studentID, exercisesID);
            boolean isCollection = totalCount > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, isCollection);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int studentID, int exercisesID) {
        try {
            int affectRow = myMapper.delete(studentID, exercisesID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(StudentComprehensiveExercisesDTO dto) {
        try {
            StudentComprehensiveExercisesEntity entity = new StudentComprehensiveExercisesEntity();
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
    public UnifiedResponse change(StudentComprehensiveExercisesDTO dto) {
        try {
            StudentComprehensiveExercisesEntity entity = new StudentComprehensiveExercisesEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(StudentComprehensiveExercisesDTO dto) {
        return null;
    }
}