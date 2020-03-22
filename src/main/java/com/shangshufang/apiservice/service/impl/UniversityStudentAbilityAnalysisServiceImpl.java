package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.StudentCodeStandardAnalysisEntity;
import com.shangshufang.apiservice.entity.UniversityStudentAbilityAnalysisEntity;
import com.shangshufang.apiservice.entity.UniversityStudentAbilityEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityStudentAbilityAnalysisMapper;
import com.shangshufang.apiservice.service.UniversityStudentAbilityAnalysisService;
import com.shangshufang.apiservice.vo.StudentCodeStandardAnalysisVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityStudentAbilityAnalysisVO;
import com.shangshufang.apiservice.vo.UniversityStudentAbilityVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UniversityStudentAbilityAnalysisServiceImpl implements UniversityStudentAbilityAnalysisService {
    @Autowired
    private UniversityStudentAbilityAnalysisMapper myMapper;
    private Logger logger = LogManager.getLogger(UniversityStudentAbilityAnalysisServiceImpl.class);

    @Override
    public UnifiedResponse ability() {
        int affectCount = 0;
        int pageNumber = 1;
        final int PAGE_SIZE = 100;

        Map<String, Object> param = new HashMap<>();

        while (true) {
            try {
                int startIndex = (pageNumber - 1) * PAGE_SIZE;
                param.put("f_start_index", startIndex);
                param.put("f_page_size", PAGE_SIZE);
                List<UniversityStudentAbilityAnalysisEntity> entityList = myMapper.searchAnalysisList(param);
                if (entityList.isEmpty()) {
                    break;
                }
                for (UniversityStudentAbilityAnalysisEntity entity : entityList) {
                    int totalCount = myMapper.searchAbilityAnalysisTotalCount(entity.getStudentUniversityCode(), entity.getStudentSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    if (totalCount > 0) {
                        affectCount += myMapper.update(entity);
                    } else {
                        affectCount += myMapper.insert(entity);
                    }
                }
                pageNumber++;
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return UnifiedResponseManager.buildSubmitSuccessResponse(affectCount);
    }

    @Override
    public UnifiedResponse findList(int pageNumber,
                                    int pageSize,
                                    int technologyID,
                                    int studentUniversityCode,
                                    int studentSchoolID,
                                    int teacherUniversityCode,
                                    int teacherSchoolID,
                                    int teacherID,
                                    String studentCellphone) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentAbilityAnalysisVO> modelList = new ArrayList<>();
            studentCellphone = studentCellphone.equals(ParameterConstant.NO_PARAMETER) ? null : studentCellphone;
            int totalCount = myMapper.searchTotalCount(technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentCellphone);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentAbilityAnalysisEntity> entityList =  myMapper.searchStudentAbilityAnalysisList(startIndex, pageSize, technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentCellphone);
            for (UniversityStudentAbilityAnalysisEntity entity : entityList) {
                UniversityStudentAbilityAnalysisVO model = new UniversityStudentAbilityAnalysisVO();
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
    public UnifiedResponse findStudentAbilityInfo(int universityCode, int schoolID, int studentID) {
        try {
            UniversityStudentAbilityVO model = new UniversityStudentAbilityVO();
            UniversityStudentAbilityEntity entity =  myMapper.searchStudentAbilityInfo(universityCode, schoolID, studentID);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findStudentTechnologyAbility(int universityCode, int schoolID, int studentID, int technologyID) {
        try {
            UniversityStudentAbilityAnalysisVO model = new UniversityStudentAbilityAnalysisVO();
            UniversityStudentAbilityAnalysisEntity entity =  myMapper.searchStudentTechnologyAbility(universityCode, schoolID, studentID, technologyID);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findCodeStandardAnalysis(int universityCode, int schoolID, int studentID, int technologyID) {
        try {
            List<StudentCodeStandardAnalysisVO> modelList = new ArrayList<>();
            List<StudentCodeStandardAnalysisEntity> entityList =  myMapper.searchCodeStandardAnalysis(universityCode, schoolID, studentID, technologyID);
            if(entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (StudentCodeStandardAnalysisEntity entity : entityList) {
                StudentCodeStandardAnalysisVO model = new StudentCodeStandardAnalysisVO();
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
