package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.*;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.AnalysisAbilityMapper;
import com.shangshufang.apiservice.service.AnalysisAbilityService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisAbilityServiceImpl implements AnalysisAbilityService {
    @Autowired
    private AnalysisAbilityMapper myMapper;
    private Logger logger = LogManager.getLogger(AnalysisAbilityServiceImpl.class);

    @Override
    public UnifiedResponse analyse() {
        int affectCount = 0;
        int pageNumber = 1;
        final int PAGE_SIZE = 10;

        //region 取得待处理的基础数据并逐一计算学习完成度、完成练习总数、一次性编译通过率、一次性运行正确率
        while (true) {
            int startIndex = (pageNumber - 1) * PAGE_SIZE;
            List<AbilityAnalysisResult4StudentMainInfoEntity> entityList;
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
            for (AbilityAnalysisResult4StudentMainInfoEntity entity : entityList) {
                try {
                    //region step1: 取得当前技术的知识点总数量
                    if (technologyID != entity.getTechnologyID()) {
                        technologyID = entity.getTechnologyID();
                        knowledgeTotalCount = myMapper.searchDataSource4KnowledgeCount(technologyID);
                    }
                    entity.setKnowledgeTotalCount(knowledgeTotalCount);
                    //endregion

                    //region step2: 取得当前学生已掌握的知识点数量
                    int finishKnowledgeCount = myMapper.searchDataSource4FinishKnowledgeCount(entity.getUniversityCode(), entity.getSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setFinishKnowledgeCount(finishKnowledgeCount);
                    //endregion

                    //region step3: 取得当前学生已完成的习题数量
                    int finishExercisesCount = myMapper.searchDataSource4FinishExercisesCount(entity.getUniversityCode(), entity.getSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setFinishExercisesCount(finishExercisesCount);
                    //endregion

                    //region step4: 取得当前学生一次性编译通过率
                    float onceCompilationSuccessRate = myMapper.searchDataSource4OnceCompilationSuccessRate(entity.getUniversityCode(), entity.getSchoolID(), entity.getStudentID(), entity.getTechnologyID());
                    entity.setOnceCompilationSuccessRate(onceCompilationSuccessRate);
                    //endregion

                    //region step5: 取得当前学生一次性运行成功率
                    float onceRunSuccessRate = myMapper.searchDataSource4OnceRunSuccessRate(entity.getUniversityCode(), entity.getSchoolID(), entity.getStudentID(), entity.getTechnologyID());
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
                    entity.setFinishKnowledgePercent(finishKnowledgeRate * 100);
                    entity.setAbilityLevel(abilityLevel);
                    //endregion

                    //region step9: 根据当前学生的数据，计算能力标准分
                    double standardScore = calculateStandardScore(finishKnowledgeCount, onceCompilationSuccessRate, onceRunSuccessRate, finishUnionExercisesCount, finishProjectCount);
                    entity.setStandardScore(standardScore);
                    //endregion

                    //region step10: 将已取得的分析结果保存到数据库
                    int totalCount = myMapper.searchAbilityAnalysisTotalCount(entity.getUniversityCode(), entity.getSchoolID(), entity.getStudentID(), entity.getTechnologyID());
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
            List<AbilityAnalysisResult4StudentMainInfoEntity> entityList;
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
            for (AbilityAnalysisResult4StudentMainInfoEntity entity : entityList) {
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
                    entity.setPositionSite(positionSite * 100);
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
    public UnifiedResponse findList(int pageNumber, int pageSize, int technologyID, int studentUniversityCode, int studentSchoolID, int teacherUniversityCode, int teacherSchoolID, int teacherID, String studentCellphone) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<AbilityAnalysisResult4StudentMainInfoVO> modelList = new ArrayList<>();
            studentCellphone = studentCellphone.equals(ParameterConstant.NO_PARAMETER) ? null : studentCellphone;
            int totalCount = myMapper.searchStudentMainResultTotalCount(technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentCellphone);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<AbilityAnalysisResult4StudentMainInfoEntity> entityList = myMapper.searchStudentMainResultList(startIndex, pageSize, technologyID, studentUniversityCode, studentSchoolID, teacherUniversityCode, teacherSchoolID, teacherID, studentCellphone);
            for (AbilityAnalysisResult4StudentMainInfoEntity entity : entityList) {
                AbilityAnalysisResult4StudentMainInfoVO model = new AbilityAnalysisResult4StudentMainInfoVO();
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
    public UnifiedResponse findStudentSummaryResult(int universityCode, int schoolID, int studentID) {
        try {
            AbilityAnalysisResult4StudentSummaryEntity entity = myMapper.searchStudentSummaryResult(universityCode, schoolID, studentID);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            AbilityAnalysisResult4StudentSummaryVO model = new AbilityAnalysisResult4StudentSummaryVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findLearningTechnologyResultList(int universityCode, int schoolID, int studentID) {
        try {
            List<AbilityAnalysisResult4LearningTechnologySummaryVO> modelList = new ArrayList<>();
            List<AbilityAnalysisResult4LearningTechnologySummaryEntity> entityList = myMapper.searchLearningTechnologyResultList(universityCode, schoolID, studentID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (AbilityAnalysisResult4LearningTechnologySummaryEntity entity : entityList) {
                AbilityAnalysisResult4LearningTechnologySummaryVO model = new AbilityAnalysisResult4LearningTechnologySummaryVO();
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
    public UnifiedResponse findKnowledgeSummaryResult(int universityCode, int schoolID, int studentID, int technologyID) {
        try {
            AbilityAnalysisResult4KnowledgeSummaryVO model = new AbilityAnalysisResult4KnowledgeSummaryVO();
            //Step1: 取得当前技术包含的知识点总数
            int knowledgeTotalCount = myMapper.searchDataSource4KnowledgeCount(technologyID);

            //Step2: 取得当前学生已掌握的知识点总数
            int finishKnowledgeCount = myMapper.searchDataSource4FinishKnowledgeCount(universityCode, schoolID, studentID, technologyID);

            //Step3: 取得当前学生正在学习的知识点总数
            int learningKnowledgeCount = myMapper.searchLearningKnowledgeTotalCount(universityCode, schoolID, studentID, technologyID);

            //Step4: 取得当前学生未学习的知识点总数
            int noLearningKnowledgeCount = knowledgeTotalCount - learningKnowledgeCount;

            //Step5: 取得当前学生掌握薄弱的知识点总数
            int weaknessKnowledgeCount = learningKnowledgeCount - finishKnowledgeCount;

            //Step5: 取得当前学生学习完成度
            DecimalFormat df = new DecimalFormat("0.00");
            float finishKnowledgePercent = Float.parseFloat(df.format((float) finishKnowledgeCount / knowledgeTotalCount)) * 100;

            model.setUniversityCode(universityCode);
            model.setSchoolID(schoolID);
            model.setStudentID(studentID);
            model.setTechnologyID(technologyID);
            model.setKnowledgeTotalCount(knowledgeTotalCount);
            model.setGraspKnowledgeCount(finishKnowledgeCount);
            model.setWeaknessKnowledgeCount(weaknessKnowledgeCount);
            model.setNoLearningKnowledgeCount(noLearningKnowledgeCount);
            model.setLearningPercentCount(finishKnowledgePercent);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findCodeGuidelineResult(int studentUniversityCode, int studentSchoolID, int studentID, int languageID) {
        try {
            List<AbilityAnalysisResult4CodeGuidelineSummaryVO> modelList = new ArrayList<>();
            List<AbilityAnalysisResult4CodeGuidelineSummaryEntity> entityList = myMapper.searchCodeStandardAnalysisResultList(studentUniversityCode, studentSchoolID, studentID, languageID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (AbilityAnalysisResult4CodeGuidelineSummaryEntity entity : entityList) {
                AbilityAnalysisResult4CodeGuidelineSummaryVO model = new AbilityAnalysisResult4CodeGuidelineSummaryVO();
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
    public UnifiedResponse findExerciseNumberResultList(int universityCode, int schoolID, int studentID, int technologyID) {
        try {
            List<AbilityAnalysisResult4ExerciseNumberTrendVO> modelList = new ArrayList<>();
            List<AbilityAnalysisResult4ExerciseNumberTrendEntity> entityList = myMapper.searchExerciseNumberTrend(universityCode, schoolID, studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (AbilityAnalysisResult4ExerciseNumberTrendEntity entity : entityList) {
                AbilityAnalysisResult4ExerciseNumberTrendVO model = new AbilityAnalysisResult4ExerciseNumberTrendVO();
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
    public UnifiedResponse findExercisePercentResultList(int universityCode, int schoolID, int studentID, int technologyID) {
        try {
            List<AbilityAnalysisResult4ExercisePercentTrendVO> modelList = new ArrayList<>();
            List<AbilityAnalysisResult4ExercisePercentTrendEntity> entityList = myMapper.searchExercisePercentTrend(universityCode, schoolID, studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (AbilityAnalysisResult4ExercisePercentTrendEntity entity : entityList) {
                AbilityAnalysisResult4ExercisePercentTrendVO model = new AbilityAnalysisResult4ExercisePercentTrendVO();
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
