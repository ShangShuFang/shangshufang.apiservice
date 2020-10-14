package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.ExerciseWarehouseKnowledgeChoiceQuestionDTO;
import com.shangshufang.apiservice.entity.ExerciseWarehouseKnowledgeChoiceQuestionEntity;
import com.shangshufang.apiservice.entity.ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ExerciseWarehouseKnowledgeChoiceQuestionMapper;
import com.shangshufang.apiservice.mapper.ExerciseWarehouseKnowledgeChoiceQuestionOptionMapper;
import com.shangshufang.apiservice.service.ExerciseWarehouseKnowledgeChoiceQuestionService;
import com.shangshufang.apiservice.vo.ExerciseWarehouseKnowledgeChoiceQuestionOptionVO;
import com.shangshufang.apiservice.vo.ExerciseWarehouseKnowledgeChoiceQuestionVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseWarehouseKnowledgeChoiceQuestionServiceImpl implements ExerciseWarehouseKnowledgeChoiceQuestionService {
    @Autowired
    private ExerciseWarehouseKnowledgeChoiceQuestionMapper myMapper;
    @Autowired
    private ExerciseWarehouseKnowledgeChoiceQuestionOptionMapper optionMapper;
    private final Logger logger = LogManager.getLogger(ExerciseWarehouseKnowledgeChoiceQuestionServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int knowledgeID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<ExerciseWarehouseKnowledgeChoiceQuestionVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(technologyID, knowledgeID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ExerciseWarehouseKnowledgeChoiceQuestionEntity> entityList =  myMapper.searchList(technologyID, knowledgeID, dataStatus, startIndex, pageSize);
            for (ExerciseWarehouseKnowledgeChoiceQuestionEntity entity : entityList) {
                List<ExerciseWarehouseKnowledgeChoiceQuestionOptionVO> optionList = new ArrayList<>();

                List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> optionEntityList = optionMapper.searchList(technologyID, knowledgeID, entity.getExercisesID());
                if (!optionEntityList.isEmpty()) {
                    for (ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity optionEntity : optionEntityList) {
                        ExerciseWarehouseKnowledgeChoiceQuestionOptionVO optionVO = new ExerciseWarehouseKnowledgeChoiceQuestionOptionVO();
                        ObjectConvertUtils.toBean(optionEntity, optionVO);
                        optionList.add(optionVO);
                    }
                }
                ExerciseWarehouseKnowledgeChoiceQuestionVO model = new ExerciseWarehouseKnowledgeChoiceQuestionVO();
                ObjectConvertUtils.toBean(entity, model);
                model.setChoiceOptions(optionList);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int technologyID, int knowledgeID, int exercisesID) {
        try {
            int affectRow = 0;
            affectRow += myMapper.delete(technologyID, knowledgeID, exercisesID);
            affectRow += optionMapper.delete(technologyID, knowledgeID, exercisesID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(ExerciseWarehouseKnowledgeChoiceQuestionDTO dto) {
        try {
            int affectRow = 0;
            List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> optionEntityList = JsonUtils.deserializationToObject(dto.getChoiceOptionsJson(), ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity.class);
            ExerciseWarehouseKnowledgeChoiceQuestionEntity entity = new ExerciseWarehouseKnowledgeChoiceQuestionEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            affectRow += myMapper.insert(entity);
            if (optionEntityList != null) {
                affectRow += optionMapper.delete(dto.getTechnologyID(), dto.getKnowledgeID(), entity.getExercisesID());
                for (ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity optionEntity : optionEntityList) {
                    optionEntity.setTechnologyID(dto.getTechnologyID());
                    optionEntity.setKnowledgeID(dto.getKnowledgeID());
                    optionEntity.setExercisesID(entity.getExercisesID());
                    optionEntity.setCreateUser(dto.getLoginUser());
                    optionEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += optionMapper.insert(optionEntity);
                }
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow, entity.getExercisesID());
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ExerciseWarehouseKnowledgeChoiceQuestionDTO dto) {
        try {
            int affectRow = 0;
            List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> optionEntityList = JsonUtils.deserializationToObject(dto.getChoiceOptionsJson(), ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity.class);
            ExerciseWarehouseKnowledgeChoiceQuestionEntity entity = new ExerciseWarehouseKnowledgeChoiceQuestionEntity();

            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            affectRow += myMapper.update(entity);

            if (optionEntityList != null) {
                affectRow += optionMapper.delete(dto.getTechnologyID(), dto.getKnowledgeID(), entity.getExercisesID());
                for (ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity optionEntity : optionEntityList) {
                    optionEntity.setTechnologyID(dto.getTechnologyID());
                    optionEntity.setKnowledgeID(dto.getKnowledgeID());
                    optionEntity.setExercisesID(dto.getExercisesID());
                    optionEntity.setCreateUser(dto.getLoginUser());
                    optionEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += optionMapper.insert(optionEntity);
                }
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(ExerciseWarehouseKnowledgeChoiceQuestionDTO dto) {
        try {
            ExerciseWarehouseKnowledgeChoiceQuestionEntity entity = new ExerciseWarehouseKnowledgeChoiceQuestionEntity();
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
