package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.DataStatusConstant;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.entity.*;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.*;
import com.shangshufang.apiservice.service.AnalysisAbilityService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisAbilityServiceImpl implements AnalysisAbilityService {
    @Autowired
    private AnalysisAbilityMapper myMapper;
    @Autowired
    private TechnologyKnowledgeMapper knowledgeMapper;
    @Autowired
    private CourseSignUpMapper signUpMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentCourseExercisesDetailMapper courseExercisesDetailMapper;

    private final Logger logger = LogManager.getLogger(AnalysisAbilityServiceImpl.class);

    @Override
    public UnifiedResponse analyse() {
        try {
            int affectCount = 0;
            affectCount += analysisStudentAbility();
            affectCount += changeStudentPosition();
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectCount);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList(int pageNumber,
                                    int pageSize,
                                    int directionID,
                                    int categoryID,
                                    int technologyID,
                                    int universityCode,
                                    int schoolID,
                                    int studentID,
                                    String studentName) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentAbilityAnalysisVO> modelList = new ArrayList<>();
            studentName = studentName.equals(ParameterConstant.NO_PARAMETER) ? null : studentName;
            int totalCount = myMapper.searchStudentAbilityTotalCount(
                    directionID, categoryID, technologyID, universityCode, schoolID, studentID, studentName);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentAbilityAnalysisEntity> entityList = myMapper.searchStudentAbilityList(
                    startIndex, pageSize, directionID, categoryID, technologyID, universityCode, schoolID, studentID, studentName);
            for (StudentAbilityAnalysisEntity entity : entityList) {
                StudentAbilityAnalysisVO model = new StudentAbilityAnalysisVO();
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
    public UnifiedResponse findStudentAbilitySummary(int studentID) {
        try {
            StudentAbilitySummaryEntity entity = myMapper.searchStudentSummary(studentID);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            StudentAbilitySummaryVO model = new StudentAbilitySummaryVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findLearningTechnologyList(int studentID) {
        try {
            List<StudentAbilityAnalysisVO> modelList = new ArrayList<>();
            List<StudentAbilityAnalysisEntity> entityList = myMapper.searchLearningTechnologyList(studentID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (StudentAbilityAnalysisEntity entity : entityList) {
                StudentAbilityAnalysisVO model = new StudentAbilityAnalysisVO();
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
    public UnifiedResponse findTechnologySummary(int studentID, int technologyID) {
        try {
            StudentAbilityAnalysisVO model = new StudentAbilityAnalysisVO();
            StudentAbilityAnalysisEntity entity = myMapper.searchStudentAbility(studentID, technologyID);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //取得当前技术包含的知识点总数
            int learningPhaseID = 0;
            int knowledgeTotalCount = knowledgeMapper.searchTotalCount(technologyID, learningPhaseID, DataStatusConstant.ACTIVE);

            //取得掌握薄弱的知识点总数
            int weaknessKnowledgeCount = courseExercisesDetailMapper.searchWeaknessKnowledgeTotalCount(studentID, technologyID);

            //Step4: 取得未掌握的知识点总数
            int noLearningKnowledgeCount = knowledgeTotalCount - entity.getFinishedKnowledgeCount() - weaknessKnowledgeCount;

            ObjectConvertUtils.toBean(entity, model);
            model.setKnowledgeTotalCount(knowledgeTotalCount);
            model.setWeaknessKnowledgeCount(weaknessKnowledgeCount);
            model.setNoLearnKnowledgeCount(noLearningKnowledgeCount);

            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findCodeGuidelineSummary(int studentID, int languageID) {
        try {
            List<CodeGuidelineSummaryVO> modelList = new ArrayList<>();
            List<CodeGuidelineSummaryEntity> entityList = myMapper.searchCodeGuidelineSummaryList(studentID, languageID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (CodeGuidelineSummaryEntity entity : entityList) {
                CodeGuidelineSummaryVO model = new CodeGuidelineSummaryVO();
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
    public UnifiedResponse findFinishedKnowledgeList(int pageNumber,
                                                     int pageSize,
                                                     int studentID,
                                                     int technologyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyKnowledgeVO> modelList = new ArrayList<>();
            int totalCount = courseExercisesDetailMapper.searchLearnedKnowledgeTotalCount(studentID, technologyID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyKnowledgeEntity> entityList = courseExercisesDetailMapper.searchStudentLearnedKnowledgeList(startIndex, pageSize, studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (TechnologyKnowledgeEntity entity : entityList) {
                TechnologyKnowledgeVO model = new TechnologyKnowledgeVO();
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
    public UnifiedResponse findWeaknessKnowledgeList(int pageNumber,
                                                     int pageSize,
                                                     int studentID,
                                                     int technologyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyKnowledgeVO> modelList = new ArrayList<>();
            int totalCount = courseExercisesDetailMapper.searchWeaknessKnowledgeTotalCount(studentID, technologyID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyKnowledgeEntity> entityList = courseExercisesDetailMapper.searchStudentWeaknessKnowledgeList(startIndex, pageSize, studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (TechnologyKnowledgeEntity entity : entityList) {
                TechnologyKnowledgeVO model = new TechnologyKnowledgeVO();
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
    public UnifiedResponse findNoLearningKnowledgeList(int pageNumber,
                                                       int pageSize,
                                                       int studentID,
                                                       int technologyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyKnowledgeVO> modelList = new ArrayList<>();
            int learningPhaseID = 0;
            int knowledgeTotalCount = knowledgeMapper.searchTotalCount(technologyID, learningPhaseID, DataStatusConstant.ACTIVE);
            int learnedKnowledgeCount = courseExercisesDetailMapper.searchLearnedKnowledgeTotalCount(studentID, technologyID);
            int weaknessKnowledgeCount = courseExercisesDetailMapper.searchWeaknessKnowledgeTotalCount(studentID, technologyID);
            int noLearnKnowledgeCount = knowledgeTotalCount - learnedKnowledgeCount - weaknessKnowledgeCount;
            if (noLearnKnowledgeCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyKnowledgeEntity> entityList =
                    courseExercisesDetailMapper.searchStudentNoLearnKnowledgeList(startIndex, pageSize, studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (TechnologyKnowledgeEntity entity : entityList) {
                TechnologyKnowledgeVO model = new TechnologyKnowledgeVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(noLearnKnowledgeCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    //region 私有方法
    private int analysisStudentAbility() {
        int affectCount = 0;
        int pageNumber = 1;
        final int PAGE_SIZE = 10;
        DecimalFormat df = new DecimalFormat("0.00");

        while (true) {
            int startIndex = (pageNumber - 1) * PAGE_SIZE;
            List<CourseSignUpEntity> signUpEntityList = signUpMapper.searchAllStudent(startIndex, PAGE_SIZE);
            if (signUpEntityList.isEmpty()) {
                break;
            }
            for (CourseSignUpEntity signUpEntity : signUpEntityList) {
                try {
                    StudentAbilityAnalysisEntity entity = new StudentAbilityAnalysisEntity();
                    //region 取得当前学生所报名课程的详细信息（包含对应的技术）
                    CourseEntity courseEntity = courseMapper.search(
                            signUpEntity.getCourseUniversityCode(),
                            signUpEntity.getCourseSchoolID(),
                            signUpEntity.getCourseID(),
                            ParameterConstant.NO_PARAMETER);
                    //endregion

                    //region 取得当前技术的知识点总数量
                    int learningPhaseID = 0;
                    int knowledgeTotalCount = knowledgeMapper.searchTotalCount(
                            courseEntity.getTechnologyID(), learningPhaseID, DataStatusConstant.ACTIVE);
                    //endregion

                    //region 取得当前学生已掌握的知识点数量及占比
                    int learnedKnowledgeCount = courseExercisesDetailMapper.searchLearnedKnowledgeTotalCount(signUpEntity.getStudentID(), courseEntity.getTechnologyID());
                    float finishKnowledgeRate = Float.parseFloat(df.format((float) learnedKnowledgeCount / knowledgeTotalCount));
                    entity.setFinishedKnowledgeCount(learnedKnowledgeCount);
                    entity.setFinishedKnowledgePercent(finishKnowledgeRate * 100);
                    //endregion

                    //region 取得当前学生已完成的该技术的综合练习
                    int finishedUnitExercisesCount = 0;
                    entity.setFinishedUnitExercisesCount(finishedUnitExercisesCount);
                    //endregion

                    //region 取得当前学生参与的与该技术有关的实战项目
                    int joinedProjectCount = 0;
                    entity.setJoinedProjectCount(joinedProjectCount);
                    //endregion

                    //region 取得该学生当前技术的能力级别
                    String abilityLevel = getTechnologyLevel(finishKnowledgeRate, finishedUnitExercisesCount, joinedProjectCount);
                    entity.setAbilityLevel(abilityLevel);
                    //endregion

                    //region 根据当前学生的数据，计算能力标准分
                    double standardScore = calculateStandardScore(learnedKnowledgeCount, finishedUnitExercisesCount, joinedProjectCount);
                    entity.setStandardScore(standardScore);
                    //endregion

                    //region 将已取得的分析结果保存到数据库
                    int totalCount = myMapper.searchAbilityAnalysisTotalCount(entity.getStudentID(), entity.getTechnologyID());
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
        return affectCount;
    }

    private int changeStudentPosition() {
        int affectCount = 0;
        int pageNumber = 1;
        final int PAGE_SIZE = 10;

        //取得技术列表
        List<StudentAbilityAnalysisEntity> technologyEntityList = myMapper.searchTechnologyList();
        if (technologyEntityList.isEmpty()) {
            return affectCount;
        }
        for (StudentAbilityAnalysisEntity technologyEntity : technologyEntityList) {
            //取得当前技术对应的学生总数
            int studentTotalCount = myMapper.searchStudentTotalCount(technologyEntity.getTechnologyID());
            if (studentTotalCount == 0) {
                continue;
            }
            //取得当前技术的学生列表，并计算每个学生的整体排名
            int startIndex = (pageNumber - 1) * PAGE_SIZE;
            while (true) {
                List<StudentAbilityAnalysisEntity> studentList = myMapper.searchStudentList(
                        technologyEntity.getTechnologyID(),
                        startIndex,
                        PAGE_SIZE);
                if (studentList.isEmpty()) {
                    break;
                }
                for (StudentAbilityAnalysisEntity studentEntity : studentList) {
                    //根据当前学生的能力分值，取得分数低于该分数的学生人数
                    int lowerScoreCount = myMapper.searchLowerThanScoreTotalCount(
                            technologyEntity.getTechnologyID(),
                            studentEntity.getStandardScore());

                    //计算该学生在当前技术的整体排名
                    float positionSite = (float) lowerScoreCount / studentTotalCount;
                    studentEntity.setPositionSite(positionSite * 100);

                    //将该学生在当前技术的整体排名更新到数据库
                    affectCount += myMapper.updatePositionSite(studentEntity);
                }
                pageNumber++;
            }
        }

        return affectCount;
    }

    private String getTechnologyLevel(float finishKnowledgeRate,
                                      int finishUnionExercisesCount,
                                      int joinProjectCount) {
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

    private double calculateStandardScore(int learnedKnowledgeCount,
                                          int finishedUnitExercisesCount,
                                          int joinedProjectCount) {
        final double FINISH_KNOWLEDGE_WEIGHT = 0.35;
        final double FINISH_UNION_EXERCISES_WEIGHT = 0.25;
        final double FINISH_PROJECT_WEIGHT = 0.40;

        DecimalFormat df = new DecimalFormat("0.00");
        double standardScore = learnedKnowledgeCount * FINISH_KNOWLEDGE_WEIGHT +
                finishedUnitExercisesCount * FINISH_UNION_EXERCISES_WEIGHT +
                joinedProjectCount * FINISH_PROJECT_WEIGHT;
        return Double.parseDouble(df.format(standardScore));
    }
    //endregion
}
