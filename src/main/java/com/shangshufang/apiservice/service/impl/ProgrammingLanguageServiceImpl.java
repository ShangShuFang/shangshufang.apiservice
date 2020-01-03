package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.ProgrammingLanguageEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ProgrammingLanguageMapper;
import com.shangshufang.apiservice.service.ProgrammingLanguageService;
import com.shangshufang.apiservice.vo.ProgrammingLanguageVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {
    @Autowired
    private ProgrammingLanguageMapper myMapper;
    private Logger logger = LogManager.getLogger(ProgrammingLanguageServiceImpl.class);

    @Override
    public UnifiedResponse findList() {
        try {
            List<ProgrammingLanguageVO> modelList = new ArrayList<>();
            List<ProgrammingLanguageEntity> entityList =  myMapper.searchList();
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ProgrammingLanguageEntity entity : entityList) {
                ProgrammingLanguageVO model = new ProgrammingLanguageVO();
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
