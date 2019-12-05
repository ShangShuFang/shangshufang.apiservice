package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.dto.TechnologyKnowledgeUsingDTO;
import com.shangshufang.apiservice.entity.TechnologyKnowledgeUsingEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyKnowledgeUsingMapper;
import com.shangshufang.apiservice.service.TechnologyKnowledgeUsingService;
import com.shangshufang.apiservice.vo.TechnologyKnowledgeUsingVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyKnowledgeUsingServiceImpl implements TechnologyKnowledgeUsingService {
    @Autowired
    private TechnologyKnowledgeUsingMapper myMapper;
    private Logger logger = LogManager.getLogger(TechnologyKnowledgeUsingServiceImpl.class);

    @Override
    public UnifiedResponse findList(int companyID) {
        try {
            List<TechnologyKnowledgeUsingVO> modelList = new ArrayList<>();

            List<TechnologyKnowledgeUsingEntity> entityList =  myMapper.searchList(companyID);
            for (TechnologyKnowledgeUsingEntity entity : entityList) {
                TechnologyKnowledgeUsingVO model = new TechnologyKnowledgeUsingVO();
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
    public UnifiedResponse findTechnologyList(int companyID) {
        try {
            List<TechnologyKnowledgeUsingVO> modelList = new ArrayList<>();

            List<TechnologyKnowledgeUsingEntity> entityList =  myMapper.searchTechnologyList(companyID);
            for (TechnologyKnowledgeUsingEntity entity : entityList) {
                TechnologyKnowledgeUsingVO model = new TechnologyKnowledgeUsingVO();
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
    public UnifiedResponse findLearningPhaseList(int companyID, int technologyID) {
        try {
            List<TechnologyKnowledgeUsingVO> modelList = new ArrayList<>();

            List<TechnologyKnowledgeUsingEntity> entityList =  myMapper.searchLearningPhaseList(companyID, technologyID);
            for (TechnologyKnowledgeUsingEntity entity : entityList) {
                TechnologyKnowledgeUsingVO model = new TechnologyKnowledgeUsingVO();
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
    public UnifiedResponse findKnowledgeList(int companyID, int technologyID, int learningPhaseID) {
        try {
            List<TechnologyKnowledgeUsingVO> modelList = new ArrayList<>();

            List<TechnologyKnowledgeUsingEntity> entityList =  myMapper.searchKnowledgeList(companyID, technologyID, learningPhaseID);
            for (TechnologyKnowledgeUsingEntity entity : entityList) {
                TechnologyKnowledgeUsingVO model = new TechnologyKnowledgeUsingVO();
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
    public UnifiedResponse add(TechnologyKnowledgeUsingDTO dto) {
        try {
            String[] usingKnowledgeArray = dto.getKnowledgeIdList().split(",");
            int affectRow = myMapper.delete(dto.getCompanyID(), dto.getTechnologyID(), dto.getLearningPhaseID());
            if(dto.getKnowledgeIdList().isEmpty()){
                return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
            }
            for (String usingKnowledge : usingKnowledgeArray) {
                TechnologyKnowledgeUsingEntity entity = new TechnologyKnowledgeUsingEntity();
                entity.setCompanyID(dto.getCompanyID());
                entity.setTechnologyID(dto.getTechnologyID());
                entity.setLearningPhaseID(dto.getLearningPhaseID());
                entity.setKnowledgeID(Integer.parseInt(usingKnowledge));
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
