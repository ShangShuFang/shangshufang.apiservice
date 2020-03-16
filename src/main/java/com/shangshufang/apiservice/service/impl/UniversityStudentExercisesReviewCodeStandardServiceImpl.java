package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityStudentExercisesReviewCodeStandardDTO;
import com.shangshufang.apiservice.entity.UniversityStudentExercisesReviewCodeStandardEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityStudentExercisesReviewCodeStandardMapper;
import com.shangshufang.apiservice.service.UniversityStudentExercisesReviewCodeStandardService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityStudentExercisesReviewCodeStandardVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityStudentExercisesReviewCodeStandardServiceImpl implements UniversityStudentExercisesReviewCodeStandardService {
    @Autowired
    private UniversityStudentExercisesReviewCodeStandardMapper myMapper;
    private Logger logger = LogManager.getLogger(UniversityStudentExercisesReviewCodeStandardServiceImpl.class);

    @Override
    public UnifiedResponse findList(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID, int reviewID) {
        try {
            List<UniversityStudentExercisesReviewCodeStandardVO> modelList = new ArrayList<>();
            List<UniversityStudentExercisesReviewCodeStandardEntity> entityList =  myMapper.searchList(reviewID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (UniversityStudentExercisesReviewCodeStandardEntity entity : entityList) {
                UniversityStudentExercisesReviewCodeStandardVO model = new UniversityStudentExercisesReviewCodeStandardVO();
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
    public UnifiedResponse add(UniversityStudentExercisesReviewCodeStandardDTO dto) {
        try {
            UniversityStudentExercisesReviewCodeStandardEntity entity = new UniversityStudentExercisesReviewCodeStandardEntity();
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
    public UnifiedResponse change(UniversityStudentExercisesReviewCodeStandardDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(UniversityStudentExercisesReviewCodeStandardDTO dto) {
        return null;
    }
}
