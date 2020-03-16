package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyKnowledgeExercisesDTO;
import com.shangshufang.apiservice.entity.CoursePlanEntity;
import com.shangshufang.apiservice.entity.TechnologyKnowledgeExercisesDocumentEntity;
import com.shangshufang.apiservice.entity.TechnologyKnowledgeExercisesEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CoursePlanMapper;
import com.shangshufang.apiservice.mapper.TechnologyKnowledgeExercisesMapper;
import com.shangshufang.apiservice.service.TechnologyKnowledgeExercisesService;
import com.shangshufang.apiservice.vo.CourseExercisesKnowledgeVO;
import com.shangshufang.apiservice.vo.CourseExercisesVO;
import com.shangshufang.apiservice.vo.TechnologyKnowledgeExercisesVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyKnowledgeExercisesServiceImpl implements TechnologyKnowledgeExercisesService {
    @Autowired
    private TechnologyKnowledgeExercisesMapper myMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private TechnologyKnowledgeExercisesMapper knowledgeExercisesMapper;

    private Logger logger = LogManager.getLogger(TechnologyKnowledgeExercisesServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int learningPhaseID, int knowledgeID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyKnowledgeExercisesVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID, learningPhaseID, knowledgeID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyKnowledgeExercisesEntity> entityList =  myMapper.searchList(startIndex, pageSize, technologyID, learningPhaseID, knowledgeID);
            for (TechnologyKnowledgeExercisesEntity entity : entityList) {
                TechnologyKnowledgeExercisesVO model = new TechnologyKnowledgeExercisesVO();
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
    public UnifiedResponse findCourseAssignList(int universityCode, int schoolID, int courseID) {
        try {
            List<CourseExercisesVO> modelList = new ArrayList<>();
            List<CoursePlanEntity> coursePlanEntityList = coursePlanMapper.searchCourseClassList(universityCode, schoolID, courseID);
            if (coursePlanEntityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (CoursePlanEntity coursePlanEntity : coursePlanEntityList) {
                CourseExercisesVO model = new CourseExercisesVO();
                List<CoursePlanEntity> knowledgeList = coursePlanMapper.searchKnowledgeList4CourseClass(universityCode, schoolID, courseID, coursePlanEntity.getCourseClass());
                if(knowledgeList.isEmpty()){
                    continue;
                }

                ObjectConvertUtils.toBean(coursePlanEntity, model);
                List<CourseExercisesKnowledgeVO> knowledgeModelList = new ArrayList<>();
                for (CoursePlanEntity knowledgeEntity : knowledgeList) {
                    CourseExercisesKnowledgeVO knowledgeModel = new CourseExercisesKnowledgeVO();
                    ObjectConvertUtils.toBean(knowledgeEntity, knowledgeModel);

                    List<TechnologyKnowledgeExercisesEntity> exercisesEntityList = knowledgeExercisesMapper.searchList4Knowledge(knowledgeEntity.getTechnologyID(), knowledgeEntity.getLearningPhaseID(), knowledgeEntity.getKnowledgeID());
                    if(exercisesEntityList.isEmpty()){
                        continue;
                    }
                    List<TechnologyKnowledgeExercisesVO> knowledgeExercisesModelList = new ArrayList<>();
                    for (TechnologyKnowledgeExercisesEntity exercisesEntity : exercisesEntityList) {
                        TechnologyKnowledgeExercisesVO exercisesModel = new TechnologyKnowledgeExercisesVO();
                        ObjectConvertUtils.toBean(exercisesEntity, exercisesModel);
                        knowledgeExercisesModelList.add(exercisesModel);
                    }
                    knowledgeModel.setKnowledgeExercisesList(knowledgeExercisesModelList);
                    knowledgeModelList.add(knowledgeModel);
                }
                model.setKnowledgeList(knowledgeModelList);
                modelList.add(model);
            }

            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(TechnologyKnowledgeExercisesDTO dto) {
        try{
            int affectRow = 0;
            List<TechnologyKnowledgeExercisesDocumentEntity> exercisesDocumentEntityList = JsonUtils.deserializationToObject(dto.getExercisesJson(), TechnologyKnowledgeExercisesDocumentEntity.class);
            if(exercisesDocumentEntityList != null) {
                affectRow += myMapper.delete(dto.getTechnologyID(), dto.getLearningPhaseID(), dto.getKnowledgeID());
                for (TechnologyKnowledgeExercisesDocumentEntity exercisesDocumentEntity : exercisesDocumentEntityList) {
                    TechnologyKnowledgeExercisesEntity entity = new TechnologyKnowledgeExercisesEntity();
                    entity.setTechnologyID(dto.getTechnologyID());
                    entity.setLearningPhaseID(dto.getLearningPhaseID());
                    entity.setKnowledgeID(dto.getKnowledgeID());
                    entity.setDocumentUrl(exercisesDocumentEntity.getDocumentUrl());
                    entity.setAnswerUrl(exercisesDocumentEntity.getAnswerUrl());
                    entity.setCreateUser(dto.getLoginUser());
                    entity.setUpdateUser(dto.getLoginUser());
                    affectRow += myMapper.insert(entity);
                }
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        }catch (Exception ex){
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(TechnologyKnowledgeExercisesDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(TechnologyKnowledgeExercisesDTO dto) {
        return null;
    }
}
