package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.LearningPathDTO;
import com.shangshufang.apiservice.entity.LearningPathEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.LearningPathMapper;
import com.shangshufang.apiservice.service.LearningPathService;
import com.shangshufang.apiservice.vo.LearningPathVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearningPathServiceImpl implements LearningPathService {
    @Autowired
    private LearningPathMapper myMapper;
    private Logger logger = LogManager.getLogger(LearningPathServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int learningPhase) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<LearningPathVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID, learningPhase);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<LearningPathEntity> entityList =  myMapper.searchList(startIndex, pageSize, technologyID, learningPhase);
            for (LearningPathEntity entity : entityList) {
                LearningPathVO model = new LearningPathVO();
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
    public UnifiedResponse findUsingLearningPhase(int technologyID) {
        try {
            List<LearningPathVO> modelList = new ArrayList<>();
            List<LearningPathEntity> entityList =  myMapper.searchUsingLearningPhase(technologyID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (LearningPathEntity entity : entityList) {
                LearningPathVO model = new LearningPathVO();
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
    public UnifiedResponse findUsingKnowledge(int technologyID, int learningPhase) {
        try {
            List<LearningPathVO> modelList = new ArrayList<>();
            List<LearningPathEntity> entityList =  myMapper.searchUsingKnowledge(technologyID, learningPhase);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (LearningPathEntity entity : entityList) {
                LearningPathVO model = new LearningPathVO();
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
    public UnifiedResponse delete(int technologyID, int learningPhase) {
        try {
            int affectRow = myMapper.delete(technologyID, learningPhase);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(LearningPathDTO dto) {
        try {
            String[] usingKnowledgeArray = dto.getKnowledgeIdList().split(",");
            int affectRow = 0;
            for (String knowledge : usingKnowledgeArray) {
                LearningPathEntity entity = new LearningPathEntity();
                entity.setTechnologyID(dto.getTechnologyID());
                entity.setLearningPhaseID(dto.getLearningPhaseID());
                entity.setKnowledgeID(Integer.parseInt(knowledge));
                entity.setCreateUser(dto.getLoginUser());
                entity.setUpdateUser(dto.getLoginUser());
                affectRow += myMapper.insert(entity);
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(LearningPathDTO dto) {
        try {
            String[] usingKnowledgeArray = dto.getKnowledgeIdList().split(",");
            int affectRow = myMapper.delete(dto.getTechnologyID(), dto.getLearningPhaseID());
            for (String knowledge : usingKnowledgeArray) {
                LearningPathEntity entity = new LearningPathEntity();
                entity.setTechnologyID(dto.getTechnologyID());
                entity.setLearningPhaseID(dto.getLearningPhaseID());
                entity.setKnowledgeID(Integer.parseInt(knowledge));
                entity.setCreateUser(dto.getLoginUser());
                entity.setUpdateUser(dto.getLoginUser());
                affectRow += myMapper.insert(entity);
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(LearningPathDTO dto) {
        try {
            LearningPathEntity entity = new LearningPathEntity();
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
