package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
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
    public UnifiedResponse findTechnology() {
        try {
            List<LearningPathVO> modelList = new ArrayList<>();
            List<LearningPathEntity> entityList =  myMapper.searchTechnology();
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
    public UnifiedResponse findLearningPhase(int technologyID) {
        try {
            List<LearningPathVO> modelList = new ArrayList<>();
            List<LearningPathEntity> entityList =  myMapper.searchLearningPhase(technologyID);
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
    public UnifiedResponse findKnowledge(int technologyID, int learningPhase) {
        try {
            List<LearningPathVO> modelList = new ArrayList<>();
            List<LearningPathEntity> entityList =  myMapper.searchKnowledge(technologyID, learningPhase);
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
}
