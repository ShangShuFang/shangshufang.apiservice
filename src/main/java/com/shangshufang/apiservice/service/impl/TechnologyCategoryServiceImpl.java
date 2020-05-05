package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyCategoryDTO;
import com.shangshufang.apiservice.entity.TechnologyCategoryEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyCategoryMapper;
import com.shangshufang.apiservice.service.TechnologyCategoryService;
import com.shangshufang.apiservice.vo.TechnologyCategoryVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.common.ParameterUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyCategoryServiceImpl implements TechnologyCategoryService {
    @Autowired
    private TechnologyCategoryMapper myMapper;

    private final Logger logger = LogManager.getLogger(TechnologyCategoryServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int directionID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyCategoryVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(directionID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyCategoryEntity> entityList =  myMapper.searchList(startIndex, pageSize, directionID, dataStatus);
            for (TechnologyCategoryEntity entity : entityList) {
                TechnologyCategoryVO model = new TechnologyCategoryVO();
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
    public UnifiedResponse checkNameExist(int directionID, String categoryName) {
        try {
            categoryName = ParameterUtils.convertSpecialChar(categoryName);
            int count =  myMapper.checkNameExist(directionID, categoryName);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int directionID, int technologyCategoryID) {
        try {
            int affectRow = myMapper.delete(directionID, technologyCategoryID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(TechnologyCategoryDTO dto) {
        try {
            TechnologyCategoryEntity entity = new TechnologyCategoryEntity();
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

    @Override
    public UnifiedResponse change(TechnologyCategoryDTO dto) {
        try {
            TechnologyCategoryEntity entity = new TechnologyCategoryEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(TechnologyCategoryDTO dto) {
        try {
            TechnologyCategoryEntity entity = new TechnologyCategoryEntity();
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
