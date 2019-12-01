package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.entity.LearningPhaseEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.LearningPhaseMapper;
import com.shangshufang.apiservice.vo.LearningPhaseVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearningPhaseServiceImpl {
    @Autowired
    private LearningPhaseMapper myMapper;
    private Logger logger = LogManager.getLogger(LearningPhaseServiceImpl.class);

    public UnifiedResponse findList() {
        try {
            List<LearningPhaseVO> modelList = new ArrayList<>();
            List<LearningPhaseEntity> entityList =  myMapper.searchList();
            for (LearningPhaseEntity entity : entityList) {
                LearningPhaseVO model = new LearningPhaseVO();
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
