package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.ChinaRegionEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ChinaRegionMapper;
import com.shangshufang.apiservice.service.ChinaRegionService;
import com.shangshufang.apiservice.vo.ChinaRegionVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChinaRegionServiceImpl implements ChinaRegionService {
    @Autowired
    private ChinaRegionMapper myMapper;
    private Logger logger = LogManager.getLogger(ChinaRegionServiceImpl.class);

    @Override
    public UnifiedResponse findList(int regionParentCode) {
        try {
            List<ChinaRegionVO> modelList = new ArrayList<>();
            List<ChinaRegionEntity> entityList =  myMapper.searchList(regionParentCode);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ChinaRegionEntity entity : entityList) {
                ChinaRegionVO model = new ChinaRegionVO();
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
