package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.ExerciseWarehouseKnowledgeBlankQuestionDTO;
import com.shangshufang.apiservice.entity.ExerciseWarehouseKnowledgeBlankQuestionEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ExerciseWarehouseKnowledgeBlankQuestionMapper;
import com.shangshufang.apiservice.service.ExerciseWarehouseKnowledgeBlankQuestionService;
import com.shangshufang.apiservice.vo.ExerciseWarehouseKnowledgeBlankQuestionVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseWarehouseKnowledgeBlankQuestionServiceImpl implements ExerciseWarehouseKnowledgeBlankQuestionService {
    @Autowired
    private ExerciseWarehouseKnowledgeBlankQuestionMapper myMapper;
    private final Logger logger = LogManager.getLogger(ExerciseWarehouseKnowledgeBlankQuestionServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int knowledgeID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<ExerciseWarehouseKnowledgeBlankQuestionVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(technologyID, knowledgeID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ExerciseWarehouseKnowledgeBlankQuestionEntity> entityList =  myMapper.searchList(technologyID, knowledgeID, dataStatus, startIndex, pageSize);
            for (ExerciseWarehouseKnowledgeBlankQuestionEntity entity : entityList) {
                ExerciseWarehouseKnowledgeBlankQuestionVO model = new ExerciseWarehouseKnowledgeBlankQuestionVO();
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
    public UnifiedResponse delete(int technologyID, int knowledgeID, int exercisesID) {
        try {
            int affectRow = myMapper.delete(technologyID, knowledgeID, exercisesID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(ExerciseWarehouseKnowledgeBlankQuestionDTO dto) {
        try {
            ExerciseWarehouseKnowledgeBlankQuestionEntity entity = new ExerciseWarehouseKnowledgeBlankQuestionEntity();
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
    public UnifiedResponse change(ExerciseWarehouseKnowledgeBlankQuestionDTO dto) {
        try {
            ExerciseWarehouseKnowledgeBlankQuestionEntity entity = new ExerciseWarehouseKnowledgeBlankQuestionEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(ExerciseWarehouseKnowledgeBlankQuestionDTO dto) {
        try {
            ExerciseWarehouseKnowledgeBlankQuestionEntity entity = new ExerciseWarehouseKnowledgeBlankQuestionEntity();
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
