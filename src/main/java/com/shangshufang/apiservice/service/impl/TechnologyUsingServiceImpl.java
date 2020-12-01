package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyUsingDTO;
import com.shangshufang.apiservice.entity.TechnologyUsingEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyUsingMapper;
import com.shangshufang.apiservice.service.TechnologyUsingService;
import com.shangshufang.apiservice.vo.TechnologyUsingVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyUsingServiceImpl implements TechnologyUsingService {
    @Autowired
    private TechnologyUsingMapper myMapper;
    private final Logger logger = LogManager.getLogger(TechnologyUsingServiceImpl.class);

    @Override
    public UnifiedResponse findUsingTechnologyList(int companyID) {
        try {
            List<TechnologyUsingVO> modelList = new ArrayList<>();

            List<TechnologyUsingEntity> entityList = myMapper.searchUsingTechnologyList(companyID);
            for (TechnologyUsingEntity entity : entityList) {
                TechnologyUsingVO model = new TechnologyUsingVO();
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
    public UnifiedResponse findCompanyList(int pageNumber, int pageSize, int technologyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyUsingVO> modelList = new ArrayList<>();

            int totalCount = myMapper.searchCompanyTotalCount(technologyID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            List<TechnologyUsingEntity> entityList = myMapper.searchCompanyList(startIndex, pageSize, technologyID);
            for (TechnologyUsingEntity entity : entityList) {
                TechnologyUsingVO model = new TechnologyUsingVO();
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
    public UnifiedResponse add(TechnologyUsingDTO dto) {
        try {
            List<TechnologyUsingEntity> entityList = JsonUtils.deserializationToObject(dto.getJsonData(), TechnologyUsingEntity.class);
            int affectRow = myMapper.delete(dto.getCompanyID());
            for (TechnologyUsingEntity entity : entityList) {
                entity.setCompanyID(dto.getCompanyID());
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
}
