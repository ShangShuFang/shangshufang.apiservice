package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.StudentExerciseAnalysisEntity;
import com.shangshufang.apiservice.entity.StudentExercisePercentAnalysisEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.StudentTechnologyDetailAnalysisMapper;
import com.shangshufang.apiservice.service.StudentTechnologyDetailAnalysisService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentTechnologyDetailAnalysisServiceImpl implements StudentTechnologyDetailAnalysisService {
    @Autowired
    private StudentTechnologyDetailAnalysisMapper myMapper;
    private Logger logger = LogManager.getLogger(StudentTechnologyDetailAnalysisServiceImpl.class);

    @Override
    public UnifiedResponse findKnowledgeAnalysisInfo(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID) {
        try {
            DecimalFormat df = new DecimalFormat("#.00");
            StudentKnowledgeAnalysisVO model = new StudentKnowledgeAnalysisVO();
            Map<String, Object> param = new HashMap<>();
            param.put("f_student_university_code", studentUniversityCode);
            param.put("f_student_school_id", studentSchoolID);
            param.put("f_student_id", studentID);
            param.put("f_technology_id", technologyID);

            myMapper.searchKnowledgeAnalysisInfo(param);

            int technologyKnowledgeCount = (int) param.get("f_technology_knowledge_count");
            int noLearningKnowledgeCount = (int) param.get("f_no_learning_knowledge_count");
            int weaknessKnowledgeCount = (int) param.get("f_weakness_knowledge_count");
            int graspKnowledgeCount = (int) param.get("f_grasp_knowledge_count");

            //float learningPercentCount =(float)Math.round(((float) (weaknessKnowledgeCount + graspKnowledgeCount) / technologyKnowledgeCount) * 100);

            float learningPercentCount =Float.parseFloat(df.format(( (float)(weaknessKnowledgeCount + graspKnowledgeCount) / technologyKnowledgeCount) * 100));
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMaximumFractionDigits(2);
            model.setTechnologyKnowledgeCount(technologyKnowledgeCount);
            model.setNoLearningKnowledgeCount(noLearningKnowledgeCount);
            model.setWeaknessKnowledgeCount(weaknessKnowledgeCount);
            model.setGraspKnowledgeCount(graspKnowledgeCount);
            model.setLearningPercentCount(Float.parseFloat(numberFormat.format(learningPercentCount)));

            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findExerciseAnalysisResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID) {
        try {
            List<StudentExerciseAnalysisVO> modelList = new ArrayList<>();
            List<StudentExerciseAnalysisEntity> entityList =  myMapper.searchExerciseAnalysisResult(studentUniversityCode, studentSchoolID, studentID, technologyID);
            if(entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (StudentExerciseAnalysisEntity entity : entityList) {
                StudentExerciseAnalysisVO model = new StudentExerciseAnalysisVO();
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
    public UnifiedResponse findExercisePercentAnalysisResult(int studentUniversityCode, int studentSchoolID, int studentID, int technologyID) {
        try {
            List<StudentExercisePercentAnalysisVO> modelList = new ArrayList<>();
            List<StudentExercisePercentAnalysisEntity> entityList =  myMapper.searchExercisePercentAnalysisResult(studentUniversityCode, studentSchoolID, studentID, technologyID);
            if(entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (StudentExercisePercentAnalysisEntity entity : entityList) {
                StudentExercisePercentAnalysisVO model = new StudentExercisePercentAnalysisVO();
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
