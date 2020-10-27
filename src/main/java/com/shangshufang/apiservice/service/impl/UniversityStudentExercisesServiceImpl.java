package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.CourseStatus;
import com.shangshufang.apiservice.constant.DataStatusConstant;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityStudentExercisesDTO;
import com.shangshufang.apiservice.entity.*;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.*;
import com.shangshufang.apiservice.service.UniversityStudentExercisesService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityStudentExercisesVO;
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
    private ExerciseWarehouseKnowledgeBlankQuestionMapper companyBlankExercisesMapper;
    @Autowired
    private TechnologyKnowledgeExercisesMapper companyProgramExercisesMapper;
    @Autowired
    private UniversityExerciseKnowledgeChoiceMapper customChoiceExercisesMapper;
    @Autowired
    private UniversityExerciseKnowledgeBlankMapper customBlankExercisesMapper;
    @Autowired
    private UniversityExerciseKnowledgeProgramMapper customProgramExercisesMapper;
    @Autowired
    private StudentCourseExercisesMapper studentCourseExercisesMapper;

    private final Logger logger = LogManager.getLogger(UniversityStudentExercisesServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID, String dataStatus, String studentName) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            studentName = studentName.equals(ParameterConstant.NO_PARAMETER) ? null : studentName;
            int totalCount = universityStudentExercisesMapper.searchTotalCount(courseUniversityCode, courseSchoolID, courseID, dataStatus, studentName);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList = universityStudentExercisesMapper.searchList(startIndex, pageSize, courseUniversityCode, courseSchoolID, courseID, dataStatus, studentName);
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

    private List<StudentCourseExercisesEntity> mergeChoiceExercises(int courseID,
                                                                    int courseClassID,
                                                                    List<ExerciseWarehouseKnowledgeChoiceQuestionEntity> companyChoiceList,
                                                                    List<UniversityExerciseKnowledgeChoiceEntity> selfChoiceList,
                                                                    List<UniversityExerciseKnowledgeChoiceEntity> otherChoiceList) {
        List<StudentCourseExercisesEntity> entityList = new ArrayList<>();
        if (!companyChoiceList.isEmpty()) {
            for (ExerciseWarehouseKnowledgeChoiceQuestionEntity companyChoice : companyChoiceList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
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
                entity.setExercisesSource(0);
                entityList.add(entity);
            }
        }

        if (!selfChoiceList.isEmpty()) {
            for (UniversityExerciseKnowledgeChoiceEntity selfChoice : selfChoiceList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
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
                entity.setExercisesSource(1);
                entityList.add(entity);
            }
        }

        if (!otherChoiceList.isEmpty()) {
            for (UniversityExerciseKnowledgeChoiceEntity otherChoice : otherChoiceList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
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
                entity.setExercisesSource(1);
                entityList.add(entity);
            }
        }

        return entityList;
    }

    private List<StudentCourseExercisesEntity> mergeBlankExercises(int courseID,
                                                                   int courseClassID,
                                                                   List<ExerciseWarehouseKnowledgeBlankQuestionEntity> companyBlankList,
                                                                   List<UniversityExerciseKnowledgeBlankEntity> selfBlankList,
                                                                   List<UniversityExerciseKnowledgeBlankEntity> otherBlankList) {
        List<StudentCourseExercisesEntity> entityList = new ArrayList<>();
        if (!companyBlankList.isEmpty()) {
            for (ExerciseWarehouseKnowledgeBlankQuestionEntity companyBlank : companyBlankList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
                entity.setTechnologyID(companyBlank.getTechnologyID());
                entity.setKnowledgeID(companyBlank.getKnowledgeID());
                entity.setExercisesID(companyBlank.getExercisesID());
                entity.setExercisesType(2);
                entity.setExercisesSource(0);
                entityList.add(entity);
            }
        }
        if (!selfBlankList.isEmpty()) {
            for (UniversityExerciseKnowledgeBlankEntity selfBlank : selfBlankList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
                entity.setTechnologyID(selfBlank.getTechnologyID());
                entity.setKnowledgeID(selfBlank.getKnowledgeID());
                entity.setExercisesID(selfBlank.getExercisesID());
                entity.setExercisesType(2);
                entity.setExercisesSource(1);
                entityList.add(entity);
            }
        }
        if (!otherBlankList.isEmpty()) {
            for (UniversityExerciseKnowledgeBlankEntity otherBlank : otherBlankList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
                entity.setTechnologyID(otherBlank.getTechnologyID());
                entity.setKnowledgeID(otherBlank.getKnowledgeID());
                entity.setExercisesID(otherBlank.getExercisesID());
                entity.setExercisesType(2);
                entity.setExercisesSource(1);
                entityList.add(entity);
            }
        }
        return entityList;
    }

    private List<StudentCourseExercisesEntity> mergeProgramExercises(int courseID,
                                                                     int courseClassID,
                                                                     List<TechnologyKnowledgeExercisesEntity> companyProgramList,
                                                                     List<UniversityExerciseKnowledgeProgramEntity> selfProgramList,
                                                                     List<UniversityExerciseKnowledgeProgramEntity> otherProgramList) {
        List<StudentCourseExercisesEntity> entityList = new ArrayList<>();
        if (!companyProgramList.isEmpty()) {
            for (TechnologyKnowledgeExercisesEntity companyProgram : companyProgramList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
                entity.setTechnologyID(companyProgram.getTechnologyID());
                entity.setKnowledgeID(companyProgram.getKnowledgeID());
                entity.setExercisesID(companyProgram.getExercisesID());
                entity.setExercisesType(3);
                entity.setExercisesSource(0);
                entityList.add(entity);
            }
        }
        if (!selfProgramList.isEmpty()) {
            for (UniversityExerciseKnowledgeProgramEntity selfProgram : selfProgramList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
                entity.setTechnologyID(selfProgram.getTechnologyID());
                entity.setKnowledgeID(selfProgram.getKnowledgeID());
                entity.setExercisesID(selfProgram.getExercisesID());
                entity.setExercisesType(3);
                entity.setExercisesSource(1);
                entityList.add(entity);
            }
        }
        if (!otherProgramList.isEmpty()) {
            for (UniversityExerciseKnowledgeProgramEntity otherProgram : otherProgramList) {
                StudentCourseExercisesEntity entity = new StudentCourseExercisesEntity();
                entity.setCourseID(courseID);
                entity.setCourseClass(courseClassID);
                entity.setTechnologyID(otherProgram.getTechnologyID());
                entity.setKnowledgeID(otherProgram.getKnowledgeID());
                entity.setExercisesID(otherProgram.getExercisesID());
                entity.setExercisesType(3);
                entity.setExercisesSource(1);
                entityList.add(entity);
            }
        }

        return entityList;
    }

    private int saveChoiceExercises(int studentID, String loginUser, List<StudentCourseExercisesEntity> choiceEntity) {
        int affectRow = 0;
        for (StudentCourseExercisesEntity entity : choiceEntity) {
            entity.setStudentID(studentID);
            entity.setCreateUser(loginUser);
            entity.setUpdateUser(loginUser);
            affectRow += studentCourseExercisesMapper.insert(entity);
        }
        return affectRow;
    }

    private int saveBlankExercises(int studentID, String loginUser, List<StudentCourseExercisesEntity> blankEntity) {
        int affectRow = 0;
        for (StudentCourseExercisesEntity entity : blankEntity) {
            entity.setStudentID(studentID);
            entity.setCreateUser(loginUser);
            entity.setUpdateUser(loginUser);
            affectRow += studentCourseExercisesMapper.insert(entity);
        }
        return affectRow;
    }

    private int saveProgramExercises(int studentID, String loginUser, List<StudentCourseExercisesEntity> programEntity) {
        int affectRow = 0;
        for (StudentCourseExercisesEntity entity : programEntity) {
            entity.setStudentID(studentID);
            entity.setCreateUser(loginUser);
            entity.setUpdateUser(loginUser);
            affectRow += studentCourseExercisesMapper.insert(entity);
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
                //循环当节课每个知识点
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
                    List<StudentCourseExercisesEntity> courseClassChoiceEntityList =
                            mergeChoiceExercises(
                                    dto.getCourseID(),
                                    dto.getCourseClass(),
                                    companyChoiceList,
                                    selfChoiceList,
                                    otherChoiceList);
                    // endregion

                    // region 合并所有填空题（企业题库、授课教师题库、其他教师题库）
                    List<StudentCourseExercisesEntity> courseClassBlankEntityList =
                            mergeBlankExercises(
                                    dto.getCourseID(),
                                    dto.getCourseClass(),
                                    companyBlankList,
                                    selfBlankList,
                                    otherBlankList);
                    // endregion

                    // region 合并所有编程题（企业题库、授课教师题库、其他教师题库）
                    List<StudentCourseExercisesEntity> courseClassProgramEntityList =
                            mergeProgramExercises(
                                    dto.getCourseID(),
                                    dto.getCourseClass(),
                                    companyProgramList,
                                    selfProgramList,
                                    otherProgramList);
                    // endregion

                    // region 根据设置的最大值，随机获取当前知识点的选择题
                    List<StudentCourseExercisesEntity> assignChoiceEntityList = new ArrayList<>();
                    assignChoiceEntityList = getRandomAssignTaskList(dto.getMaxChoiceCount(), courseClassChoiceEntityList);

                    // endregion

                    // region 根据设置的最大值，随机获取当前知识点的填空题
                    List<StudentCourseExercisesEntity> assignBlankEntityList = new ArrayList<>();
                    assignBlankEntityList = getRandomAssignTaskList(dto.getMaxBlankCount(), courseClassBlankEntityList);
                    // endregion

                    // region 根据设置的最大值，随机获取当前知识点的编程题
                    List<StudentCourseExercisesEntity> assignProgramEntityList = new ArrayList<>();
                    assignProgramEntityList = getRandomAssignTaskList(dto.getMaxProgramCount(), courseClassProgramEntityList);
                    // endregion

                    // region 根据随机取得的知识点的选择题，将其分配给当前学生
                    affectRow += saveChoiceExercises(courseSignUpEntity.getStudentID(), dto.getLoginUser(), assignChoiceEntityList);
                    // endregion

                    // region 根据随机取得的知识点的填空题，将其分配给当前学生
                    affectRow += saveBlankExercises(courseSignUpEntity.getStudentID(), dto.getLoginUser(), assignBlankEntityList);
                    // endregion

                    // region 根据随机取得的知识点的编程题，将其分配给当前学生
                    affectRow += saveProgramExercises(courseSignUpEntity.getStudentID(), dto.getLoginUser(), assignProgramEntityList);
                    // endregion
                }
            }

            //更新当前课程对应的节次状态为"2"
            CoursePlanEntity coursePlanEntity = new CoursePlanEntity();
            coursePlanEntity.setUniversityCode(dto.getCourseUniversityCode());
            coursePlanEntity.setSchoolID(dto.getCourseSchoolID());
            coursePlanEntity.setCourseID(dto.getCourseID());
            coursePlanEntity.setCourseClass(dto.getCourseClass());
            coursePlanEntity.setDataStatus(CourseStatus.Finished);
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

    private List<StudentCourseExercisesEntity> getRandomAssignTaskList(int assignCount, List<StudentCourseExercisesEntity> taskEntityList) {
        List<StudentCourseExercisesEntity> assignTaskList = new ArrayList<>();
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
