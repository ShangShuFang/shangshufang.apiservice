package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.TechnologyDirectionEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyDirectionMapper;
import com.shangshufang.apiservice.service.TechnologyDirectionService;
import com.shangshufang.apiservice.vo.TechnologyDirectionVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyDirectionServiceImpl implements TechnologyDirectionService {
    @Autowired
    private TechnologyDirectionMapper myMapper;
    private Logger logger = LogManager.getLogger(TechnologyDirectionServiceImpl.class);

    @Override
    public UnifiedResponse findList4Technology(int technologyID) {
        try {
            List<TechnologyDirectionVO> modelList = new ArrayList<>();

            List<TechnologyDirectionEntity> entityList =  myMapper.searchList4Technology(technologyID);
            for (TechnologyDirectionEntity entity : entityList) {
                TechnologyDirectionVO model = new TechnologyDirectionVO();
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
    public UnifiedResponse findList4Direction(int directionID) {
        try {
            List<TechnologyDirectionVO> modelList = new ArrayList<>();

            List<TechnologyDirectionEntity> entityList =  myMapper.searchList4Direction(directionID);
            for (TechnologyDirectionEntity entity : entityList) {
                TechnologyDirectionVO model = new TechnologyDirectionVO();
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
