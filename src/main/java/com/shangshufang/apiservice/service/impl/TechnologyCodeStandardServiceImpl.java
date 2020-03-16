package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyCodeStandardDTO;
import com.shangshufang.apiservice.entity.TechnologyCodeStandardEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyCodeStandardMapper;
import com.shangshufang.apiservice.service.TechnologyCodeStandardService;
import com.shangshufang.apiservice.vo.TechnologyCodeStandardVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyCodeStandardServiceImpl implements TechnologyCodeStandardService {
    @Autowired
    private TechnologyCodeStandardMapper myMapper;
    private Logger logger = LogManager.getLogger(TechnologyCodeStandardServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyCodeStandardVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyCodeStandardEntity> entityList =  myMapper.searchList(startIndex, pageSize, technologyID);
            for (TechnologyCodeStandardEntity entity : entityList) {
                TechnologyCodeStandardVO model = new TechnologyCodeStandardVO();
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
    public UnifiedResponse checkCodeStandardExist(int technologyID, String codeStandardName) {
        try {
            int count =  myMapper.checkCodeStandardExist(technologyID, codeStandardName);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int technologyID, int codeStandardID) {
        try {
            int affectRow = myMapper.delete(technologyID, codeStandardID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(TechnologyCodeStandardDTO dto) {
        try {
            TechnologyCodeStandardEntity entity = new TechnologyCodeStandardEntity();
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
    public UnifiedResponse change(TechnologyCodeStandardDTO dto) {
        try {
            TechnologyCodeStandardEntity entity = new TechnologyCodeStandardEntity();
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
    public UnifiedResponse changeDataStatus(TechnologyCodeStandardDTO dto) {
        return null;
    }
}
