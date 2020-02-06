package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ExercisesTypeConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.ExercisesDTO;
import com.shangshufang.apiservice.entity.CourseExercisesEntity;
import com.shangshufang.apiservice.entity.ExercisesDocumentEntity;
import com.shangshufang.apiservice.entity.ExercisesSingleEntity;
import com.shangshufang.apiservice.entity.ExercisesKnowledgeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ExercisesDocumentMapper;
import com.shangshufang.apiservice.mapper.ExercisesImageMapper;
import com.shangshufang.apiservice.mapper.ExercisesKnowledgeMapper;
import com.shangshufang.apiservice.mapper.ExercisesMapper;
import com.shangshufang.apiservice.service.ExercisesService;
import com.shangshufang.apiservice.vo.CourseExercisesVO;
import com.shangshufang.apiservice.vo.ExercisesDocumentVO;
import com.shangshufang.apiservice.vo.ExercisesSingleVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExercisesServiceImpl implements ExercisesService {
    @Autowired
    private ExercisesMapper myMapper;
    @Autowired
    private ExercisesImageMapper exercisesImageMapper;
    @Autowired
    private ExercisesDocumentMapper exercisesDocumentMapper;
    @Autowired
    private ExercisesKnowledgeMapper exercisesKnowledgeMapper;

    private Logger logger = LogManager.getLogger(ExercisesServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String exercisesType, int technologyID, int learningPhaseID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            switch (exercisesType){
                case ExercisesTypeConstant.Single:
                    return findSingleList(startIndex, pageSize, technologyID, learningPhaseID);
                case ExercisesTypeConstant.Comprehensive:
                    return findComprehensiveList(startIndex, pageSize, technologyID);
                case ExercisesTypeConstant.Project:
                    return findProjectList(startIndex, pageSize);
                default:
                    return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findCourseExercisesList(int universityCode, int schoolID, int courseID) {
        try{
            List<CourseExercisesVO> modelList = new ArrayList<>();
            List<CourseExercisesEntity> entityList =  myMapper.searchCourseExercisesList(universityCode, schoolID, courseID);
            if(entityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (CourseExercisesEntity entity : entityList) {
                List<ExercisesDocumentVO> taskModelList = new ArrayList<>();
                List<ExercisesDocumentEntity> taskEntityList = exercisesDocumentMapper.searchList(entity.getExercisesID());
                if(!taskEntityList.isEmpty()){
                    for (ExercisesDocumentEntity exercisesDocumentEntity : taskEntityList) {
                        ExercisesDocumentVO taskModel = new ExercisesDocumentVO();
                        ObjectConvertUtils.toBean(exercisesDocumentEntity, taskModel);
                        taskModelList.add(taskModel);
                    }
                }
                CourseExercisesVO model = new CourseExercisesVO();
                ObjectConvertUtils.toBean(entity, model);
                model.setDocumentList(taskModelList);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        }catch (Exception ex){
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse find(int exercisesID) {
        try {
            ExercisesSingleVO model = new ExercisesSingleVO();
            ExercisesSingleEntity entity = myMapper.search(exercisesID);
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
    public UnifiedResponse checkExercisesCodeExist(String exercisesCode) {
        try {
            int count =  myMapper.checkExercisesCodeExist(exercisesCode);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int exercisesID) {
        try {
            int affectRow = myMapper.delete(exercisesID);
            affectRow += exercisesImageMapper.delete(exercisesID);
            affectRow += exercisesDocumentMapper.delete(exercisesID);
            affectRow += exercisesKnowledgeMapper.delete(exercisesID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(ExercisesDTO dto) {
        try {
            ExercisesSingleEntity entity = new ExercisesSingleEntity();
            List<ExercisesKnowledgeEntity> exercisesKnowledgeEntityList = JsonUtils.deserializationToObject(dto.getKnowledgeListJson(), ExercisesKnowledgeEntity.class);

            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());

            //保存基本信息
            int affectRow = myMapper.insert(entity);

            //保存知识点信息
            for (ExercisesKnowledgeEntity exercisesKnowledgeEntity : exercisesKnowledgeEntityList) {
                exercisesKnowledgeEntity.setExercisesID(entity.getExercisesID());
                exercisesKnowledgeEntity.setExercisesType(dto.getExercisesType());
                exercisesKnowledgeEntity.setCreateUser(dto.getLoginUser());
                exercisesKnowledgeEntity.setUpdateUser(dto.getLoginUser());
                affectRow += exercisesKnowledgeMapper.insert(exercisesKnowledgeEntity);
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ExercisesDTO dto) {
        try {
            ExercisesSingleEntity entity = new ExercisesSingleEntity();
            List<ExercisesKnowledgeEntity> exercisesKnowledgeEntityList = JsonUtils.deserializationToObject(dto.getKnowledgeListJson(), ExercisesKnowledgeEntity.class);

            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());

            //更新基本信息
            int affectRow = myMapper.update(entity);

            //更新知识点信息
            affectRow += exercisesKnowledgeMapper.delete(dto.getExercisesID());
            for (ExercisesKnowledgeEntity exercisesKnowledgeEntity : exercisesKnowledgeEntityList) {
                exercisesKnowledgeEntity.setExercisesID(dto.getExercisesID());
                exercisesKnowledgeEntity.setCreateUser(dto.getLoginUser());
                exercisesKnowledgeEntity.setUpdateUser(dto.getLoginUser());
                affectRow += exercisesKnowledgeMapper.insert(exercisesKnowledgeEntity);
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(ExercisesDTO dto) {
        try {
            ExercisesSingleEntity entity = new ExercisesSingleEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private UnifiedResponse findSingleList(int startIndex, int pageSize, int technologyID, int learningPhaseID) throws Exception {
        List<ExercisesSingleVO> modelList = new ArrayList<>();
        int totalCount = myMapper.searchSingleTotalCount(technologyID, learningPhaseID);
        if(totalCount == 0){
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
        }
        List<ExercisesSingleEntity> entityList =  myMapper.searchSingleList(startIndex, pageSize, technologyID, learningPhaseID);
        for (ExercisesSingleEntity entity : entityList) {
            List<ExercisesDocumentVO> taskModelList = new ArrayList<>();
            List<ExercisesDocumentEntity> taskEntityList = exercisesDocumentMapper.searchList(entity.getExercisesID());
            if(!taskEntityList.isEmpty()){
                for (ExercisesDocumentEntity exercisesDocumentEntity : taskEntityList) {
                    ExercisesDocumentVO taskModel = new ExercisesDocumentVO();
                    ObjectConvertUtils.toBean(exercisesDocumentEntity, taskModel);
                    taskModelList.add(taskModel);
                }
            }
            ExercisesSingleVO model = new ExercisesSingleVO();
            ObjectConvertUtils.toBean(entity, model);
            model.setDocumentList(taskModelList);
            modelList.add(model);
        }
        return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
    }

    private UnifiedResponse findComprehensiveList(int startIndex, int pageSize, int technologyID) throws Exception {
        return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
    }

    private UnifiedResponse findProjectList(int startIndex, int pageSize) throws Exception {
        return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
    }
}
