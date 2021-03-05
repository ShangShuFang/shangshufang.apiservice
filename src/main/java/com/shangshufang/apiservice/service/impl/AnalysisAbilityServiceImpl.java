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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AnalysisAbilityServiceImpl implements AnalysisAbilityService {
    @Autowired
    private AnalysisAbilityMapper myMapper;
    @Autowired
    private UniversityStudentMapper studentMapper;
    @Autowired
    private TechnologyMapper technologyMapper;
    @Autowired
    private TechnologyKnowledgeMapper knowledgeMapper;
    @Autowired
    private CourseSignUpMapper signUpMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentComprehensiveExercisesMapper studentComprehensiveExercisesMapper;
    @Autowired
    private StudentCourseExercisesDetailMapper courseExercisesDetailMapper;
    @Autowired
    private ComprehensiveExercisesAnalysisMapper comprehensiveExercisesAnalysisMapper;
    @Autowired
    private ResumeBrowsingHistoryMapper browsingHistoryMapper;

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
                //查询完成的就业测评的数量
                int comprehensiveExercisesCount =
                        studentComprehensiveExercisesMapper.searchTotalCountWithTechnology(
                                entity.getStudentID(),
                                entity.getTechnologyID());
                model.setFinishedUnitExercisesCount(comprehensiveExercisesCount);

                //查询个人详细信息浏览量
                int browseCount = browsingHistoryMapper.searchBrowsedByCompanyTotalCount(entity.getStudentID());
                model.setBrowseCount(browseCount);

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
            int browseCount = browsingHistoryMapper.searchBrowsedByCompanyTotalCount(studentID);
            StudentAbilitySummaryVO model = new StudentAbilitySummaryVO();
            ObjectConvertUtils.toBean(entity, model);
            model.setBrowseCount(browseCount);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findLearningTechnologyList(int studentID) {
        try {
            final int startIndex = 0;
            final int pageSize = 9999;
            final int universityCode = 0;
            final int schoolID = 0;
            List<StudentAbilityAnalysisVO> modelList = new ArrayList<>();
            List<CourseSignUpEntity> signCourseEntityList =
                    signUpMapper.searchStudentSignUpList(startIndex, pageSize, universityCode, schoolID, studentID);
            if (signCourseEntityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<Integer> technologyList =
                    signCourseEntityList.stream()
                            .map(CourseSignUpEntity::getTechnologyID)
                            .collect(Collectors.toList())
                            .stream()
                            .distinct()
                            .collect(Collectors.toList());

            for (Integer technologyID : technologyList) {
                StudentAbilityAnalysisEntity entity = myMapper.searchLearningTechnology(studentID, technologyID);
                if (entity == null) {
                    continue;
                }
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
    public UnifiedResponse findAllTechnologyList(int studentID) {
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

    @Override
    public UnifiedResponse selectComprehensiveExercisesAnalysisList(int studentID) {
        try {
            //取得热门技术测评题数量以及学生提交的数量
            List<ComprehensiveExercisesAnalysisVO> modelList = new ArrayList<>();
            List<ComprehensiveExercisesAnalysisEntity> entityList =
                    comprehensiveExercisesAnalysisMapper.searchComprehensiveExercisesAnalysis(studentID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ComprehensiveExercisesAnalysisEntity entity : entityList) {
                ComprehensiveExercisesAnalysisVO model = new ComprehensiveExercisesAnalysisVO();
                ObjectConvertUtils.toBean(entity, model);
                //取得每个热门技术测评题涉及的知识点分析情况
                List<ComprehensiveExercisesKnowledgeAnalysisVO> knowledgeAnalysisModelList = new ArrayList<>();
                List<ComprehensiveExercisesKnowledgeAnalysisEntity> knowledgeAnalysisEntityList =
                        comprehensiveExercisesAnalysisMapper.searchComprehensiveExercisesKnowledgeAnalysis(studentID, entity.getTechnologyID());
//                if (knowledgeAnalysisEntityList.isEmpty()) {
//                    continue;
//                }
                for (ComprehensiveExercisesKnowledgeAnalysisEntity KnowledgeAnalysisEntity : knowledgeAnalysisEntityList) {
                    ComprehensiveExercisesKnowledgeAnalysisVO knowledgeAnalysisModel = new ComprehensiveExercisesKnowledgeAnalysisVO();
                    ObjectConvertUtils.toBean(KnowledgeAnalysisEntity, knowledgeAnalysisModel);
                    knowledgeAnalysisModelList.add(knowledgeAnalysisModel);
                }
                model.setKnowledgeAnalysisVOList(knowledgeAnalysisModelList);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse selectComprehensiveExercisesSubmitList(int studentID) {
        try {
            List<MapVO> modelList = new ArrayList<>();
            List<MapEntity> entityList =
                    comprehensiveExercisesAnalysisMapper.selectComprehensiveExercisesSubmit(studentID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (MapEntity entity : entityList) {
                MapVO model = new MapVO();
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
    public UnifiedResponse searchComprehensiveExercisesKnowledgeAnalysis(int studentID, int technologyID) {
        try {
            List<ComprehensiveExercisesKnowledgeAnalysisVO> modelList = new ArrayList<>();
            List<ComprehensiveExercisesKnowledgeAnalysisEntity> entityList =
                    comprehensiveExercisesAnalysisMapper.searchComprehensiveExercisesKnowledgeAnalysis(studentID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (ComprehensiveExercisesKnowledgeAnalysisEntity entity : entityList) {
                ComprehensiveExercisesKnowledgeAnalysisVO model = new ComprehensiveExercisesKnowledgeAnalysisVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);
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
        final int UNIVERSITY_CODE = 0;
        final int SCHOOL_ID = 0;
        final int MAJOR_ID = 0;
        final String FULL_NAME = null;

        while (true) {
            int startIndex = (pageNumber - 1) * PAGE_SIZE;
            List<UniversityStudentEntity> studentEntityList =
                    studentMapper.searchList(startIndex, PAGE_SIZE, UNIVERSITY_CODE, SCHOOL_ID, MAJOR_ID, FULL_NAME);
            if (studentEntityList.isEmpty()) {
                break;
            }
            for (UniversityStudentEntity studentEntity : studentEntityList) {
                try {
                    StudentAbilityAnalysisEntity entity = new StudentAbilityAnalysisEntity();
                    ObjectConvertUtils.toBean(studentEntity, entity);

                    //region 查询参与课程的数量以及完成就业测评数量
                    final int directionCode = 0;
                    final int programLanguage = 0;
                    final int difficultyLevelCode = 0;
                    final String dataStatus = null;
                    int courseSignCount = signUpMapper.searchStudentSignUpTotalCount(UNIVERSITY_CODE, SCHOOL_ID, entity.getStudentID());
                    int comprehensiveExercisesCount = studentComprehensiveExercisesMapper.searchTotalCount4Student(entity.getStudentID(), directionCode, programLanguage, difficultyLevelCode, dataStatus);
                    if (courseSignCount == 0 && comprehensiveExercisesCount == 0) {
                        continue;
                    }
                    entity.setJoinedProjectCount(0);//todo 设置参与的项目数量
                    //endregion

                    //region 保存当前学生的能力分析结果
                    if (courseSignCount > 0 && comprehensiveExercisesCount == 0) { //只参加了课程学习，没有提交就业测评
                        affectCount += saveWithCourseExercisesOnly(entity);
                    } else if (courseSignCount == 0 && comprehensiveExercisesCount > 0) {//没有参加课程学习，只提交了就业测评
                        affectCount += saveWithComprehensiveExercisesOnly(entity);
                    } else { //参加了课程学习并提交了就业测评
                        affectCount += saveWithCourseAndComprehensiveExercises(entity);
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

    private int save(StudentAbilityAnalysisEntity entity) {
        int affectCount = 0;
        int totalCount = myMapper.searchAbilityAnalysisTotalCount(entity.getStudentID(), entity.getTechnologyID());
        if (totalCount > 0) {
            affectCount = myMapper.update(entity);
        } else {
            affectCount = myMapper.insert(entity);
        }
        return affectCount;
    }

    private int saveWithCourseExercisesOnly(StudentAbilityAnalysisEntity entity) {
        int affectCount = 0;
        final int signCourseStartIndex = 0;
        final int signCoursePageNumber = 9999;
        final int UNIVERSITY_CODE = 0;
        final int SCHOOL_ID = 0;
        final int LEARNING_PHASE_ID = 0;
        DecimalFormat df = new DecimalFormat("0.00");

        List<CourseSignUpEntity> courseSignUpEntityList =
                signUpMapper.searchStudentSignUpList(
                        signCourseStartIndex,
                        signCoursePageNumber,
                        UNIVERSITY_CODE,
                        SCHOOL_ID,
                        entity.getStudentID());

        if (courseSignUpEntityList.isEmpty()) {
            return 0;
        }

        for (CourseSignUpEntity courseSignUpEntity : courseSignUpEntityList) {
            //region 取得当前技术的知识点总数量
            int knowledgeTotalCount = knowledgeMapper.searchTotalCount(
                    courseSignUpEntity.getTechnologyID(),
                    LEARNING_PHASE_ID,
                    DataStatusConstant.ACTIVE);
            //endregion

            entity.setTechnologyID(courseSignUpEntity.getTechnologyID());
            //region 取得当前学生已掌握的知识点数量及占比
            int learnedKnowledgeCount =
                    courseExercisesDetailMapper.searchLearnedKnowledgeTotalCount(
                            entity.getStudentID(),
                            courseSignUpEntity.getTechnologyID());
            float finishKnowledgeRate = Float.parseFloat(df.format((float) learnedKnowledgeCount / knowledgeTotalCount));
            entity.setFinishedKnowledgeCount(learnedKnowledgeCount);
            entity.setFinishedKnowledgePercent(finishKnowledgeRate * 100);
            //endregion

            //region 取得学生提交的就业测评的数量
            int comprehensiveExercisesCount =
                    studentComprehensiveExercisesMapper.searchTotalCountWithTechnology(
                            entity.getStudentID(),
                            courseSignUpEntity.getTechnologyID());
            //endregion


            //region 取得该学生当前技术的能力级别
            String abilityLevel = getTechnologyLevel(finishKnowledgeRate, comprehensiveExercisesCount, entity.getJoinedProjectCount());
            entity.setAbilityLevel(abilityLevel);
            //endregion

            //region 根据当前学生的数据，计算能力标准分
            double standardScore = calculateStandardScore(learnedKnowledgeCount, comprehensiveExercisesCount, entity.getJoinedProjectCount());
            entity.setStandardScore(standardScore);
            //endregion

            affectCount += save(entity);
        }
        return affectCount;
    }

    private int saveWithComprehensiveExercisesOnly(StudentAbilityAnalysisEntity entity) {
        int affectCount = 0;
        final int LEARNING_PHASE_ID = 0;
        DecimalFormat df = new DecimalFormat("0.00");

        List<TechnologyEntity> technologyEntityList = studentComprehensiveExercisesMapper.searchTechnologyList(entity.getStudentID());
        if (technologyEntityList.isEmpty()) {
            return 0;
        }
        for (TechnologyEntity technologyEntity : technologyEntityList) {
            if (technologyEntity.getTechnologyID() == 0) {
                continue;
            }
            entity.setTechnologyID(technologyEntity.getTechnologyID());
            //region 取得当前技术的知识点总数量
            int knowledgeTotalCount = knowledgeMapper.searchTotalCount(
                    technologyEntity.getTechnologyID(),
                    LEARNING_PHASE_ID,
                    DataStatusConstant.ACTIVE);
            //endregion
            if (knowledgeTotalCount == 0) {
                continue;
            }

            //region 取得当前学生已掌握的知识点数量及占比
            int learnedKnowledgeCount =
                    studentComprehensiveExercisesMapper.searchLearnedKnowledgeTotalCount(
                            entity.getStudentID(),
                            technologyEntity.getTechnologyID());
            float finishKnowledgeRate = Float.parseFloat(df.format((float) learnedKnowledgeCount / knowledgeTotalCount));
            entity.setFinishedKnowledgeCount(learnedKnowledgeCount);
            entity.setFinishedKnowledgePercent(finishKnowledgeRate * 100);
            //endregion

            //region 取得学生提交的就业测评的数量
            int comprehensiveExercisesCount =
                    studentComprehensiveExercisesMapper.searchTotalCountWithTechnology(
                            entity.getStudentID(),
                            technologyEntity.getTechnologyID());
            //endregion

            //region 取得该学生当前技术的能力级别
            String abilityLevel = getTechnologyLevel(finishKnowledgeRate, comprehensiveExercisesCount, entity.getJoinedProjectCount());
            entity.setAbilityLevel(abilityLevel);
            //endregion

            //region 根据当前学生的数据，计算能力标准分
            double standardScore = calculateStandardScore(learnedKnowledgeCount, comprehensiveExercisesCount, entity.getJoinedProjectCount());
            entity.setStandardScore(standardScore);
            //endregion

            affectCount += save(entity);
        }
        return affectCount;
    }

    private int saveWithCourseAndComprehensiveExercises(StudentAbilityAnalysisEntity entity) {
        int affectCount = 0;
        final int startIndex = 0;
        final int pageSize = 9999;
        final int signCourseStartIndex = 0;
        final int signCoursePageNumber = 9999;
        final int UNIVERSITY_CODE = 0;
        final int LEARNING_PHASE_ID = 0;
        final int SCHOOL_ID = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        HashMap<Integer, List<Integer>> learnedKnowledgeMap = new HashMap<Integer, List<Integer>>();

        //region 取得在课程学习中掌握的知识点
        List<CourseSignUpEntity> courseSignUpEntityList =
                signUpMapper.searchStudentSignUpList(
                        signCourseStartIndex,
                        signCoursePageNumber,
                        UNIVERSITY_CODE,
                        SCHOOL_ID,
                        entity.getStudentID());
        for (CourseSignUpEntity courseSignUpEntity : courseSignUpEntityList) {
            List<TechnologyKnowledgeEntity> learnedKnowledgeList =
                    courseExercisesDetailMapper.searchStudentLearnedKnowledgeList(
                            startIndex,
                            pageSize,
                            entity.getStudentID(),
                            courseSignUpEntity.getTechnologyID());
            if (!learnedKnowledgeList.isEmpty()) {
                List<Integer> learnedKnowledgeIDList =
                        learnedKnowledgeList.stream()
                                .map(TechnologyKnowledgeEntity::getKnowledgeID)
                                .collect(Collectors.toList());
                learnedKnowledgeMap.put(courseSignUpEntity.getTechnologyID(), learnedKnowledgeIDList);
            }

        }
        //endregion

        //region 取得通过综合练习掌握的知识点
        List<TechnologyEntity> technologyEntityList = studentComprehensiveExercisesMapper.searchTechnologyList(entity.getStudentID());
        for (TechnologyEntity technologyEntity : technologyEntityList) {
            List<TechnologyKnowledgeEntity> learnedKnowledgeList =
                    studentComprehensiveExercisesMapper.searchLearnedKnowledgeList(
                            entity.getStudentID(),
                            technologyEntity.getTechnologyID());
            if (!learnedKnowledgeList.isEmpty()) {
                List<Integer> learnedKnowledgeIDList =
                        learnedKnowledgeList.stream()
                                .map(TechnologyKnowledgeEntity::getKnowledgeID)
                                .collect(Collectors.toList());
                if (learnedKnowledgeMap.containsKey(technologyEntity.getTechnologyID())) {
                    List<Integer> knowledgeList = learnedKnowledgeMap.get(technologyEntity.getTechnologyID());
                    boolean existed = false;
                    for (Integer learnedKnowledge4Comprehensive : learnedKnowledgeIDList) {
                        for (Integer learnedKnowledge4Course : knowledgeList) {
                            if (learnedKnowledge4Comprehensive.equals(learnedKnowledge4Course)) {
                                existed = true;
                                break;
                            }
                        }
                        if (!existed) {
                            learnedKnowledgeMap.get(technologyEntity.getTechnologyID()).add(learnedKnowledge4Comprehensive);
                        }
                    }
                } else {
                    learnedKnowledgeMap.put(technologyEntity.getTechnologyID(), learnedKnowledgeIDList);
                }
            }
        }
        //endregion

        for (Integer i : learnedKnowledgeMap.keySet()) {
            int technologyID = i;
            int learnedKnowledgeCount = learnedKnowledgeMap.get(i).size();
            //region 取得当前技术的知识点总数量
            int knowledgeTotalCount = knowledgeMapper.searchTotalCount(
                    technologyID,
                    LEARNING_PHASE_ID,
                    DataStatusConstant.ACTIVE);
            //endregion
            //region 取得学生提交的就业测评的数量
            int comprehensiveExercisesCount =
                    studentComprehensiveExercisesMapper.searchTotalCountWithTechnology(
                            entity.getStudentID(),
                            technologyID);
            //endregion

            float finishKnowledgeRate = Float.parseFloat(df.format((float) learnedKnowledgeCount / knowledgeTotalCount));
            String abilityLevel = getTechnologyLevel(finishKnowledgeRate, comprehensiveExercisesCount, entity.getJoinedProjectCount());
            double standardScore = calculateStandardScore(learnedKnowledgeCount, comprehensiveExercisesCount, entity.getJoinedProjectCount());
            entity.setTechnologyID(technologyID);
            entity.setFinishedKnowledgeCount(learnedKnowledgeCount);
            entity.setFinishedKnowledgePercent(finishKnowledgeRate * 100);
            entity.setAbilityLevel(abilityLevel);
            entity.setStandardScore(standardScore);
            affectCount += save(entity);
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

            while (true) {
                int startIndex = (pageNumber - 1) * PAGE_SIZE;
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
