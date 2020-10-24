package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityExerciseKnowledgeChoiceDTO;
import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeChoiceEntity;
import com.shangshufang.apiservice.entity.UniversityExerciseKnowledgeChoiceOptionEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityExerciseKnowledgeChoiceMapper;
import com.shangshufang.apiservice.mapper.UniversityExerciseKnowledgeChoiceOptionMapper;
import com.shangshufang.apiservice.service.UniversityExerciseKnowledgeChoiceService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityExerciseKnowledgeChoiceOptionVO;
import com.shangshufang.apiservice.vo.UniversityExerciseKnowledgeChoiceVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityExerciseKnowledgeChoiceServiceImpl implements UniversityExerciseKnowledgeChoiceService {
    @Autowired
    private UniversityExerciseKnowledgeChoiceMapper myMapper;
    @Autowired
    private UniversityExerciseKnowledgeChoiceOptionMapper optionMapper;
    private final Logger logger = LogManager.getLogger(UniversityExerciseKnowledgeChoiceServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int knowledgeID, int teacherID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityExerciseKnowledgeChoiceVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(technologyID, knowledgeID, teacherID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityExerciseKnowledgeChoiceEntity> entityList =  myMapper.searchList(technologyID, knowledgeID, teacherID, startIndex, pageSize);
            for (UniversityExerciseKnowledgeChoiceEntity entity : entityList) {
                List<UniversityExerciseKnowledgeChoiceOptionVO> optionList = new ArrayList<>();

                List<UniversityExerciseKnowledgeChoiceOptionEntity> optionEntityList = optionMapper.searchList(technologyID, knowledgeID, entity.getExercisesID());
                if (!optionEntityList.isEmpty()) {
                    for (UniversityExerciseKnowledgeChoiceOptionEntity optionEntity : optionEntityList) {
                        UniversityExerciseKnowledgeChoiceOptionVO optionVO = new UniversityExerciseKnowledgeChoiceOptionVO();
                        ObjectConvertUtils.toBean(optionEntity, optionVO);
                        optionList.add(optionVO);
                    }
                }
                UniversityExerciseKnowledgeChoiceVO model = new UniversityExerciseKnowledgeChoiceVO();
                ObjectConvertUtils.toBean(entity, model);
                model.setChoiceOptions(optionList);
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
            int affectRow = 0;
            affectRow += myMapper.delete(technologyID, knowledgeID, exercisesID, teacherID);
            affectRow += optionMapper.delete(technologyID, knowledgeID, exercisesID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(UniversityExerciseKnowledgeChoiceDTO dto) {
        try {
            int affectRow = 0;
            List<UniversityExerciseKnowledgeChoiceOptionEntity> optionEntityList = JsonUtils.deserializationToObject(dto.getChoiceOptionsJson(), UniversityExerciseKnowledgeChoiceOptionEntity.class);
            UniversityExerciseKnowledgeChoiceEntity entity = new UniversityExerciseKnowledgeChoiceEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            affectRow += myMapper.insert(entity);
            if (optionEntityList != null) {
                affectRow += optionMapper.delete(dto.getTechnologyID(), dto.getKnowledgeID(), entity.getExercisesID());
                for (UniversityExerciseKnowledgeChoiceOptionEntity optionEntity : optionEntityList) {
                    optionEntity.setTechnologyID(dto.getTechnologyID());
                    optionEntity.setKnowledgeID(dto.getKnowledgeID());
                    optionEntity.setExercisesID(entity.getExercisesID());
                    optionEntity.setCreateUser(dto.getLoginUser());
                    optionEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += optionMapper.insert(optionEntity);
                }
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow, entity.getExercisesID());
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(UniversityExerciseKnowledgeChoiceDTO dto) {
        try {
            int affectRow = 0;
            List<UniversityExerciseKnowledgeChoiceOptionEntity> optionEntityList = JsonUtils.deserializationToObject(dto.getChoiceOptionsJson(), UniversityExerciseKnowledgeChoiceOptionEntity.class);
            UniversityExerciseKnowledgeChoiceEntity entity = new UniversityExerciseKnowledgeChoiceEntity();

            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            affectRow += myMapper.update(entity);

            if (optionEntityList != null) {
                affectRow += optionMapper.delete(dto.getTechnologyID(), dto.getKnowledgeID(), entity.getExercisesID());
                for (UniversityExerciseKnowledgeChoiceOptionEntity optionEntity : optionEntityList) {
                    optionEntity.setTechnologyID(dto.getTechnologyID());
                    optionEntity.setKnowledgeID(dto.getKnowledgeID());
                    optionEntity.setExercisesID(dto.getExercisesID());
                    optionEntity.setCreateUser(dto.getLoginUser());
                    optionEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += optionMapper.insert(optionEntity);
                }
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(UniversityExerciseKnowledgeChoiceDTO dto) {
        return null;
    }
}
