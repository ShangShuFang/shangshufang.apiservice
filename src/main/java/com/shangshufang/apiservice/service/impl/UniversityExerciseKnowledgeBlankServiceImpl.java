package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityExerciseKnowledgeBlankDTO;
import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeBlankEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityExerciseKnowledgeBlankMapper;
import com.shangshufang.apiservice.service.UniversityExerciseKnowledgeBlankService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityExerciseKnowledgeBlankVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityExerciseKnowledgeBlankServiceImpl implements UniversityExerciseKnowledgeBlankService {
    @Autowired
    private UniversityExerciseKnowledgeBlankMapper myMapper;
    private final Logger logger = LogManager.getLogger(UniversityExerciseKnowledgeBlankServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int knowledgeID, int teacherID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityExerciseKnowledgeBlankVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID, knowledgeID, teacherID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityExerciseKnowledgeBlankEntity> entityList =  myMapper.searchList(technologyID, knowledgeID, teacherID, startIndex, pageSize);
            for (UniversityExerciseKnowledgeBlankEntity entity : entityList) {
                UniversityExerciseKnowledgeBlankVO model = new UniversityExerciseKnowledgeBlankVO();
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
    public UnifiedResponse delete(int technologyID, int knowledgeID, int exercisesID, int teacherID) {
        try {
            int affectRow = myMapper.delete(technologyID, knowledgeID, exercisesID, teacherID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(UniversityExerciseKnowledgeBlankDTO dto) {
        try {
            UniversityExerciseKnowledgeBlankEntity entity = new UniversityExerciseKnowledgeBlankEntity();
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
    public UnifiedResponse change(UniversityExerciseKnowledgeBlankDTO dto) {
        try {
            UniversityExerciseKnowledgeBlankEntity entity = new UniversityExerciseKnowledgeBlankEntity();
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
    public UnifiedResponse changeDataStatus(UniversityExerciseKnowledgeBlankDTO dto) {
        return null;
    }
}
