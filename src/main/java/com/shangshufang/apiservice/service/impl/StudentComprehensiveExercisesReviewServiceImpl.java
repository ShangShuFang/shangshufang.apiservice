package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.StudentComprehensiveExercisesReviewDTO;
import com.shangshufang.apiservice.entity.StudentComprehensiveExercisesReviewEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.StudentComprehensiveExercisesReviewMapper;
import com.shangshufang.apiservice.service.StudentComprehensiveExercisesReviewService;
import com.shangshufang.apiservice.vo.StudentComprehensiveExercisesReviewVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentComprehensiveExercisesReviewServiceImpl implements StudentComprehensiveExercisesReviewService {
    @Autowired
    private StudentComprehensiveExercisesReviewMapper myMapper;
    private final Logger logger = LogManager.getLogger(StudentComprehensiveExercisesReviewServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int studentID, int exercisesID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentComprehensiveExercisesReviewVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(studentID, exercisesID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentComprehensiveExercisesReviewEntity> entityList =  myMapper.searchList(startIndex, pageSize, studentID, exercisesID);
            for (StudentComprehensiveExercisesReviewEntity entity : entityList) {
                StudentComprehensiveExercisesReviewVO model = new StudentComprehensiveExercisesReviewVO();
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
    public UnifiedResponse add(StudentComprehensiveExercisesReviewDTO dto) {
        try {
            StudentComprehensiveExercisesReviewEntity entity = new StudentComprehensiveExercisesReviewEntity();
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
}
