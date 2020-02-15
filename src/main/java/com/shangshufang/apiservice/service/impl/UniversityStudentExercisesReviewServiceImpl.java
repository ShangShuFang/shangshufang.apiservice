package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ExercisesReviewResultConstant;
import com.shangshufang.apiservice.constant.ExercisesStatusConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityStudentExercisesReviewDTO;
import com.shangshufang.apiservice.entity.UniversityStudentExercisesEntity;
import com.shangshufang.apiservice.entity.UniversityStudentExercisesReviewEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityStudentExercisesMapper;
import com.shangshufang.apiservice.mapper.UniversityStudentExercisesReviewMapper;
import com.shangshufang.apiservice.service.UniversityStudentExercisesReviewService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityStudentExercisesReviewVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityStudentExercisesReviewServiceImpl implements UniversityStudentExercisesReviewService {
    @Autowired
    private UniversityStudentExercisesReviewMapper myMapper;
    @Autowired
    private UniversityStudentExercisesMapper universityStudentExercisesMapper;

    private Logger logger = LogManager.getLogger(UniversityStudentExercisesReviewServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int studentExercisesID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentExercisesReviewVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(studentExercisesID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesReviewEntity> entityList =  myMapper.searchList(startIndex, pageSize, studentExercisesID);
            for (UniversityStudentExercisesReviewEntity entity : entityList) {
                UniversityStudentExercisesReviewVO model = new UniversityStudentExercisesReviewVO();
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
    public UnifiedResponse add(UniversityStudentExercisesReviewDTO dto) {
        try {
            int affectRow = 0;
            UniversityStudentExercisesEntity exercisesEntity = new UniversityStudentExercisesEntity();
            UniversityStudentExercisesReviewEntity entity = new UniversityStudentExercisesReviewEntity();

            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            affectRow += myMapper.insert(entity);

            ObjectConvertUtils.toBean(dto, exercisesEntity);
            if(dto.getReviewResult().equals(ExercisesReviewResultConstant.PASS)){
                exercisesEntity.setDataStatus(ExercisesStatusConstant.Pass);
            }else {
                exercisesEntity.setDataStatus(ExercisesStatusConstant.Reviewed);
            }

            exercisesEntity.setUpdateUser(dto.getLoginUser());
            affectRow += universityStudentExercisesMapper.updateDataStatus(exercisesEntity);

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(UniversityStudentExercisesReviewDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(UniversityStudentExercisesReviewDTO dto) {
        try {
            UniversityStudentExercisesReviewEntity entity = new UniversityStudentExercisesReviewEntity();
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
