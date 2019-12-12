package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.ExercisesDTO;
import com.shangshufang.apiservice.entity.ExercisesEntity;
import com.shangshufang.apiservice.entity.ExercisesKnowledgeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ExercisesDocumentMapper;
import com.shangshufang.apiservice.mapper.ExercisesImageMapper;
import com.shangshufang.apiservice.mapper.ExercisesKnowledgeMapper;
import com.shangshufang.apiservice.mapper.ExercisesMapper;
import com.shangshufang.apiservice.service.ExercisesService;
import com.shangshufang.apiservice.vo.ExercisesVO;
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
            List<ExercisesVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(exercisesType.equals("A") ? null : exercisesType, technologyID, learningPhaseID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<ExercisesEntity> entityList =  myMapper.searchList(startIndex, pageSize, exercisesType.equals("A") ? null : exercisesType, technologyID, learningPhaseID);
            for (ExercisesEntity entity : entityList) {
                ExercisesVO model = new ExercisesVO();
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
    public UnifiedResponse find(int exercisesID) {
        try {
            ExercisesVO model = new ExercisesVO();
            ExercisesEntity entity = myMapper.search(exercisesID);
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
            ExercisesEntity entity = new ExercisesEntity();
            List<ExercisesKnowledgeEntity> exercisesKnowledgeEntityList = JsonUtils.deserializationToObject(dto.getKnowledgeListJson(), ExercisesKnowledgeEntity.class);

            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());

            //保存基本信息
            int affectRow = myMapper.insert(entity);

            //保存知识点信息
            for (ExercisesKnowledgeEntity exercisesKnowledgeEntity : exercisesKnowledgeEntityList) {
                exercisesKnowledgeEntity.setExercisesID(entity.getExercisesID());
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
            ExercisesEntity entity = new ExercisesEntity();
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
            ExercisesEntity entity = new ExercisesEntity();
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
