package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.GrowingMapDTO;
import com.shangshufang.apiservice.entity.GrowingMapDetailEntity;
import com.shangshufang.apiservice.entity.GrowingMapEntity;
import com.shangshufang.apiservice.entity.LearningPathEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.GrowingMapDetailMapper;
import com.shangshufang.apiservice.mapper.GrowingMapMapper;
import com.shangshufang.apiservice.mapper.LearningPathMapper;
import com.shangshufang.apiservice.service.GrowingMapService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrowingMapServiceImpl implements GrowingMapService {
    @Autowired
    private GrowingMapMapper growingMapMapper;
    @Autowired
    private GrowingMapDetailMapper growingMapDetailMapper;
    @Autowired
    private LearningPathMapper learningPathMapper;

    private final Logger logger = LogManager.getLogger(GrowingMapServiceImpl.class);

    @Override
    public UnifiedResponse findMapList(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<GrowingMapVO> modelList = new ArrayList<>();
            int totalCount = growingMapMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<GrowingMapEntity> entityList =  growingMapMapper.searchList(startIndex, pageSize);
            for (GrowingMapEntity entity : entityList) {
                GrowingMapVO model = new GrowingMapVO();
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
    public UnifiedResponse findMap(int growingID) {
        try {
            GrowingMapEntity entity =  growingMapMapper.search(growingID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            GrowingMapVO model = new GrowingMapVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findLearningPhaseList(int growingID) {
        try{
            List<TechnologyLearningPathVO> modelList = new ArrayList<>();
            List<LearningPathEntity> technologyEntityList = growingMapDetailMapper.searchList4Technology(growingID);
            if (technologyEntityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (LearningPathEntity technologyEntity : technologyEntityList) {
                TechnologyLearningPathVO model = new TechnologyLearningPathVO();
                List<LearningPathVO> learningPhaseList = new ArrayList<>();

                ObjectConvertUtils.toBean(technologyEntity, model);
                List<LearningPathEntity> learningPhaseEntityList = growingMapDetailMapper.searchList4LearningPhase(growingID, technologyEntity.getTechnologyID());
                if (learningPhaseEntityList.isEmpty()) {
                    continue;
                }
                for (LearningPathEntity learningPhaseEntity : learningPhaseEntityList) {
                    LearningPathVO learningPhaseModel = new LearningPathVO();
                    ObjectConvertUtils.toBean(learningPhaseEntity, learningPhaseModel);
                    learningPhaseList.add(learningPhaseModel);
                }
                model.setLearningPhaseList(learningPhaseList);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findMapDetailList(int growingID) {
        try {
            GrowingMapVO model = new GrowingMapVO();
            List<GrowingMapDetailVO> growingMapDetailList = new ArrayList<>();

            GrowingMapEntity growingMapEntity = growingMapMapper.search(growingID);
            if (growingMapEntity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(growingMapEntity, model);

            List<GrowingMapDetailEntity> growingMapDetailEntityList = growingMapDetailMapper.searchList(growingID);
            for (GrowingMapDetailEntity growingMapDetailEntity : growingMapDetailEntityList) {
                GrowingMapDetailVO growingMapDetailModel = new GrowingMapDetailVO();
                List<LearningPathVO> knowledgeModelList = new ArrayList<>();
                ObjectConvertUtils.toBean(growingMapDetailEntity, growingMapDetailModel);
                List<LearningPathEntity> knowledgeEntityList =  learningPathMapper.searchKnowledge(growingMapDetailEntity.getTechnologyID(), growingMapDetailEntity.getLearningPhaseID());
                for (LearningPathEntity learningPathEntity : knowledgeEntityList) {
                    LearningPathVO knowledgeModel = new LearningPathVO();
                    ObjectConvertUtils.toBean(learningPathEntity, knowledgeModel);
                    knowledgeModelList.add(knowledgeModel);
                }
                growingMapDetailModel.setKnowledgeList(knowledgeModelList);
                growingMapDetailList.add(growingMapDetailModel);
            }
            model.setGrowingMapDetailList(growingMapDetailList);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkGrowingTargetExist(String growingTarget) {
        try {
            int count =  growingMapMapper.checkNameExist(growingTarget);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int growingID) {
        try {
            int affectRow = 0;
            affectRow += growingMapMapper.delete(growingID);
            affectRow += growingMapDetailMapper.delete(growingID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse save(GrowingMapDTO dto) {
        try {
            int affectRow;

            if (dto.getGrowingID() == 0) {
                affectRow = add(dto);
                return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
            }

            GrowingMapEntity entity = growingMapMapper.search(dto.getGrowingID());
            if (entity == null) {
                affectRow = add(dto);
                return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
            }

            affectRow = change(dto);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private int add(GrowingMapDTO dto) throws Exception {
        int affectRow = 0;
        GrowingMapEntity growingMapEntity = new GrowingMapEntity();
        List<GrowingMapDetailEntity> growingMapDetailEntityList = JsonUtils.deserializationToObject(dto.getDetailJson(), GrowingMapDetailEntity.class);

        ObjectConvertUtils.toBean(dto, growingMapEntity);
        growingMapEntity.setCreateUser(dto.getLoginUser());
        growingMapEntity.setUpdateUser(dto.getLoginUser());
        affectRow += growingMapMapper.insert(growingMapEntity);
        for (GrowingMapDetailEntity growingMapDetailEntity : growingMapDetailEntityList) {
            growingMapDetailEntity.setGrowingID(growingMapEntity.getGrowingID());
            growingMapDetailEntity.setCreateUser(dto.getLoginUser());
            growingMapDetailEntity.setUpdateUser(dto.getLoginUser());
            affectRow += growingMapDetailMapper.insert(growingMapDetailEntity);
        }
        return affectRow;
    }

    private int change(GrowingMapDTO dto) throws Exception {
        int affectRow = 0;
        GrowingMapEntity growingMapEntity = new GrowingMapEntity();
        List<GrowingMapDetailEntity> growingMapDetailEntityList = JsonUtils.deserializationToObject(dto.getDetailJson(), GrowingMapDetailEntity.class);

        ObjectConvertUtils.toBean(dto, growingMapEntity);
        growingMapEntity.setCreateUser(dto.getLoginUser());
        growingMapEntity.setUpdateUser(dto.getLoginUser());
        affectRow += growingMapMapper.update(growingMapEntity);
        affectRow += growingMapDetailMapper.delete(dto.getGrowingID());
        for (GrowingMapDetailEntity growingMapDetailEntity : growingMapDetailEntityList) {
            growingMapDetailEntity.setGrowingID(dto.getGrowingID());
            growingMapDetailEntity.setCreateUser(dto.getLoginUser());
            growingMapDetailEntity.setUpdateUser(dto.getLoginUser());
            affectRow += growingMapDetailMapper.insert(growingMapDetailEntity);
        }
        return affectRow;
    }
}
