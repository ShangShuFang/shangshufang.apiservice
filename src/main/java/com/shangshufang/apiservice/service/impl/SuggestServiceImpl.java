package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.SuggestDTO;
import com.shangshufang.apiservice.entity.SuggestEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.SuggestMapper;
import com.shangshufang.apiservice.service.SuggestService;
import com.shangshufang.apiservice.vo.SuggestVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestServiceImpl implements SuggestService {
    @Autowired
    private SuggestMapper myMapper;
    private Logger logger = LogManager.getLogger(SuggestServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String portal, String cellphone, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<SuggestVO> modelList = new ArrayList<>();
            cellphone = cellphone.equals(ParameterConstant.NO_PARAMETER) ? null : cellphone;
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(portal, cellphone, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<SuggestEntity> entityList =  myMapper.searchList(startIndex, pageSize, portal, cellphone, dataStatus);
            for (SuggestEntity entity : entityList) {
                SuggestVO model = new SuggestVO();
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
    public UnifiedResponse add(SuggestDTO dto) {
        try {
            SuggestEntity entity = new SuggestEntity();
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
    public UnifiedResponse change(SuggestDTO dto) {
        try {
            SuggestEntity entity = new SuggestEntity();
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
    public UnifiedResponse changeDataStatus(SuggestDTO dto) {
        return null;
    }
}
