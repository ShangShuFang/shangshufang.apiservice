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
    public UnifiedResponse findList(int pageNumber,
                                    int pageSize,
                                    int programLanguage,
                                    int universityCode,
                                    int schoolID,
                                    int majorID,
                                    String fullName,
                                    String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentComprehensiveExercisesVO> modelList = new ArrayList<>();
            fullName = fullName.equals(ParameterConstant.NO_PARAMETER) ? null : fullName;
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(programLanguage, universityCode, schoolID, majorID, fullName, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentComprehensiveExercisesEntity> entityList =  myMapper.searchList(startIndex, pageSize, programLanguage, universityCode, schoolID, majorID, fullName, dataStatus);
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
    public UnifiedResponse findList4Student(int pageNumber,
                                            int pageSize,
                                            int studentID,
                                            int directionCode,
                                            int programLanguage,
                                            int difficultyLevelCode,
                                            String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentComprehensiveExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount4Student(studentID, directionCode, programLanguage, difficultyLevelCode, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentComprehensiveExercisesEntity> entityList =  myMapper.searchList4Student(startIndex, pageSize, studentID, directionCode, programLanguage, difficultyLevelCode, dataStatus);
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
    public UnifiedResponse find(int studentID, int exercisesID) {
        try {
            StudentComprehensiveExercisesVO model = new StudentComprehensiveExercisesVO();
            StudentComprehensiveExercisesEntity entity =  myMapper.search(studentID, exercisesID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(entity, model);

            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findComprehensiveExercisesWithTechnology(int studentID, int technologyID) {
        try {
            List<StudentComprehensiveExercisesVO> modelList = new ArrayList<>();
            List<StudentComprehensiveExercisesEntity> entityList =
                    myMapper.searchComprehensiveExercisesWithTechnology(studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (StudentComprehensiveExercisesEntity entity : entityList) {
                StudentComprehensiveExercisesVO model = new StudentComprehensiveExercisesVO();
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
        try {
            StudentComprehensiveExercisesEntity entity = new StudentComprehensiveExercisesEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
