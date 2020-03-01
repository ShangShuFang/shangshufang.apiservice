package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.SuggestTypeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.SuggestTypeMapper;
import com.shangshufang.apiservice.service.SuggestTypeService;
import com.shangshufang.apiservice.vo.SuggestTypeVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestTypeServiceImpl implements SuggestTypeService {
    @Autowired
    private SuggestTypeMapper myMapper;
    private Logger logger = LogManager.getLogger(SuggestTypeServiceImpl.class);

    @Override
    public UnifiedResponse findList(String portal) {
        try {
            List<SuggestTypeVO> modelList = new ArrayList<>();
            List<SuggestTypeEntity> entityList =  myMapper.searchList(portal);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (SuggestTypeEntity entity : entityList) {
                SuggestTypeVO model = new SuggestTypeVO();
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
