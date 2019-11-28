package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
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
    private Logger logger = LogManager.getLogger(TechnologyUsingServiceImpl.class);

    @Override
    public UnifiedResponse findUsingTechnologyList(int companyID) {
        try {
            List<TechnologyUsingVO> modelList = new ArrayList<>();

            List<TechnologyUsingEntity> entityList =  myMapper.searchUsingTechnologyList(companyID);
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
    public UnifiedResponse add(TechnologyUsingDTO dto) {
        try {
            String[] usingTechnologies = dto.getTechnologyIdList().split(",");
            int affectRow = myMapper.delete(dto.getCompanyID());
            for (String usingTechnology : usingTechnologies) {
                TechnologyUsingEntity entity = new TechnologyUsingEntity();
                entity.setCompanyID(dto.getCompanyID());
                entity.setTechnologyID(Integer.parseInt(usingTechnology));
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
