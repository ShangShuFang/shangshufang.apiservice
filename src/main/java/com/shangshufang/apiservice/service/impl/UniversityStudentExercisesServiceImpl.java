package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.*;
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
import java.util.List;
import java.util.Random;

@Service
public class UniversityStudentExercisesServiceImpl implements UniversityStudentExercisesService {
    @Autowired
    private TechnologyKnowledgeExercisesMapper knowledgeExercisesMapper;
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

    private final Logger logger = LogManager.getLogger(UniversityStudentExercisesServiceImpl.class);

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
    public UnifiedResponse findList4Student(int pageNumber, int pageSize, int studentID, int courseID, String dataStatus, String studentName, boolean isSelf) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            studentName = studentName.equals(ParameterConstant.NO_PARAMETER) ? null : studentName;
            int totalCount = universityStudentExercisesMapper.searchTotalCount4Student(studentID, courseID, dataStatus, studentName, isSelf);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList = universityStudentExercisesMapper.searchList4Student(startIndex, pageSize, studentID, courseID, dataStatus, studentName, isSelf);
            for (UniversityStudentExercisesEntity entity : entityList) {
                UniversityStudentExercisesVO model = new UniversityStudentExercisesVO();
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
    public UnifiedResponse findList4Technology(int pageNumber, int pageSize, int universityCode, int schoolID, int studentID, int technologyID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = universityStudentExercisesMapper.searchTotalCount4Technology(universityCode, schoolID, studentID, technologyID, dataStatus);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList = universityStudentExercisesMapper.searchList4Technology(startIndex, pageSize, universityCode, schoolID, studentID, technologyID, dataStatus);
            for (UniversityStudentExercisesEntity entity : entityList) {
                UniversityStudentExercisesVO model = new UniversityStudentExercisesVO();
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
    public UnifiedResponse findCourseExercisesDetail(int courseExercisesID) {
        try {
            StudentCourseExercisesVO model = new StudentCourseExercisesVO();

            //region 取得练习的基本信息
            StudentCourseExercisesEntity courseExercisesEntity =
                    studentCourseExercisesMapper.search(courseExercisesID);
            if (courseExercisesEntity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(courseExercisesEntity, model);
            //endregion

            //region 取得选择题（企业题库+自定义题库）
            List<StudentCourseExercisesDetailVO> choiceExercisesList = new ArrayList<>();
            List<StudentCourseExercisesDetailEntity> choiceEntityList =
                    studentCourseExercisesDetailMapper.searchChoiceList(courseExercisesID);
            if (!choiceEntityList.isEmpty()) {
                for (StudentCourseExercisesDetailEntity choiceEntity : choiceEntityList) {
                    if (choiceEntity.getExercisesSourceType() == ExercisesSourceTypeConstant.COMPANY) {
                        List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> companyChoiceOptionEntityList =
                                companyChoiceExercisesOptionMapper.searchList(courseExercisesID);
                        choiceEntity.setChoiceOptionEntityList(companyChoiceOptionEntityList);
                    }
                    if (choiceEntity.getExercisesSourceType() == ExercisesSourceTypeConstant.SELF) {
                        List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> customChoiceOptionEntityList =
                                customChoiceExercisesOptionMapper.searchList(courseExercisesID);
                        choiceEntity.setChoiceOptionEntityList(customChoiceOptionEntityList);
                    }
                }

                for (StudentCourseExercisesDetailEntity choiceEntity : choiceEntityList) {
                    StudentCourseExercisesDetailVO choiceExercisesModel = new StudentCourseExercisesDetailVO();
                    ObjectConvertUtils.toBean(choiceEntity, choiceExercisesModel);

                    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity> optionEntityList = choiceEntity.getChoiceOptionEntityList();
                    List<ExerciseWarehouseKnowledgeChoiceQuestionOptionVO> optionModelList = new ArrayList<>();
                    for (ExerciseWarehouseKnowledgeChoiceQuestionOptionEntity optionEntity : optionEntityList) {
                        optionEntity.setRightAnswer(false);
                        ExerciseWarehouseKnowledgeChoiceQuestionOptionVO optionModel = new ExerciseWarehouseKnowledgeChoiceQuestionOptionVO();
                        ObjectConvertUtils.toBean(optionEntity, optionModel);
                        optionModelList.add(optionModel);
                    }
                    choiceExercisesModel.setOptionList(optionModelList);
                    choiceExercisesList.add(choiceExercisesModel);
                }

                model.setChoiceExercisesList(choiceExercisesList);
            }
            //endregion

            //region 取得填空题（企业题库+自定义题库）
            List<StudentCourseExercisesDetailVO> blankExercisesList = new ArrayList<>();
            List<StudentCourseExercisesDetailEntity> blankEntityList =
                    studentCourseExercisesDetailMapper.searchBlankList(courseExercisesID);
            if (!blankEntityList.isEmpty()) {
                for (StudentCourseExercisesDetailEntity blankExercisesEntity : blankEntityList) {
                    StudentCourseExercisesDetailVO blankExercisesModel = new StudentCourseExercisesDetailVO();
                    ObjectConvertUtils.toBean(blankExercisesEntity, blankExercisesModel);
                    blankExercisesList.add(blankExercisesModel);
                }
                model.setBlankExercisesList(blankExercisesList);
            }
            //endregion

            //region 取得编程题（企业题库+自定义题库）
            List<StudentCourseExercisesDetailVO> programExercisesList = new ArrayList<>();
            List<StudentCourseExercisesDetailEntity> programEntityList =
                    studentCourseExercisesDetailMapper.searchProgramList(courseExercisesID);
            if (!programEntityList.isEmpty()) {
                for (StudentCourseExercisesDetailEntity programExercisesEntity : programEntityList) {
                    StudentCourseExercisesDetailVO programExercisesModel = new StudentCourseExercisesDetailVO();
                    ObjectConvertUtils.toBean(programExercisesEntity, programExercisesModel);
                    programExercisesList.add(programExercisesModel);
                }
                model.setProgramExercisesList(programExercisesList);
            }
            //endregion

            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
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
