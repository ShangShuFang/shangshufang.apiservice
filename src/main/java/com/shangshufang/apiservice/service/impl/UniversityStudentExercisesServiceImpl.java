package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.*;
import com.shangshufang.apiservice.dto.CourseExercisesPaperDTO;
import com.shangshufang.apiservice.dto.CourseProgramExercisesMarkDTO;
import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.entity.*;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.*;
import com.shangshufang.apiservice.service.UniversityStudentExercisesService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class UniversityStudentExercisesServiceImpl implements UniversityStudentExercisesService {
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private CourseSignUpMapper courseSignUpMapper;
    @Autowired
    private UniversityStudentExercisesMapper universityStudentExercisesMapper;
    @Autowired
    private ExerciseWarehouseKnowledgeChoiceQuestionMapper companyChoiceExercisesMapper;
    @Autowired
    private ExerciseWarehouseKnowledgeChoiceQuestionOptionMapper companyChoiceExercisesOptionMapper;
    @Autowired
    private ExerciseWarehouseKnowledgeBlankQuestionMapper companyBlankExercisesMapper;
    @Autowired
    private TechnologyKnowledgeExercisesMapper companyProgramExercisesMapper;
    @Autowired
    private UniversityExerciseKnowledgeChoiceMapper customChoiceExercisesMapper;
    @Autowired
    private UniversityExerciseKnowledgeChoiceOptionMapper customChoiceExercisesOptionMapper;
    @Autowired
    private UniversityExerciseKnowledgeBlankMapper customBlankExercisesMapper;
    @Autowired
    private UniversityExerciseKnowledgeProgramMapper customProgramExercisesMapper;
    @Autowired
    private StudentCourseExercisesMapper studentCourseExercisesMapper;
    @Autowired
    private StudentCourseExercisesDetailMapper studentCourseExercisesDetailMapper;
    @Autowired
    private StudentCourseExercisesChoiceAnswerMapper choiceAnswerMapper;
    @Autowired
    private StudentCourseExercisesBlankAnswerMapper blankAnswerMapper;
    @Autowired
    private StudentCourseExercisesProgramAnswerMapper programAnswerMapper;
    @Autowired
    private StudentCourseExercisesReviewMapper exercisesReviewMapper;
    @Autowired
    private StudentCourseExercisesProgramReviewDetailMapper programReviewDetailMapper;
    @Autowired
    private StudentCourseExercisesCodeReviewDetailMapper codeReviewDetailMapper;

    private final Logger logger = LogManager.getLogger(UniversityStudentExercisesServiceImpl.class);

    private final String SYS_ADMIN_ID = "0";

    @Override
    public UnifiedResponse findList(int pageNumber,
                                    int pageSize,
                                    int technologyID,
                                    int universityCode,
                                    int schoolID,
                                    int courseID,
                                    int studentID,
                                    String studentName,
                                    String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentCourseExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            studentName = studentName.equals(ParameterConstant.NO_PARAMETER) ? null : studentName;
            int totalCount = studentCourseExercisesMapper.searchTotalCountNew(technologyID, universityCode, schoolID, courseID, studentID, studentName, dataStatus);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentCourseExercisesEntity> entityList = studentCourseExercisesMapper.searchListNew(startIndex, pageSize, technologyID, universityCode, schoolID, courseID, studentID, studentName, dataStatus);
            for (StudentCourseExercisesEntity entity : entityList) {
                StudentCourseExercisesVO model = new StudentCourseExercisesVO();
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
    public UnifiedResponse findList(int pageNumber, int pageSize, int courseID, String dataStatus, String studentName) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentCourseExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            studentName = studentName.equals(ParameterConstant.NO_PARAMETER) ? null : studentName;
            int totalCount = studentCourseExercisesMapper.searchTotalCount(courseID, dataStatus, studentName);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentCourseExercisesEntity> entityList = studentCourseExercisesMapper.searchList(startIndex, pageSize, courseID, dataStatus, studentName);
            for (StudentCourseExercisesEntity entity : entityList) {
                StudentCourseExercisesVO model = new StudentCourseExercisesVO();
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
    public UnifiedResponse findList4Student(int pageNumber,
                                            int pageSize,
                                            int courseID,
                                            int studentID,
                                            String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentCourseExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = studentCourseExercisesMapper.searchTotalCount4Student(courseID, studentID, dataStatus);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<StudentCourseExercisesEntity> entityList =
                    studentCourseExercisesMapper.searchList4Student(startIndex, pageSize, courseID, studentID, dataStatus);
            for (StudentCourseExercisesEntity entity : entityList) {
                StudentCourseExercisesVO model = new StudentCourseExercisesVO();
                ObjectConvertUtils.toBean(entity, model);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

//    @Override
//    public UnifiedResponse findList4Technology(int pageNumber, int pageSize, int universityCode, int schoolID, int studentID, int technologyID, String dataStatus) {
//        try {
//            int startIndex = (pageNumber - 1) * pageSize;
//            List<UniversityStudentExercisesVO> modelList = new ArrayList<>();
//            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
//            int totalCount = universityStudentExercisesMapper.searchTotalCount4Technology(universityCode, schoolID, studentID, technologyID, dataStatus);
//            if (totalCount == 0) {
//                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
//            }
//            List<UniversityStudentExercisesEntity> entityList = universityStudentExercisesMapper.searchList4Technology(startIndex, pageSize, universityCode, schoolID, studentID, technologyID, dataStatus);
//            for (UniversityStudentExercisesEntity entity : entityList) {
//                UniversityStudentExercisesVO model = new UniversityStudentExercisesVO();
//                ObjectConvertUtils.toBean(entity, model);
//                modelList.add(model);
//            }
//            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
//        } catch (Exception ex) {
//            logger.error(ex.toString());
//            return UnifiedResponseManager.buildExceptionResponse();
//        }
//    }

    @Override
    public UnifiedResponse findProgramReviewList(int courseExercisesID, int courseExercisesDetailID) {
        try {
            List<StudentCourseExercises4ProgramDetailVO> modelList = new ArrayList<>();
            List<StudentCourseExercisesReviewEntity> entityList =
                    exercisesReviewMapper.searchList(courseExercisesID, courseExercisesDetailID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            for (StudentCourseExercisesReviewEntity entity : entityList) {
                StudentCourseExercises4ProgramDetailVO model = new StudentCourseExercises4ProgramDetailVO();
                //取得程序批改项明细
                StudentCourseExercisesProgramReviewDetailEntity programReviewEntity =
                        programReviewDetailMapper.search(courseExercisesID,
                                courseExercisesDetailID,
                                entity.getReviewID());
                if (programReviewEntity == null) {
                    throw new Exception(String.format("Can't find program detail, courseExercisesID: %s, courseExercisesDetailID: %s, reviewID: %s",
                            courseExercisesID, courseExercisesDetailID, entity.getReviewID()));
                }
                ObjectConvertUtils.toBean(programReviewEntity, model);

                //取得程序代码规范性问题列表
                List<StudentCourseExercisesCodeReviewDetailEntity> codeReviewEntityList = codeReviewDetailMapper.searchList(courseExercisesID,
                        courseExercisesDetailID,
                        entity.getReviewID());
                if (!codeReviewEntityList.isEmpty()) {
                    List<CodeStandardVO> codeStandardErrorList = new ArrayList<>();
                    for (StudentCourseExercisesCodeReviewDetailEntity codeReviewEntity : codeReviewEntityList) {
                        CodeStandardVO codeStandardVO = new CodeStandardVO();
                        ObjectConvertUtils.toBean(codeReviewEntity, codeStandardVO);
                        codeStandardErrorList.add(codeStandardVO);
                    }
                    model.setCodeStandardErrorList(codeStandardErrorList);
                }
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
    public UnifiedResponse findCourseExercisesDetail(int courseExercisesID) {
        try {
            StudentCourseExercisesVO model = getCourseExercisesModel(courseExercisesID);
            if (model == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private StudentCourseExercisesVO getCourseExercisesModel (int courseExercisesID) throws Exception {
        StudentCourseExercisesVO model = new StudentCourseExercisesVO();

        //region 取得练习的基本信息
        StudentCourseExercisesEntity courseExercisesEntity =
                studentCourseExercisesMapper.search(courseExercisesID);
        if (courseExercisesEntity == null) {
            return null;
        }
        ObjectConvertUtils.toBean(courseExercisesEntity, model);
        //endregion

        //region 取得选择题（企业题库+自定义题库）
        List<StudentCourseExercises4SingleChoiceDetailVO> singleChoiceList = new ArrayList<>();
        List<StudentCourseExercises4MultipleChoiceDetailVO> multipleChoiceList = new ArrayList<>();

        //从数据库中取得选择题，包括企业题库和自定义题库
        List<StudentCourseExercisesDetailEntity> choiceEntityList =
                studentCourseExercisesDetailMapper.searchChoiceList(courseExercisesID);
        if (!choiceEntityList.isEmpty()) {
            //region 根据题目来源，分别取出每个选择题的选项列表
            for (StudentCourseExercisesDetailEntity choiceEntity : choiceEntityList) {
                if (choiceEntity.getExercisesSourceType() == ExercisesSourceTypeConstant.COMPANY) {
                    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> companyChoiceOptionEntityList =
                            companyChoiceExercisesOptionMapper.searchList(choiceEntity.getExercisesID());
                    choiceEntity.setChoiceOptionEntityList(companyChoiceOptionEntityList);
                }
                if (choiceEntity.getExercisesSourceType() == ExercisesSourceTypeConstant.SELF) {
                    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> customChoiceOptionEntityList =
                            customChoiceExercisesOptionMapper.searchList(choiceEntity.getExercisesID());
                    choiceEntity.setChoiceOptionEntityList(customChoiceOptionEntityList);
                }
            }
            //endregion

            //region 根据从数据库取得选择题，按照单选、多选分类，分别设置单选和多选的返回对象
            for (StudentCourseExercisesDetailEntity choiceEntity : choiceEntityList) {
                List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> optionEntityList = choiceEntity.getChoiceOptionEntityList();
                List<ExerciseWarehouseKnowledgeChoiceQuestionOptionVO> optionModelList = new ArrayList<>();
                for (ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity optionEntity : optionEntityList) {
                    ExerciseWarehouseKnowledgeChoiceQuestionOptionVO optionModel = new ExerciseWarehouseKnowledgeChoiceQuestionOptionVO();
                    ObjectConvertUtils.toBean(optionEntity, optionModel);
                    optionModelList.add(optionModel);
                }

                if (choiceEntity.getExercisesType() == ExercisesTypeConstant.SingleChoice) {
                    StudentCourseExercises4SingleChoiceDetailVO singleChoice = new StudentCourseExercises4SingleChoiceDetailVO();
                    ObjectConvertUtils.toBean(choiceEntity, singleChoice);
                    singleChoice.setOptionList(optionModelList);

                    if (!choiceEntity.getCorrectResult().equals(ExercisesStatusConstant.Pending)) {
                        StudentCourseExercisesChoiceAnswerEntity answerEntity =
                                choiceAnswerMapper.searchLatestAnswer(courseExercisesID, choiceEntity.getCourseExercisesDetailID());
                        if(answerEntity == null) {
                            String errorMessage = String.format("Can't find single choice answer, courseExercisesID: %s, courseExercisesDetailID: %s",
                                    courseExercisesID, choiceEntity.getCourseExercisesDetailID());
                            throw new Exception(errorMessage);
                        }
                        singleChoice.setSelectedOptionID(Integer.parseInt(answerEntity.getSelectedOption()));
                    }
                    singleChoiceList.add(singleChoice);
                }
                if (choiceEntity.getExercisesType() == ExercisesTypeConstant.MultipleChoice) {
                    StudentCourseExercises4MultipleChoiceDetailVO multipleChoice = new StudentCourseExercises4MultipleChoiceDetailVO();
                    ObjectConvertUtils.toBean(choiceEntity, multipleChoice);
                    multipleChoice.setOptionList(optionModelList);
                    if (!choiceEntity.getCorrectResult().equals(ExercisesStatusConstant.Pending)) {
                        StudentCourseExercisesChoiceAnswerEntity answerEntity =
                                choiceAnswerMapper.searchLatestAnswer(courseExercisesID, choiceEntity.getCourseExercisesDetailID());
                        if(answerEntity == null) {
                            String errorMessage = String.format("Can't find multiple choice answer, courseExercisesID: %s, courseExercisesDetailID: %s",
                                    courseExercisesID, choiceEntity.getCourseExercisesDetailID());
                            throw new Exception(errorMessage);
                        }
                        multipleChoice.setSelectedOption(answerEntity.getSelectedOption());
                    }
                    multipleChoiceList.add(multipleChoice);
                }
            }
            //endregion

            model.setSingleChoiceExercisesList(singleChoiceList);
            model.setMultipleChoiceExercisesList(multipleChoiceList);
        }
        //endregion

        //region 取得填空题（企业题库+自定义题库）
        List<StudentCourseExercises4BlankDetailVO> blankExercisesList = new ArrayList<>();
        List<StudentCourseExercisesDetailEntity> blankEntityList =
                studentCourseExercisesDetailMapper.searchBlankList(courseExercisesID);
        if (!blankEntityList.isEmpty()) {
            for (StudentCourseExercisesDetailEntity blankExercisesEntity : blankEntityList) {
                StudentCourseExercises4BlankDetailVO blankExercisesModel = new StudentCourseExercises4BlankDetailVO();
                ObjectConvertUtils.toBean(blankExercisesEntity, blankExercisesModel);
                //查询当前填空题的正确答案
                if (blankExercisesEntity.getExercisesSourceType() == ExercisesSourceTypeConstant.COMPANY) {
                    //从企业题库取得当前选择题的正确答案
                    ExerciseWarehouseKnowledgeBlankQuestionEntity companyBlankExercisesEntity =
                            companyBlankExercisesMapper.search(blankExercisesEntity.getExercisesID());
                    if (companyBlankExercisesEntity == null) {
                        throw new Exception(String.format("Can't find company blank exercises by exercisesID: %s", blankExercisesEntity.getExercisesID()));
                    }
                    blankExercisesModel.setRightAnswer(companyBlankExercisesEntity.getRightAnswer());
                } else {
                    //从自定义题库取得当前选择题的正确答案
                    UniversityExerciseKnowledgeBlankEntity customBlankExercisesEntity =
                            customBlankExercisesMapper.search(blankExercisesEntity.getExercisesID());
                    if (customBlankExercisesEntity == null) {
                        throw new Exception(String.format("Can't find custom blank exercises by exercisesID: %s", blankExercisesEntity.getExercisesID()));
                    }
                    blankExercisesModel.setRightAnswer(customBlankExercisesEntity.getRightAnswer());
                }
                if (!blankExercisesModel.getCorrectResult().equals(ExercisesStatusConstant.Pending)) {
                    StudentCourseExercisesBlankAnswerEntity answerEntity =
                            blankAnswerMapper.searchLatestAnswer(courseExercisesID, blankExercisesEntity.getCourseExercisesDetailID());
                    if(answerEntity == null) {
                        throw new Exception(String.format("Can't find fill blank answer, courseExercisesID: %s, courseExercisesDetailID: %s",
                                courseExercisesID, blankExercisesEntity.getCourseExercisesDetailID()));
                    }
                    blankExercisesModel.setFillInContent(answerEntity.getFillInContent());
                }
                blankExercisesList.add(blankExercisesModel);
            }
            model.setBlankExercisesList(blankExercisesList);
        }
        //endregion

        //region 取得编程题（企业题库+自定义题库）
        List<StudentCourseExercises4ProgramDetailVO> programExercisesList = new ArrayList<>();
        List<StudentCourseExercisesDetailEntity> programEntityList =
                studentCourseExercisesDetailMapper.searchProgramList(courseExercisesID);
        if (!programEntityList.isEmpty()) {
            for (StudentCourseExercisesDetailEntity programExercisesEntity : programEntityList) {
                StudentCourseExercises4ProgramDetailVO programExercisesModel = new StudentCourseExercises4ProgramDetailVO();
                ObjectConvertUtils.toBean(programExercisesEntity, programExercisesModel);
                //region todo 取得参考答案的地址（企业题库）或答案（自定义题库）
//                if (programExercisesEntity.getExercisesSourceType() == ExercisesSourceTypeConstant.COMPANY) {
//                    //从企业题库从取得题目参考代码的地址
//                } else {
//                    //从自定义题库中取得题库的参考答案
//                }
                //endregion

                //region 取得最近一次的代码批改结果
                if (programExercisesEntity.getCorrectResult().equals(ExercisesStatusConstant.Reviewed) ||
                        programExercisesEntity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                    StudentCourseExercisesProgramReviewDetailEntity programReviewEntity =
                            programReviewDetailMapper.searchLatest(programExercisesEntity.getCourseExercisesID(),
                                    programExercisesEntity.getCourseExercisesDetailID());
                    if (programReviewEntity == null) {
                        throw new Exception(String.format("Can't find program review data. courseExercisesID: %s, courseExercisesDetailID: %s",
                                programExercisesEntity.getCourseExercisesID(),
                                programExercisesEntity.getCourseExercisesDetailID()));
                    }
                    programExercisesModel.setCompilationResult(programReviewEntity.getCompilationResult());
                    programExercisesModel.setRunResult(programReviewEntity.getRunResult());
                    programExercisesModel.setCodeStandardResult(programReviewEntity.getCodeStandardResult());
                    programExercisesModel.setReviewResult(programReviewEntity.getReviewResult());
                    programExercisesModel.setReviewMemo(programReviewEntity.getReviewMemo());
                }
                //endregion

                //region 取得学生提交的代码地址
                if (!courseExercisesEntity.getDataStatus().equals(ExercisesStatusConstant.Pending)) {
                    StudentCourseExercisesProgramAnswerEntity answerEntity =
                            programAnswerMapper.searchLatestAnswer(courseExercisesID, programExercisesEntity.getCourseExercisesDetailID());
                    if(answerEntity == null) {
                        throw new Exception(String.format("Can't find program answer, courseExercisesID: %s, courseExercisesDetailID: %s",
                                courseExercisesID, programExercisesEntity.getCourseExercisesDetailID()));
                    }
                    programExercisesModel.setSubmitSourceCodeUrl(answerEntity.getSourceCodeUrl());
                }
                //endregion

                programExercisesList.add(programExercisesModel);
            }
            model.setProgramExercisesList(programExercisesList);
        }
        //endregion

        return model;
    }

    private List<ExerciseWarehouseKnowledgeChoiceQuestionEntity> findCompanyChoiceExercises(int technologyID, int knowledgeID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return companyChoiceExercisesMapper.searchList(
                technologyID,
                knowledgeID,
                DataStatusConstant.ACTIVE,
                startIndex,
                pageSize);
    }

    private List<ExerciseWarehouseKnowledgeBlankQuestionEntity> findCompanyBlankExercises(int technologyID, int knowledgeID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return companyBlankExercisesMapper.searchList(
                technologyID,
                knowledgeID,
                DataStatusConstant.ACTIVE,
                startIndex,
                pageSize);
    }

    private List<TechnologyKnowledgeExercisesEntity> findCompanyProgramExercises(int technologyID, int knowledgeID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return companyProgramExercisesMapper.searchList(
                startIndex,
                pageSize,
                technologyID,
                knowledgeID);
    }


    private List<UniversityExerciseKnowledgeChoiceEntity> findSelfChoiceExercises(int technologyID, int knowledgeID, int teacherID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return customChoiceExercisesMapper.searchList(
                technologyID,
                knowledgeID,
                teacherID,
                startIndex,
                pageSize);
    }

    private List<UniversityExerciseKnowledgeBlankEntity> findSelfBlankExercises(int technologyID, int knowledgeID, int teacherID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return customBlankExercisesMapper.searchList(
                technologyID,
                knowledgeID,
                teacherID,
                startIndex,
                pageSize);
    }

    private List<UniversityExerciseKnowledgeProgramEntity> findSelfProgramExercises(int technologyID, int knowledgeID, int teacherID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return customProgramExercisesMapper.searchList(
                technologyID,
                knowledgeID,
                teacherID,
                startIndex,
                pageSize);
    }


    private List<UniversityExerciseKnowledgeChoiceEntity> findOtherChoiceExercises(int technologyID, int knowledgeID, int teacherID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return customChoiceExercisesMapper.searchOtherList(
                technologyID,
                knowledgeID,
                teacherID,
                startIndex,
                pageSize);
    }

    private List<UniversityExerciseKnowledgeBlankEntity> findOtherBlankExercises(int technologyID, int knowledgeID, int teacherID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return customBlankExercisesMapper.searchOtherList(
                technologyID,
                knowledgeID,
                teacherID,
                startIndex,
                pageSize);
    }

    private List<UniversityExerciseKnowledgeProgramEntity> findOtherProgramExercises(int technologyID, int knowledgeID, int teacherID) {
        final int startIndex = 0;
        final int pageSize = 9999;
        return customProgramExercisesMapper.searchOtherList(
                technologyID,
                knowledgeID,
                teacherID,
                startIndex,
                pageSize);
    }

    private List<StudentCourseExercisesDetailEntity> mergeChoiceExercises(int courseExercisesID,
                                                                    List<ExerciseWarehouseKnowledgeChoiceQuestionEntity> companyChoiceList,
                                                                    List<UniversityExerciseKnowledgeChoiceEntity> selfChoiceList,
                                                                    List<UniversityExerciseKnowledgeChoiceEntity> otherChoiceList) {
        List<StudentCourseExercisesDetailEntity> entityList = new ArrayList<>();
        if (!companyChoiceList.isEmpty()) {
            for (ExerciseWarehouseKnowledgeChoiceQuestionEntity companyChoice : companyChoiceList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(companyChoice.getTechnologyID());
                entity.setKnowledgeID(companyChoice.getKnowledgeID());
                entity.setExercisesID(companyChoice.getExercisesID());
                switch (companyChoice.getExercisesType()) {
                    case "S":
                        entity.setExercisesType(0);
                        break;
                    case "M":
                        entity.setExercisesType(1);
                        break;
                }
                entity.setExercisesSourceType(0);
                entityList.add(entity);
            }
        }

        if (!selfChoiceList.isEmpty()) {
            for (UniversityExerciseKnowledgeChoiceEntity selfChoice : selfChoiceList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(selfChoice.getTechnologyID());
                entity.setKnowledgeID(selfChoice.getKnowledgeID());
                entity.setExercisesID(selfChoice.getExercisesID());
                switch (selfChoice.getExercisesType()) {
                    case "S":
                        entity.setExercisesType(0);
                        break;
                    case "M":
                        entity.setExercisesType(1);
                        break;
                }
                entity.setExercisesSourceType(1);
                entityList.add(entity);
            }
        }

        if (!otherChoiceList.isEmpty()) {
            for (UniversityExerciseKnowledgeChoiceEntity otherChoice : otherChoiceList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(otherChoice.getTechnologyID());
                entity.setKnowledgeID(otherChoice.getKnowledgeID());
                entity.setExercisesID(otherChoice.getExercisesID());
                switch (otherChoice.getExercisesType()) {
                    case "S":
                        entity.setExercisesType(0);
                        break;
                    case "M":
                        entity.setExercisesType(1);
                        break;
                }
                entity.setExercisesSourceType(1);
                entityList.add(entity);
            }
        }

        return entityList;
    }

    private List<StudentCourseExercisesDetailEntity> mergeBlankExercises(int courseExercisesID,
                                                                   List<ExerciseWarehouseKnowledgeBlankQuestionEntity> companyBlankList,
                                                                   List<UniversityExerciseKnowledgeBlankEntity> selfBlankList,
                                                                   List<UniversityExerciseKnowledgeBlankEntity> otherBlankList) {
        List<StudentCourseExercisesDetailEntity> entityList = new ArrayList<>();
        if (!companyBlankList.isEmpty()) {
            for (ExerciseWarehouseKnowledgeBlankQuestionEntity companyBlank : companyBlankList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(companyBlank.getTechnologyID());
                entity.setKnowledgeID(companyBlank.getKnowledgeID());
                entity.setExercisesID(companyBlank.getExercisesID());
                entity.setExercisesType(2);
                entity.setExercisesSourceType(0);
                entityList.add(entity);
            }
        }
        if (!selfBlankList.isEmpty()) {
            for (UniversityExerciseKnowledgeBlankEntity selfBlank : selfBlankList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(selfBlank.getTechnologyID());
                entity.setKnowledgeID(selfBlank.getKnowledgeID());
                entity.setExercisesID(selfBlank.getExercisesID());
                entity.setExercisesType(2);
                entity.setExercisesSourceType(1);
                entityList.add(entity);
            }
        }
        if (!otherBlankList.isEmpty()) {
            for (UniversityExerciseKnowledgeBlankEntity otherBlank : otherBlankList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(otherBlank.getTechnologyID());
                entity.setKnowledgeID(otherBlank.getKnowledgeID());
                entity.setExercisesID(otherBlank.getExercisesID());
                entity.setExercisesType(2);
                entity.setExercisesSourceType(1);
                entityList.add(entity);
            }
        }
        return entityList;
    }

    private List<StudentCourseExercisesDetailEntity> mergeProgramExercises(int courseExercisesID,
                                                                     List<TechnologyKnowledgeExercisesEntity> companyProgramList,
                                                                     List<UniversityExerciseKnowledgeProgramEntity> selfProgramList,
                                                                     List<UniversityExerciseKnowledgeProgramEntity> otherProgramList) {
        List<StudentCourseExercisesDetailEntity> entityList = new ArrayList<>();
        if (!companyProgramList.isEmpty()) {
            for (TechnologyKnowledgeExercisesEntity companyProgram : companyProgramList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(companyProgram.getTechnologyID());
                entity.setKnowledgeID(companyProgram.getKnowledgeID());
                entity.setExercisesID(companyProgram.getExercisesID());
                entity.setExercisesType(3);
                entity.setExercisesSourceType(0);
                entityList.add(entity);
            }
        }
        if (!selfProgramList.isEmpty()) {
            for (UniversityExerciseKnowledgeProgramEntity selfProgram : selfProgramList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(selfProgram.getTechnologyID());
                entity.setKnowledgeID(selfProgram.getKnowledgeID());
                entity.setExercisesID(selfProgram.getExercisesID());
                entity.setExercisesType(3);
                entity.setExercisesSourceType(1);
                entityList.add(entity);
            }
        }
        if (!otherProgramList.isEmpty()) {
            for (UniversityExerciseKnowledgeProgramEntity otherProgram : otherProgramList) {
                StudentCourseExercisesDetailEntity entity = new StudentCourseExercisesDetailEntity();
                entity.setCourseExercisesID(courseExercisesID);
                entity.setTechnologyID(otherProgram.getTechnologyID());
                entity.setKnowledgeID(otherProgram.getKnowledgeID());
                entity.setExercisesID(otherProgram.getExercisesID());
                entity.setExercisesType(3);
                entity.setExercisesSourceType(1);
                entityList.add(entity);
            }
        }

        return entityList;
    }

    private int saveCourseClassExercises(int courseID, int courseClass, int studentID, String loginUser) {
        StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
        entity.setCourseID(courseID);
        entity.setCourseClass(courseClass);
        entity.setStudentID(studentID);
        entity.setCreateUser(loginUser);
        entity.setUpdateUser(loginUser);
        return studentCourseExercisesMapper.insert(entity);
    }

    private int saveExercisesDetail4Choice(List<StudentCourseExercisesDetailEntity> choiceEntity, String loginUser) {
        int affectRow = 0;
        for (StudentCourseExercisesDetailEntity entity : choiceEntity) {
            entity.setCreateUser(loginUser);
            entity.setUpdateUser(loginUser);
            affectRow += studentCourseExercisesDetailMapper.insert(entity);
        }
        return affectRow;
    }

    private int saveExercisesDetail4Blank(List<StudentCourseExercisesDetailEntity> blankEntity, String loginUser) {
        int affectRow = 0;
        for (StudentCourseExercisesDetailEntity entity : blankEntity) {
            entity.setCreateUser(loginUser);
            entity.setUpdateUser(loginUser);
            affectRow += studentCourseExercisesDetailMapper.insert(entity);
        }
        return affectRow;
    }

    private int saveExercisesDetail4Program(List<StudentCourseExercisesDetailEntity> programEntity, String loginUser) {
        int affectRow = 0;
        for (StudentCourseExercisesDetailEntity entity : programEntity) {
            entity.setCreateUser(loginUser);
            entity.setUpdateUser(loginUser);
            affectRow += studentCourseExercisesDetailMapper.insert(entity);
        }
        return affectRow;
    }

    @Override
    public UnifiedResponse assign(UniversityStudentExercisesDTO dto) {
        try {
            int affectRow = 0;
            //取得当前课程所有报名的学生列表
            List<CourseSignUpEntity> courseSignUpEntityList = courseSignUpMapper.searchAllCourseSignUpList(
                    dto.getCourseUniversityCode(),
                    dto.getCourseSchoolID(),
                    dto.getCourseID());
            if (courseSignUpEntityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //取得当前节次的所有知识点
            List<CoursePlanEntity> courseKnowledgeList = coursePlanMapper.searchKnowledgeList4CourseClass(dto.getCourseUniversityCode(), dto.getCourseSchoolID(), dto.getCourseID(), dto.getCourseClass());
            if (courseKnowledgeList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //循环已报名的学生列表，给每个学生添加分配的练习题
            for (CourseSignUpEntity courseSignUpEntity : courseSignUpEntityList) {
                //给当前学生下发练习
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(dto.getCourseID());
                entity.setCourseClass(dto.getCourseClass());
                entity.setStudentID(courseSignUpEntity.getStudentID());
                entity.setCreateUser(dto.getLoginUser());
                entity.setUpdateUser(dto.getLoginUser());
                affectRow += studentCourseExercisesMapper.insert(entity);
                //循环当节课每个知识点，将当前节次每个知识点的练习下发给当前学生
                for (CoursePlanEntity coursePlanEntity : courseKnowledgeList) {

                    // region 取得当前知识点企业题库的练习题
                    List<ExerciseWarehouseKnowledgeChoiceQuestionEntity> companyChoiceList = new ArrayList<>();
                    List<ExerciseWarehouseKnowledgeBlankQuestionEntity> companyBlankList = new ArrayList<>();
                    List<TechnologyKnowledgeExercisesEntity> companyProgramList = new ArrayList<>();
                    if (dto.isContainCompanyExercises()) {
                        //取得当前知识点企业题库的选择题
                        companyChoiceList =
                                findCompanyChoiceExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID());
                        //取得当前知识点企业题库的填空题
                        companyBlankList =
                                findCompanyBlankExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID());
                        //取得当前知识点企业题库的编程题
                        companyProgramList =
                                findCompanyProgramExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID());
                    }
                    // endregion

                    // region 取得当前知识点授课教师的练习题
                    List<UniversityExerciseKnowledgeChoiceEntity> selfChoiceList = new ArrayList<>();
                    List<UniversityExerciseKnowledgeBlankEntity> selfBlankList = new ArrayList<>();
                    List<UniversityExerciseKnowledgeProgramEntity> selfProgramList = new ArrayList<>();
                    if (dto.isContainSelfExercises()) {
                        //取得当前知识点授课教师题库的选择题
                        selfChoiceList =
                                findSelfChoiceExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID(),
                                        dto.getTeacherID());

                        //取得当前知识点授课教师题库的填空题
                        selfBlankList =
                                findSelfBlankExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID(),
                                        dto.getTeacherID());
                        //取得当前知识点授课教师题库的编程题
                        selfProgramList =
                                findSelfProgramExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID(),
                                        dto.getTeacherID());
                    }
                    // endregion

                    // region 取得当前知识点其他教师的练习题
                    List<UniversityExerciseKnowledgeChoiceEntity> otherChoiceList = new ArrayList<>();
                    List<UniversityExerciseKnowledgeBlankEntity> otherBlankList = new ArrayList<>();
                    List<UniversityExerciseKnowledgeProgramEntity> otherProgramList = new ArrayList<>();
                    if (dto.isContainOtherExercises()) {
                        //取得当前知识点其他教师题库的选择题
                        otherChoiceList =
                                findOtherChoiceExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID(),
                                        dto.getTeacherID());

                        //取得当前知识点其他教师题库的填空题
                        otherBlankList =
                                findOtherBlankExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID(),
                                        dto.getTeacherID());
                        //取得当前知识点其他教师题库的编程题
                        otherProgramList =
                                findOtherProgramExercises(
                                        coursePlanEntity.getTechnologyID(),
                                        coursePlanEntity.getKnowledgeID(),
                                        dto.getTeacherID());
                    }
                    // endregion

                    // region 合并所有选择题（企业题库、授课教师题库、其他教师题库）
                    List<StudentCourseExercisesDetailEntity> courseClassChoiceEntityList =
                            mergeChoiceExercises(
                                    entity.getCourseExercisesID(),
                                    companyChoiceList,
                                    selfChoiceList,
                                    otherChoiceList);
                    // endregion

                    // region 合并所有填空题（企业题库、授课教师题库、其他教师题库）
                    List<StudentCourseExercisesDetailEntity> courseClassBlankEntityList =
                            mergeBlankExercises(
                                    entity.getCourseExercisesID(),
                                    companyBlankList,
                                    selfBlankList,
                                    otherBlankList);
                    // endregion

                    // region 合并所有编程题（企业题库、授课教师题库、其他教师题库）
                    List<StudentCourseExercisesDetailEntity> courseClassProgramEntityList =
                            mergeProgramExercises(
                                    entity.getCourseExercisesID(),
                                    companyProgramList,
                                    selfProgramList,
                                    otherProgramList);
                    // endregion

                    // region 根据设置的最大值，随机获取当前知识点的选择题
                    List<StudentCourseExercisesDetailEntity> assignChoiceEntityList =
                            getRandomAssignTaskList(dto.getMaxChoiceCount(), courseClassChoiceEntityList);

                    // endregion

                    // region 根据设置的最大值，随机获取当前知识点的填空题
                    List<StudentCourseExercisesDetailEntity> assignBlankEntityList =
                            getRandomAssignTaskList(dto.getMaxBlankCount(), courseClassBlankEntityList);
                    // endregion

                    // region 根据设置的最大值，随机获取当前知识点的编程题
                    List<StudentCourseExercisesDetailEntity> assignProgramEntityList =
                            getRandomAssignTaskList(dto.getMaxProgramCount(), courseClassProgramEntityList);
                    // endregion

                    // region 根据随机取得的知识点的选择题，将其分配给当前学生
                    affectRow += saveExercisesDetail4Choice(assignChoiceEntityList, dto.getLoginUser());
                    // endregion

                    // region 根据随机取得的知识点的填空题，将其分配给当前学生
                    affectRow += saveExercisesDetail4Blank(assignBlankEntityList, dto.getLoginUser());
                    // endregion

                    // region 根据随机取得的知识点的编程题，将其分配给当前学生
                    affectRow += saveExercisesDetail4Program(assignProgramEntityList, dto.getLoginUser());
                    // endregion
                }
            }

            //更新当前课程对应的节次状态为"F"
            CoursePlanEntity coursePlanEntity = new CoursePlanEntity();
            coursePlanEntity.setUniversityCode(dto.getCourseUniversityCode());
            coursePlanEntity.setSchoolID(dto.getCourseSchoolID());
            coursePlanEntity.setCourseID(dto.getCourseID());
            coursePlanEntity.setCourseClass(dto.getCourseClass());
            coursePlanEntity.setDataStatus(CoursePlanStatus.Finished);
            coursePlanEntity.setUpdateUser(dto.getLoginUser());
            affectRow += coursePlanMapper.updateDataStatus(coursePlanEntity);

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse markCourseExercises(CourseExercisesPaperDTO dto) {
        try {
            int affectRow = 0;
            //region 1.先取得当前试卷的详细习题以及每个习题的正确答案
            StudentCourseExercisesVO courseExercisesVO = getCourseExercisesModel(dto.getCourseExercisesID());
            if (courseExercisesVO == null) {
                throw new Exception(String.format("Can't find course exercises by CourseExercisesID: %s", dto.getCourseExercisesID()));
            }
            //endregion

            //region 2.取得学生对当前试卷答题的内容
            List<StudentCourseExercisesChoiceAnswerEntity> singleChoiceAnswerEntityList = getCourseSingleChoiceExercisesAnswerList(dto);
            List<StudentCourseExercisesChoiceAnswerEntity> multipleChoiceAnswerEntityList = getCourseMultipleChoiceExercisesAnswerList(dto);
            List<StudentCourseExercisesBlankAnswerEntity> blankAnswerEntityList = getCourseBlankExercisesAnswerList(dto);
            List<StudentCourseExercisesProgramAnswerEntity> programAnswerEntityList = getCourseProgramExercisesAnswerList(dto);
            //endregion

            //region 3.将学生测试卷的答题信息保存到数据库
            affectRow += saveChoiceAnswer(singleChoiceAnswerEntityList);
            affectRow += saveChoiceAnswer(multipleChoiceAnswerEntityList);
            affectRow += saveBlankAnswer(blankAnswerEntityList);
            affectRow += saveProgramAnswer(programAnswerEntityList);
            //endregion

            //region 4.对学生测试卷的答题信息与正确答案进行比较批改，之后将更新习题的批改结果并将批改记录保存到数据库（单选、多选、填空）
            affectRow += correctSingleChoiceAnswer(courseExercisesVO, singleChoiceAnswerEntityList);
            affectRow += correctMultipleChoiceAnswer(courseExercisesVO, multipleChoiceAnswerEntityList);
            affectRow += correctBlankAnswer(courseExercisesVO, blankAnswerEntityList);
            affectRow += changeProgramStatusToWaiting(programAnswerEntityList);
            //endregion

            //region 5.对学生测试卷的批改结果进行更新(批改中)
            if (affectRow > 0) {
                // String courseExercisesChangeStatus = getCourseExercisesChangeStatus(dto.getCourseExercisesID());
                affectRow += changeCourseExercisesStatus(
                        dto.getStudentID(),
                        dto.getCourseID(),
                        dto.getCourseClass(),
                        dto.getCourseExercisesID(),
                        ExercisesStatusConstant.Correcting);
            }
            //endregion
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private String getCourseExercisesChangeStatus(int courseExercisesID) throws Exception {
        StudentCourseExercisesVO model = getCourseExercisesModel(courseExercisesID);
        if (model == null) {
            throw new Exception(String.format("Can't find course exercises, courseExercisesID: %s", courseExercisesID));
        }
        if (model.getDataStatus().equals(ExercisesStatusConstant.Pending)) {
            return ExercisesStatusConstant.Pending;
        }
        List<StudentCourseExercises4SingleChoiceDetailVO> singleChoiceExercisesList =
                model.getSingleChoiceExercisesList();
        List<StudentCourseExercises4MultipleChoiceDetailVO> multipleChoiceExercisesList =
                model.getMultipleChoiceExercisesList();
        List<StudentCourseExercises4BlankDetailVO> blankExercisesList =
                model.getBlankExercisesList();
        List<StudentCourseExercises4ProgramDetailVO> programExercisesList =
                model.getProgramExercisesList();

        if (!singleChoiceExercisesList.isEmpty()) {
            for (StudentCourseExercises4SingleChoiceDetailVO vo : singleChoiceExercisesList) {
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.Pending)) {
                    return ExercisesStatusConstant.Pending;
                }
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.No)) {
                    return ExercisesStatusConstant.Reviewed;
                }
            }
        }

        if (!multipleChoiceExercisesList.isEmpty()) {
            for (StudentCourseExercises4MultipleChoiceDetailVO vo : multipleChoiceExercisesList) {
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.Pending)) {
                    return ExercisesStatusConstant.Pending;
                }
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.No)) {
                    return ExercisesStatusConstant.Reviewed;
                }
            }
        }

        if (!blankExercisesList.isEmpty()) {
            for (StudentCourseExercises4BlankDetailVO vo : blankExercisesList) {
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.Pending)) {
                    return ExercisesStatusConstant.Pending;
                }
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.No)) {
                    return ExercisesStatusConstant.Reviewed;
                }
            }
        }

        boolean allProgramExercisesReviewedPass = true;
        if (!programExercisesList.isEmpty()) {
            for (StudentCourseExercises4ProgramDetailVO vo : programExercisesList) {
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.Waiting)) {
                    return ExercisesStatusConstant.Correcting;
                }
                if (vo.getCorrectResult().equals(ExercisesStatusConstant.Reviewed)) {
                    allProgramExercisesReviewedPass = false;
                }
            }
            if (!allProgramExercisesReviewedPass) {
                return ExercisesStatusConstant.Reviewed;
            }
        }
        return ExercisesStatusConstant.Pass;
    }

    @Override
    public UnifiedResponse correctProgramAnswer(CourseProgramExercisesMarkDTO dto) {
        try {
            int affectRow = 0;
            List<CodeStandardEntity> codeStandardEntityList =
                    JsonUtils.deserializationToObject(dto.getCodeStandardErrorListJson(), CodeStandardEntity.class);

            //region 添加练习批改记录
            boolean isRight = dto.getReviewResult().equals(ExercisesStatusConstant.Pass);
            StudentCourseExercisesReviewEntity reviewEntity = new StudentCourseExercisesReviewEntity();
            affectRow += saveCourseExercisesReview(
                    dto.getStudentID(),
                    dto.getCourseID(),
                    dto.getCourseClass(),
                    dto.getCourseExercisesID(),
                    dto.getCourseExercisesDetailID(),
                    isRight,
                    reviewEntity);
            //endregion

            //region 添加编程题批改项记录
            affectRow += saveCourseProgramReview(reviewEntity.getReviewID(), dto);
            //endregion

            //region 添加编程题代码规范问题项记录
            affectRow += saveCourseProgramCodeReview(reviewEntity.getReviewID(), dto, codeStandardEntityList);
            //endregion

            //region 更新练习题状态 (通过：Y ｜ 未通过：R)
            String courseExercisesDetailStatus = dto.getReviewResult().equals(ExercisesStatusConstant.Pass) ?
                    ExercisesStatusConstant.Yes:
                    ExercisesStatusConstant.Reviewed;
            affectRow += changeCourseExercisesDetailStatus(dto.getCourseExercisesDetailID(), courseExercisesDetailStatus);
            //endregion

            //region 更新练习卷状态
            String courseExercisesChangeStatus = getCourseExercisesChangeStatus(dto.getCourseExercisesID());
            affectRow += changeCourseExercisesStatus(
                    dto.getStudentID(),
                    dto.getCourseID(),
                    dto.getCourseClass(),
                    dto.getCourseExercisesID(),
                    courseExercisesChangeStatus);
            //endregion

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private int saveCourseProgramReview(int reviewID, CourseProgramExercisesMarkDTO dto) {
        StudentCourseExercisesProgramReviewDetailEntity entity =
                new StudentCourseExercisesProgramReviewDetailEntity();
        entity.setReviewID(reviewID);
        entity.setStudentID(dto.getStudentID());
        entity.setCourseID(dto.getCourseID());
        entity.setCourseClass(dto.getCourseClass());
        entity.setCourseExercisesID(dto.getCourseExercisesID());
        entity.setCourseExercisesDetailID(dto.getCourseExercisesDetailID());
        entity.setCompilationResult(dto.getCompilationResult());
        entity.setRunResult(dto.getRunResult());
        entity.setCodeStandardResult(dto.getCodeStandardResult());
        entity.setReviewResult(dto.getReviewResult());
        entity.setReviewMemo(dto.getReviewMemo());
        entity.setCreateUser(dto.getLoginUser());
        entity.setUpdateUser(dto.getLoginUser());
        return programReviewDetailMapper.insert(entity);
    }

    private int saveCourseProgramCodeReview(int reviewID, CourseProgramExercisesMarkDTO dto, List<CodeStandardEntity> entityList) {
        int affectRow = 0;
        if (entityList.isEmpty()) {
            return affectRow;
        }
        for (CodeStandardEntity entity : entityList) {
            StudentCourseExercisesCodeReviewDetailEntity codeReviewEntity =
                    new StudentCourseExercisesCodeReviewDetailEntity();
            codeReviewEntity.setReviewID(reviewID);
            codeReviewEntity.setStudentID(dto.getStudentID());
            codeReviewEntity.setCourseID(dto.getCourseID());
            codeReviewEntity.setCourseClass(dto.getCourseClass());
            codeReviewEntity.setCourseExercisesID(dto.getCourseExercisesID());
            codeReviewEntity.setCourseExercisesDetailID(dto.getCourseExercisesDetailID());
            codeReviewEntity.setLanguageID(entity.getLanguageID());
            codeReviewEntity.setCodeStandardID(entity.getCodeStandardID());
            codeReviewEntity.setCreateUser(dto.getLoginUser());
            codeReviewEntity.setUpdateUser(dto.getLoginUser());
            affectRow += codeReviewDetailMapper.insert(codeReviewEntity);
        }
        return affectRow;
    }

    private int changeCourseExercisesStatus(int studentID,
                                            int courseID,
                                            int courseClass,
                                            int courseExercisesID,
                                            String dataStatus) {
        StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
        entity.setStudentID(studentID);
        entity.setCourseID(courseID);
        entity.setCourseClass(courseClass);
        entity.setCourseExercisesID(courseExercisesID);
        entity.setDataStatus(dataStatus);
        entity.setCreateUser(SYS_ADMIN_ID);
        entity.setUpdateUser(SYS_ADMIN_ID);
        return studentCourseExercisesMapper.updateDataStatus(entity);
    }

    private int changeCourseExercisesDetailStatus(int courseExercisesDetailID, String dataStatus) {
        StudentCourseExercisesDetailEntity exercisesDetailEntity = new StudentCourseExercisesDetailEntity();
        exercisesDetailEntity.setCourseExercisesDetailID(courseExercisesDetailID);
        exercisesDetailEntity.setCorrectResult(dataStatus);
        exercisesDetailEntity.setUpdateUser(SYS_ADMIN_ID);
        return studentCourseExercisesDetailMapper.updateDataStatus(exercisesDetailEntity);
    }

    private int saveCourseExercisesReview(int studentID,
                                          int courseID,
                                          int courseClass,
                                          int courseExercisesID,
                                          int courseExercisesDetailID,
                                          boolean isRight) {
        String dataStatus = isRight ? ExercisesStatusConstant.Yes : ExercisesStatusConstant.No;
        StudentCourseExercisesReviewEntity exercisesReviewEntity = new StudentCourseExercisesReviewEntity();
        exercisesReviewEntity.setStudentID(studentID);
        exercisesReviewEntity.setCourseID(courseID);
        exercisesReviewEntity.setCourseClass(courseClass);
        exercisesReviewEntity.setCourseExercisesID(courseExercisesID);
        exercisesReviewEntity.setCourseExercisesDetailID(courseExercisesDetailID);
        exercisesReviewEntity.setCorrectResult(dataStatus);
        exercisesReviewEntity.setCreateUser(SYS_ADMIN_ID);
        exercisesReviewEntity.setUpdateUser(SYS_ADMIN_ID);
        return exercisesReviewMapper.insert(exercisesReviewEntity);
    }

    private int saveCourseExercisesReview(int studentID,
                                          int courseID,
                                          int courseClass,
                                          int courseExercisesID,
                                          int courseExercisesDetailID,
                                          boolean isRight,
                                          StudentCourseExercisesReviewEntity entity) {
        if (entity == null) {
            return 0;
        }
        String dataStatus = isRight ? ExercisesStatusConstant.Yes : ExercisesStatusConstant.No;
        entity.setStudentID(studentID);
        entity.setCourseID(courseID);
        entity.setCourseClass(courseClass);
        entity.setCourseExercisesID(courseExercisesID);
        entity.setCourseExercisesDetailID(courseExercisesDetailID);
        entity.setCorrectResult(dataStatus);
        entity.setCreateUser(SYS_ADMIN_ID);
        entity.setUpdateUser(SYS_ADMIN_ID);
        return exercisesReviewMapper.insert(entity);
    }

    private int correctSingleChoiceAnswer(StudentCourseExercisesVO courseExercisesVO, List<StudentCourseExercisesChoiceAnswerEntity> answerEntityList) {
        int affectRow = 0;
        List<StudentCourseExercises4SingleChoiceDetailVO> modelList = courseExercisesVO.getSingleChoiceExercisesList();
        //1.单选题批改
        if (answerEntityList.isEmpty() || modelList.isEmpty()) {
            return 0;
        }
        //遍历学生提交的单选题
        for (StudentCourseExercisesChoiceAnswerEntity entity : answerEntityList) {
            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                continue;
            }
            //取得当前习题的正确答案
            int rightOptionID = 0;
            //遍历试卷中的选择题
            for (StudentCourseExercises4SingleChoiceDetailVO model : modelList) {
                if (entity.getCourseExercisesDetailID() == model.getCourseExercisesDetailID()) {
                    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionVO> optionList = model.getOptionList();
                    //遍历练习题选项
                    for (ExerciseWarehouseKnowledgeChoiceQuestionOptionVO option : optionList) {
                        if(option.getRightAnswer()){
                            rightOptionID = option.getOptionID();
                            break;
                        }
                    }
                    boolean isRight = false;
                    //判断习题提交的选项与标准答案的选项是否一致
                    if (Integer.parseInt(entity.getSelectedOption()) == rightOptionID) {
                        isRight = true;
                    }

                    //2.批改结果更新
                    String dataStatus = isRight ? ExercisesStatusConstant.Yes : ExercisesStatusConstant.No;
                    affectRow += changeCourseExercisesDetailStatus(entity.getCourseExercisesDetailID(), dataStatus);

                    //3.保存批改记录
                    affectRow += saveCourseExercisesReview(
                            entity.getStudentID(),
                            entity.getCourseID(),
                            entity.getCourseClass(),
                            entity.getCourseExercisesID(),
                            entity.getCourseExercisesDetailID(),
                            isRight);
                    break;
                }
            }
        }
        return affectRow;
    }

    private int correctMultipleChoiceAnswer(StudentCourseExercisesVO courseExercisesVO, List<StudentCourseExercisesChoiceAnswerEntity> answerEntityList) {
        int affectRow = 0;
        List<StudentCourseExercises4MultipleChoiceDetailVO> modelList = courseExercisesVO.getMultipleChoiceExercisesList();
        //1.多选题批改
        if (answerEntityList.isEmpty() || modelList.isEmpty()) {
            return 0;
        }
        //遍历学生提交的多选题
        for (StudentCourseExercisesChoiceAnswerEntity entity : answerEntityList) {
            //如果当前习题之前已经批改且正确，则不再需要批改
            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                continue;
            }
            String[] answerOptionTempList = entity.getSelectedOption().split(",");
            List<Integer> answerOptionList = new ArrayList<>();
            for (String option : answerOptionTempList) {
                answerOptionList.add(Integer.parseInt(option));
            }
            answerOptionList.sort(Comparator.naturalOrder()); //将学生提交的多选题选项进行排序

            //取得当前习题的正确答案
            List<Integer> rightOptionList = new ArrayList<>();
            //遍历试卷中的多择题
            for (StudentCourseExercises4MultipleChoiceDetailVO model : modelList) {
                if (entity.getCourseExercisesDetailID() == model.getCourseExercisesDetailID()) {
                    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionVO> optionList = model.getOptionList();
                    //遍历练习题选项
                    for (ExerciseWarehouseKnowledgeChoiceQuestionOptionVO option : optionList) {
                        if(option.getRightAnswer()) {
                            rightOptionList.add(option.getOptionID());
                        }
                    }
                    rightOptionList.sort(Comparator.naturalOrder()); //将正确的选项进行排序
                    boolean isRight = false;
                    String answerOptionListStr = answerOptionList.toString();
                    String rightOptionListStr = rightOptionList.toString();
                    //判断习题提交的选项与标准答案的选项是否一致
                    if (answerOptionListStr.equals(rightOptionListStr)) {
                        isRight = true;
                    }

                    //2.批改结果更新
                    String dataStatus = isRight ? ExercisesStatusConstant.Yes : ExercisesStatusConstant.No;
                    affectRow += changeCourseExercisesDetailStatus(entity.getCourseExercisesDetailID(), dataStatus);

                    //3.保存批改记录
                    affectRow += saveCourseExercisesReview(
                            entity.getStudentID(),
                            entity.getCourseID(),
                            entity.getCourseClass(),
                            entity.getCourseExercisesID(),
                            entity.getCourseExercisesDetailID(),
                            isRight);
                }
            }
        }

        return affectRow;
    }

    private int correctBlankAnswer(StudentCourseExercisesVO courseExercisesVO, List<StudentCourseExercisesBlankAnswerEntity> answerEntityList) {
        int affectRow = 0;
        List<StudentCourseExercises4BlankDetailVO> modelList = courseExercisesVO.getBlankExercisesList();
        //1.填空题批改
        if (answerEntityList.isEmpty() || modelList.isEmpty()) {
            return 0;
        }
        //遍历学生提交的单选题
        for (StudentCourseExercisesBlankAnswerEntity entity : answerEntityList) {
            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                continue;
            }
            //取得当前习题的正确答案
            String rightAnswer = "";
            //遍历试卷中的填空题
            for (StudentCourseExercises4BlankDetailVO model : modelList) {
                if (entity.getCourseExercisesDetailID() == model.getCourseExercisesDetailID()) {
                    rightAnswer = model.getRightAnswer();
                    boolean isRight = false;
                    //判断习题提交的选项与标准答案的选项是否一致
                    if (entity.getFillInContent().equals(rightAnswer)) {
                        isRight = true;
                    }

                    //2.批改结果更新
                    String dataStatus = isRight ? ExercisesStatusConstant.Yes : ExercisesStatusConstant.No;
                    affectRow += changeCourseExercisesDetailStatus(entity.getCourseExercisesDetailID(), dataStatus);

                    //3.保存批改记录
                    affectRow += saveCourseExercisesReview(
                            entity.getStudentID(),
                            entity.getCourseID(),
                            entity.getCourseClass(),
                            entity.getCourseExercisesID(),
                            entity.getCourseExercisesDetailID(),
                            isRight);
                    break;
                }
            }
        }

        return affectRow;
    }

    private int changeProgramStatusToWaiting(List<StudentCourseExercisesProgramAnswerEntity> programAnswerEntityList) {
        int affectRow = 0;
        if (programAnswerEntityList.isEmpty()) {
            return affectRow;
        }
        for (StudentCourseExercisesProgramAnswerEntity entity : programAnswerEntityList) {
            affectRow += changeCourseExercisesDetailStatus(entity.getCourseExercisesDetailID(), ExercisesStatusConstant.Waiting);
        }
        return affectRow;
    }

    private List<StudentCourseExercisesChoiceAnswerEntity> getCourseSingleChoiceExercisesAnswerList(CourseExercisesPaperDTO dto) {
        List<StudentCourseExercisesChoiceAnswerEntity> singleChoiceAnswerEntityList =
                JsonUtils.deserializationToObject(dto.getSingleChoiceListJson(), StudentCourseExercisesChoiceAnswerEntity.class);
        if (singleChoiceAnswerEntityList == null) {
            return null;
        }
        for (StudentCourseExercisesChoiceAnswerEntity entity : singleChoiceAnswerEntityList) {
            entity.setStudentID(dto.getStudentID());
            entity.setCourseID(dto.getCourseID());
            entity.setCourseClass(dto.getCourseClass());
            entity.setCourseExercisesID(dto.getCourseExercisesID());
            entity.setCreateUser(SYS_ADMIN_ID);
            entity.setUpdateUser(SYS_ADMIN_ID);
        }
        return singleChoiceAnswerEntityList;
    }

    private List<StudentCourseExercisesChoiceAnswerEntity> getCourseMultipleChoiceExercisesAnswerList(CourseExercisesPaperDTO dto) {
        List<StudentCourseExercisesChoiceAnswerEntity> multipleChoiceAnswerEntityList =
                JsonUtils.deserializationToObject(dto.getMultipleChoiceListJson(), StudentCourseExercisesChoiceAnswerEntity.class);
        if (multipleChoiceAnswerEntityList == null) {
            return null;
        }
        for (StudentCourseExercisesChoiceAnswerEntity entity : multipleChoiceAnswerEntityList) {
            entity.setStudentID(dto.getStudentID());
            entity.setCourseID(dto.getCourseID());
            entity.setCourseClass(dto.getCourseClass());
            entity.setCourseExercisesID(dto.getCourseExercisesID());
            entity.setCreateUser(SYS_ADMIN_ID);
            entity.setUpdateUser(SYS_ADMIN_ID);
        }
        return multipleChoiceAnswerEntityList;
    }

    private List<StudentCourseExercisesBlankAnswerEntity> getCourseBlankExercisesAnswerList(CourseExercisesPaperDTO dto) {
        List<StudentCourseExercisesBlankAnswerEntity> blankAnswerEntityList =
                JsonUtils.deserializationToObject(dto.getBlankChoiceListJson(), StudentCourseExercisesBlankAnswerEntity.class);
        if (blankAnswerEntityList == null) {
            return null;
        }
        for (StudentCourseExercisesBlankAnswerEntity entity : blankAnswerEntityList) {
            entity.setStudentID(dto.getStudentID());
            entity.setCourseID(dto.getCourseID());
            entity.setCourseClass(dto.getCourseClass());
            entity.setCourseExercisesID(dto.getCourseExercisesID());
            entity.setCreateUser(SYS_ADMIN_ID);
            entity.setUpdateUser(SYS_ADMIN_ID);
        }
        return blankAnswerEntityList;
    }

    private List<StudentCourseExercisesProgramAnswerEntity> getCourseProgramExercisesAnswerList(CourseExercisesPaperDTO dto) {
        List<StudentCourseExercisesProgramAnswerEntity> programAnswerEntityList =
                JsonUtils.deserializationToObject(dto.getProgramChoiceListJson(), StudentCourseExercisesProgramAnswerEntity.class);
        if (programAnswerEntityList == null) {
            return null;
        }
        for (StudentCourseExercisesProgramAnswerEntity entity : programAnswerEntityList) {
            entity.setStudentID(dto.getStudentID());
            entity.setCourseID(dto.getCourseID());
            entity.setCourseClass(dto.getCourseClass());
            entity.setCourseExercisesID(dto.getCourseExercisesID());
            entity.setCreateUser(SYS_ADMIN_ID);
            entity.setUpdateUser(SYS_ADMIN_ID);
        }
        return programAnswerEntityList;
    }

//    private int saveSingleChoiceAnswer(List<StudentCourseExercisesChoiceAnswerEntity> entityList) {
//        int affectRow = 0;
//        if (entityList == null) {
//            return affectRow;
//        }
//        for (StudentCourseExercisesChoiceAnswerEntity entity : entityList) {
//            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
//                continue;
//            }
//            affectRow += choiceAnswerMapper.insert(entity);
//        }
//        return affectRow;
//    }

//    private int saveMultipleChoiceAnswer(List<StudentCourseExercisesChoiceAnswerEntity> entityList) {
//        int affectRow = 0;
//        if (entityList == null) {
//            return affectRow;
//        }
//        for (StudentCourseExercisesChoiceAnswerEntity entity : entityList) {
//            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
//                continue;
//            }
//            affectRow += choiceAnswerMapper.insert(entity);
//        }
//        return affectRow;
//    }

    private int saveChoiceAnswer(List<StudentCourseExercisesChoiceAnswerEntity> entityList) {
        int affectRow = 0;
        if (entityList == null) {
            return affectRow;
        }
        for (StudentCourseExercisesChoiceAnswerEntity entity : entityList) {
            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                continue;
            }
            affectRow += choiceAnswerMapper.insert(entity);
        }
        return affectRow;
    }

    private int saveBlankAnswer(List<StudentCourseExercisesBlankAnswerEntity> entityList) {
        int affectRow = 0;
        if (entityList == null) {
            return affectRow;
        }
        for (StudentCourseExercisesBlankAnswerEntity entity : entityList) {
            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                continue;
            }
            affectRow += blankAnswerMapper.insert(entity);
        }
        return affectRow;
    }

    private int saveProgramAnswer(List<StudentCourseExercisesProgramAnswerEntity> entityList) {
        int affectRow = 0;
        if (entityList == null) {
            return affectRow;
        }
        for (StudentCourseExercisesProgramAnswerEntity entity : entityList) {
            if (entity.getCorrectResult().equals(ExercisesStatusConstant.Yes)) {
                continue;
            }
            affectRow += programAnswerMapper.insert(entity);
        }
        return affectRow;
    }

    @Override
    public UnifiedResponse add(UniversityStudentExercisesDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse change(UniversityStudentExercisesDTO dto) {
        try {
            UniversityStudentExercisesEntity entity = new UniversityStudentExercisesEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = universityStudentExercisesMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(UniversityStudentExercisesDTO dto) {
        return null;
    }

    private List<StudentCourseExercisesDetailEntity> getRandomAssignTaskList(int assignCount, List<StudentCourseExercisesDetailEntity> taskEntityList) {
        List<StudentCourseExercisesDetailEntity> assignTaskList = new ArrayList<>();
        if (assignCount >= taskEntityList.size()) {
            assignTaskList.addAll(taskEntityList);
        } else {
            Random random = new Random();
            List<Integer> randomTaskIndexList = new ArrayList<>();

            int min = 0;
            int max = taskEntityList.size();

            while (randomTaskIndexList.size() < assignCount) {
                int value = random.nextInt(max) % (max - min + 1) + min;
                if (randomTaskIndexList.size() != 0) {
                    boolean isExists = randomTaskIndexList.contains(value);
                    if (isExists) {
                        continue;
                    }
                }
                randomTaskIndexList.add(value);
            }

            for (int randomIndex : randomTaskIndexList) {
                assignTaskList.add(taskEntityList.get(randomIndex));
            }
        }

        return assignTaskList;
    }

    ;
}
