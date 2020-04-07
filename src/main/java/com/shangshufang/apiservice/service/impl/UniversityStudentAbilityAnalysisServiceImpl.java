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

import java.text.DecimalFormat;
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
    public UnifiedResponse abilityAnalysis() {
        int affectCount = 0;
        int pageNumber = 1;
        final int PAGE_SIZE = 10;

        //region 取得待处理的基础数据并逐一计算学习完成度、完成练习总数、一次性编译通过率、一次性运行正确率
        while (true) {
            int startIndex = (pageNumber - 1) * PAGE_SIZE;
            List<UniversityStudentAbilityAnalysisEntity> entityList;
            try {
                // 分批次取得待处理的基础数据
                entityList = myMapper.searchDataSource4Base(startIndex, PAGE_SIZE);
                if (entityList.isEmpty()) {
                    break;
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
                break;
            }

            // 依次取得每一笔数据对应的分析结果
            int technologyID = 0;
            int knowledgeTotalCount = 0;
            for (UniversityStudentAbilityAnalysisEntity entity : entityList) {
                try {
                    //region step1: 取得当前技术的知识点总数量
                    if (technologyID != entity.getTechnologyID()) {
                        technologyID = entity.getTechnologyID();
                        knowledgeTotalCount = myMapper.searchDataSource4KnowledgeCount(technologyID);
                    }
                    entity.setKnowledgeTotalCount(knowledgeTotalCount);
                    //endregion

                    //region step2: 取得当前学生已掌握的知识点数量及百分比
                    int finishKnowledgeCount = myMapper.searchDataSource4FinishKnowledgeCount(entity.getStudentUniversityCode(), entity.getStudentSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setFinishKnowledgeCount(finishKnowledgeCount);
                    //endregion

                    //region step3: 取得当前学生已完成的习题数量
                    int finishExercisesCount = myMapper.searchDataSource4FinishExercisesCount(entity.getStudentUniversityCode(), entity.getStudentSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setFinishExercisesCount(finishExercisesCount);
                    //endregion

                    //region step4: 取得当前学生一次性编译通过率
                    float onceCompilationSuccessRate = myMapper.searchDataSource4OnceCompilationSuccessRate(entity.getStudentUniversityCode(), entity.getStudentSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setOnceCompilationSuccessRate(onceCompilationSuccessRate);
                    //endregion

                    //region step5: 取得当前学生一次性运行成功率
                    float onceRunSuccessRate = myMapper.searchDataSource4OnceRunSuccessRate(entity.getStudentUniversityCode(), entity.getStudentSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setOnceRunSuccessRate(onceRunSuccessRate);
                    //endregion

                    //region step6: 取得当前学生已完成的综合练习数量
                    int finishUnionExercisesCount = 0;
                    entity.setFinishUnionExercisesCount(finishUnionExercisesCount);
                    //endregion

                    //region step7: 取得当前学生参与的项目数量
                    int finishProjectCount = 0;
                    entity.setFinishProjectCount(finishProjectCount);
                    //endregion

                    //region step8: 根据当前学生的数据，计算其对应技术的能力级别
                    DecimalFormat df = new DecimalFormat("0.00");
                    float finishKnowledgeRate = Float.parseFloat(df.format((float) finishKnowledgeCount / knowledgeTotalCount));
                    String abilityLevel = getTechnologyLevel(finishKnowledgeRate, finishUnionExercisesCount, finishProjectCount);
                    entity.setFinishKnowledgePercent(finishKnowledgeRate);
                    entity.setAbilityLevel(abilityLevel);
                    //endregion

                    //region step9: 根据当前学生的数据，计算能力标准分
                    double standardScore = calculateStandardScore(finishKnowledgeCount, onceCompilationSuccessRate, onceRunSuccessRate, finishUnionExercisesCount, finishProjectCount);
                    entity.setStandardScore(standardScore);
                    //endregion

                    //region step10: 将已取得的分析结果保存到数据库
                    int totalCount = myMapper.searchAbilityAnalysisTotalCount(entity.getStudentUniversityCode(), entity.getStudentSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    if (totalCount > 0) {
                        affectCount += myMapper.update(entity);
                    } else {
                        affectCount += myMapper.insert(entity);
                    }
                    //endregion
                } catch (Exception ex) {
                    logger.error(ex.toString());
                }
            }
            pageNumber++;
        }
        //endregion

        if (affectCount == 0) {
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectCount);
        }

        //region 依次计算每个学生每个技术的综合能力分值及计算名次，并再次更新到数据库中
        pageNumber = 1;
        while (true) {
            List<UniversityStudentAbilityAnalysisEntity> entityList;
            int startIndex = (pageNumber - 1) * PAGE_SIZE;
            try {
                // 分批次取得待处理的基础数据
                entityList = myMapper.searchDataSource4Analysis(startIndex, PAGE_SIZE);
                if (entityList.isEmpty()) {
                    break;
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
                break;
            }

            int studentTotalCount = 0;
            int technologyID = 0;
            for (UniversityStudentAbilityAnalysisEntity entity : entityList) {
                try {
                    //region step1: 取得学习当前技术的学生总数
                    if (technologyID != entity.getTechnologyID()) {
                        technologyID = entity.getTechnologyID();
                        studentTotalCount = myMapper.searchStudentTotalCountWithTechnology(technologyID);
                    }
                    int lowerTotalCount = myMapper.searchStudentPositionNumberWithTechnology(technologyID, entity.getStandardScore());
                    DecimalFormat df = new DecimalFormat("0.00");
                    float positionSite = Float.parseFloat(df.format((float) lowerTotalCount / studentTotalCount));
                    //endregion

                    //region step2: 将计算后的名次站位更新回数据库
                    entity.setPositionSite(positionSite);
                    affectCount += myMapper.updatePositionSite(entity);
                    //endregion
                } catch (Exception ex) {
                    logger.error(ex.toString());
                }
            }
            pageNumber++;
        }
        //endregion

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
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentAbilityAnalysisEntity> entityList = myMapper.searchStudentAbilityAnalysisList(startIndex, pageSize, technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentCellphone);
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
            UniversityStudentAbilityEntity entity = myMapper.searchStudentAbilityInfo(universityCode, schoolID, studentID);
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
            UniversityStudentAbilityAnalysisEntity entity = myMapper.searchStudentTechnologyAbility(universityCode, schoolID, studentID, technologyID);
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
            List<StudentCodeStandardAnalysisEntity> entityList = myMapper.searchCodeStandardAnalysis(universityCode, schoolID, studentID, technologyID);
            if (entityList.isEmpty()) {
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

    private String getTechnologyLevel(float finishKnowledgeRate, int finishUnionExercisesCount, int joinProjectCount) {
        if (finishKnowledgeRate < 50) {
            return "L1";
        }
        if ((finishKnowledgeRate >= 50 && finishKnowledgeRate < 90) ||
                (finishKnowledgeRate >= 90 && finishUnionExercisesCount < 3)) {
            return "L2";
        }
        if (finishKnowledgeRate >= 90 && (finishUnionExercisesCount >= 3 && finishUnionExercisesCount < 6)) {
            return "L3";
        }
        if (finishKnowledgeRate >= 90 && (finishUnionExercisesCount >= 6 && finishUnionExercisesCount < 9)) {
            return "L4";
        }
        if ((finishKnowledgeRate >= 90 && (finishUnionExercisesCount >= 9 && finishUnionExercisesCount < 12)) ||
                (finishKnowledgeRate >= 90 && (finishUnionExercisesCount >= 12 && joinProjectCount == 0))) {
            return "L5";
        }
        if (finishKnowledgeRate >= 90 && finishUnionExercisesCount >= 12 && (joinProjectCount >= 1 && joinProjectCount < 3)) {
            return "L6";
        }
        if (finishKnowledgeRate >= 90 && finishUnionExercisesCount >= 12 && (joinProjectCount >= 3 && joinProjectCount < 5)) {
            return "L7";
        }
        if (finishKnowledgeRate >= 90 && finishUnionExercisesCount >= 12 && joinProjectCount >= 5) {
            return "L7";
        }
        return "UN";
    }

    private double calculateStandardScore(int finishKnowledgeCount, float onceCompilationSuccessRate, float onceRunSuccessRate, int finishUnionExercisesCount, int finishProjectCount) {
        final double FINISH_KNOWLEDGE_WEIGHT = 0.30;
        final double ONCE_COMPILATION_SUCCESS_RATE_WEIGHT = 0.05;
        final double ONCE_RUN_SUCCESS_RATE_WEIGHT = 0.10;
        final double FINISH_UNION_EXERCISES_WEIGHT = 0.25;
        final double FINISH_PROJECT_WEIGHT = 0.30;

        DecimalFormat df = new DecimalFormat("0.00");
        double standardScore = finishKnowledgeCount * FINISH_KNOWLEDGE_WEIGHT +
                onceCompilationSuccessRate * ONCE_COMPILATION_SUCCESS_RATE_WEIGHT +
                onceRunSuccessRate * ONCE_RUN_SUCCESS_RATE_WEIGHT +
                finishUnionExercisesCount * FINISH_UNION_EXERCISES_WEIGHT +
                finishProjectCount * FINISH_PROJECT_WEIGHT;

        return Double.parseDouble(df.format(standardScore));
    }
}
