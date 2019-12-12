package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.ExercisesKnowledgeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ExercisesKnowledgeMapper;
import com.shangshufang.apiservice.service.ExercisesKnowledgeService;
import com.shangshufang.apiservice.vo.ExercisesKnowledgeVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExercisesKnowledgeServiceImpl implements ExercisesKnowledgeService {
    @Autowired
    private ExercisesKnowledgeMapper myMapper;
    private Logger logger = LogManager.getLogger(ExercisesKnowledgeServiceImpl.class);

    @Override
    public UnifiedResponse findList(int exercisesID) {
        try {
            List<ExercisesKnowledgeVO> modelList = new ArrayList<>();
            List<ExercisesKnowledgeEntity> entityList =  myMapper.searchList(exercisesID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ExercisesKnowledgeEntity entity : entityList) {
                ExercisesKnowledgeVO model = new ExercisesKnowledgeVO();
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
