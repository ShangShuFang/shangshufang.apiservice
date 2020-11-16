package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CodeStandardDTO;
import com.shangshufang.apiservice.entity.CodeStandardEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CodeStandardMapper;
import com.shangshufang.apiservice.service.CodeStandardService;
import com.shangshufang.apiservice.vo.CodeStandardVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeStandardServiceImpl implements CodeStandardService {
    @Autowired
    private CodeStandardMapper myMapper;

    private Logger logger = LogManager.getLogger(CodeStandardServiceImpl.class);
    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int languageID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CodeStandardVO> modelList = new ArrayList<>();

            int totalCount = myMapper.searchTotalCount(languageID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CodeStandardEntity> entityList =  myMapper.searchList(startIndex, pageSize, languageID);
            for (CodeStandardEntity entity : entityList) {
                CodeStandardVO model = new CodeStandardVO();
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
    public UnifiedResponse findList(int technologyID) {
        try {
            List<CodeStandardVO> modelList = new ArrayList<>();
            List<CodeStandardEntity> entityList =  myMapper.searchListByTechnology(technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (CodeStandardEntity entity : entityList) {
                CodeStandardVO model = new CodeStandardVO();
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
    public UnifiedResponse checkNameExist(int languageID, String codeStandardName) {
        try {
            int count =  myMapper.checkNameExist(languageID, codeStandardName);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int languageID, int codeStandardID) {
        try {
            int affectRow = myMapper.delete(languageID, codeStandardID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(CodeStandardDTO dto) {
        try {
            CodeStandardEntity entity = new CodeStandardEntity();
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
    public UnifiedResponse change(CodeStandardDTO dto) {
        try {
            CodeStandardEntity entity = new CodeStandardEntity();
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
    public UnifiedResponse changeDataStatus(CodeStandardDTO dto) {
        return null;
    }
}
