package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.BusinessAnalyseLogDTO;
import com.shangshufang.apiservice.entity.BusinessAnalyseLogEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.BusinessAnalyseLogMapper;
import com.shangshufang.apiservice.service.BusinessAnalyseLogService;
import com.shangshufang.apiservice.vo.BusinessAnalyseLogVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessAnalyseLogServiceImpl implements BusinessAnalyseLogService {
    @Autowired
    private BusinessAnalyseLogMapper myMapper;
    private Logger logger = LogManager.getLogger(BusinessAnalyseLogServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<BusinessAnalyseLogVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount();
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<BusinessAnalyseLogEntity> entityList =  myMapper.searchList(startIndex, pageSize);
            for (BusinessAnalyseLogEntity entity : entityList) {
                BusinessAnalyseLogVO model = new BusinessAnalyseLogVO();
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
    public UnifiedResponse add(BusinessAnalyseLogDTO dto) {
        try {
            BusinessAnalyseLogEntity entity = new BusinessAnalyseLogEntity();
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
}
