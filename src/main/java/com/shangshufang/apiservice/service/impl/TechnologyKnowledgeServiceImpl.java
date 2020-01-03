package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyKnowledgeDTO;
import com.shangshufang.apiservice.entity.TechnologyKnowledgeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyKnowledgeMapper;
import com.shangshufang.apiservice.service.TechnologyKnowledgeService;
import com.shangshufang.apiservice.vo.TechnologyKnowledgeVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyKnowledgeServiceImpl implements TechnologyKnowledgeService {
    @Autowired
    private TechnologyKnowledgeMapper myMapper;
    private Logger logger = LogManager.getLogger(TechnologyKnowledgeServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int learningPhaseID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyKnowledgeVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID, learningPhaseID, dataStatus.equals("N") ? null : dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyKnowledgeEntity> entityList =  myMapper.searchList(startIndex, pageSize, technologyID, learningPhaseID, dataStatus.equals("N") ? null : dataStatus);
            for (TechnologyKnowledgeEntity entity : entityList) {
                TechnologyKnowledgeVO model = new TechnologyKnowledgeVO();
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
    public UnifiedResponse find(int technologyID) {
        try {
            List<TechnologyKnowledgeVO> modelList = new ArrayList<>();
            List<TechnologyKnowledgeEntity> entityList =  myMapper.search(technologyID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            for (TechnologyKnowledgeEntity entity : entityList) {
                TechnologyKnowledgeVO model = new TechnologyKnowledgeVO();
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
    public UnifiedResponse checkKnowledgeNameExist(int technologyID, String knowledgeName) {
        try {
            int count =  myMapper.checkKnowledgeNameExist(technologyID, knowledgeName);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int technologyID, int learningPhaseID, int knowledgeID) {
        try {
            int affectRow = myMapper.delete(technologyID, learningPhaseID, knowledgeID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(TechnologyKnowledgeDTO dto) {
        try {
            TechnologyKnowledgeEntity entity = new TechnologyKnowledgeEntity();
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
    public UnifiedResponse change(TechnologyKnowledgeDTO dto) {
        try {
            TechnologyKnowledgeEntity entity = new TechnologyKnowledgeEntity();
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
    public UnifiedResponse changeDataStatus(TechnologyKnowledgeDTO dto) {
        try {
            TechnologyKnowledgeEntity entity = new TechnologyKnowledgeEntity();
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
