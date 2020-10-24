package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityExerciseKnowledgeProgramDTO;
import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeProgramEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityExerciseKnowledgeProgramMapper;
import com.shangshufang.apiservice.service.UniversityExerciseKnowledgeProgramService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityExerciseKnowledgeProgramVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityExerciseKnowledgeProgramServiceImpl implements UniversityExerciseKnowledgeProgramService {
    @Autowired
    private UniversityExerciseKnowledgeProgramMapper myMapper;
    private final Logger logger = LogManager.getLogger(UniversityExerciseKnowledgeProgramServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int knowledgeID, int teacherID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityExerciseKnowledgeProgramVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID, knowledgeID, teacherID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityExerciseKnowledgeProgramEntity> entityList =  myMapper.searchList(technologyID, knowledgeID, teacherID, startIndex, pageSize);
            for (UniversityExerciseKnowledgeProgramEntity entity : entityList) {
                UniversityExerciseKnowledgeProgramVO model = new UniversityExerciseKnowledgeProgramVO();
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
    public UnifiedResponse add(UniversityExerciseKnowledgeProgramDTO dto) {
        try {
            UniversityExerciseKnowledgeProgramEntity entity = new UniversityExerciseKnowledgeProgramEntity();
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
    public UnifiedResponse change(UniversityExerciseKnowledgeProgramDTO dto) {
        try {
            UniversityExerciseKnowledgeProgramEntity entity = new UniversityExerciseKnowledgeProgramEntity();
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
    public UnifiedResponse changeDataStatus(UniversityExerciseKnowledgeProgramDTO dto) {
        return null;
    }
}
