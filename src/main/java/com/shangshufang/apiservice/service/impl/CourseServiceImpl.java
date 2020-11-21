package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.DateUtils;
import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.*;
import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.entity.*;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.*;
import com.shangshufang.apiservice.service.CourseService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseScheduleMapper courseScheduleMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private CourseSignUpMapper signUpMapper;
    @Autowired
    private StudentCourseExercisesMapper courseExercisesMapper;
    @Autowired
    private StudentCourseExercisesDetailMapper courseExercisesDetailMapper;

    private final Logger logger = LogManager.getLogger(CourseServiceImpl.class);

    @Override
    public UnifiedResponse findListLikeName(int pageNumber, int pageSize, String content) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseVO> modelList = new ArrayList<>();
            content = content.equals(ParameterConstant.NO_PARAMETER) ? null : "%" + content + "%";
            int totalCount = courseMapper.searchTotalCountLikeName(content);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CourseEntity> entityList = courseMapper.searchListLikeName(startIndex, pageSize, content);
            for (CourseEntity entity : entityList) {
                CourseVO model = new CourseVO();
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
    public UnifiedResponse findList(int pageNumber,
                                    int pageSize,
                                    int universityCode,
                                    int schoolID,
                                    int teacherID,
                                    int directionID,
                                    int categoryID,
                                    int technologyID,
                                    String courseTimeBegin,
                                    String dataStatus,
                                    boolean isSelf,
                                    String searchType) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            int totalCount = 0;
            List<CourseEntity> entityList = null;

            List<CourseVO> modelList = new ArrayList<>();

            courseTimeBegin = courseTimeBegin.equals(ParameterConstant.NO_PARAMETER) ? null : courseTimeBegin;
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;

            if (searchType.equals(CourseSearchTypeConstant.STARTED)) {
                totalCount = courseMapper.searchStartedTotalCount(
                        universityCode,
                        schoolID,
                        teacherID,
                        technologyID,
                        courseTimeBegin,
                        dataStatus,
                        directionID,
                        categoryID,
                        isSelf);
            } else {
                totalCount = courseMapper.searchTotalCount(
                        universityCode,
                        schoolID,
                        teacherID,
                        technologyID,
                        courseTimeBegin,
                        dataStatus,
                        directionID,
                        categoryID,
                        isSelf);
            }

            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //取得课程基本信息
            if (searchType.equals(CourseSearchTypeConstant.STARTED)) {
                entityList = courseMapper.searchStartedList(
                        universityCode,
                        schoolID,
                        teacherID,
                        technologyID,
                        courseTimeBegin,
                        dataStatus,
                        startIndex,
                        pageSize,
                        directionID,
                        categoryID,
                        isSelf);
            } else {
                entityList = courseMapper.searchList(
                        universityCode,
                        schoolID,
                        teacherID,
                        technologyID,
                        courseTimeBegin,
                        dataStatus,
                        startIndex,
                        pageSize,
                        directionID,
                        categoryID,
                        isSelf);
            }


            for (CourseEntity entity : entityList) {
                //取得课程的课表
                List<CourseScheduleVO> courseScheduleModelList = findCourseScheduleList(universityCode, schoolID, entity.getCourseID());
                //取得课程的授课计划
                List<CoursePlanVO> coursePlanModelList = findCoursePlanList(universityCode, schoolID, entity.getCourseID());

                CourseVO model = new CourseVO();
                //设置课程基本信息
                ObjectConvertUtils.toBean(entity, model);
                //设置课程课表信息
                model.setCourseScheduleList(courseScheduleModelList);
                //设置课程授课计划
                model.setCoursePlanList(coursePlanModelList);

                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList4Student(int pageNumber, int pageSize, int directionID, int categoryID, int technologyID, int universityCode, int schoolID, boolean isSelf, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseVO> modelList = new ArrayList<>();
            int totalCount = courseMapper.searchTotalCount4Student(directionID, categoryID, technologyID, universityCode, schoolID, isSelf, studentID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CourseEntity> entityList = courseMapper.searchList4Student(startIndex, pageSize, directionID, categoryID, technologyID, universityCode, schoolID, isSelf, studentID);
            for (CourseEntity entity : entityList) {
                CourseVO model = new CourseVO();
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
    public UnifiedResponse findSimpleList(int universityCode, int schoolID, int teacherID, int technologyID) {
        try {
            List<CourseVO> modelList = new ArrayList<>();
            //取得课程基本信息
            List<CourseEntity> entityList = courseMapper.searchSimpleList(universityCode, schoolID, teacherID, technologyID);
            if (entityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            for (CourseEntity entity : entityList) {
                CourseVO model = new CourseVO();
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
    public UnifiedResponse find(int universityCode,
                                int schoolID,
                                int courseID,
                                String dataStatus) {
        try {
            CourseVO model = new CourseVO();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            //取得课程基本信息
            CourseEntity entity = courseMapper.search(universityCode, schoolID, courseID, dataStatus);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            //取得课程的课表
            List<CourseScheduleVO> courseScheduleModelList = findCourseScheduleList(universityCode, schoolID, courseID);
            //取得课程的授课计划
            List<CoursePlanVO> coursePlanModelList = findCoursePlanList(universityCode, schoolID, courseID);

            //设置课程基本信息
            ObjectConvertUtils.toBean(entity, model);
            //设置课程课表信息
            model.setCourseScheduleList(courseScheduleModelList);
            //设置课程授课计划
            model.setCoursePlanList(coursePlanModelList);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findKnowledgeLearnAnalyse(int pageNumber, int pageSize, int universityCode, int schoolID, int courseID) {
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseKnowledgeLearnAnalyseVO> modelList = new ArrayList<>();
            //取得课程的报名人数
            float signUpTotalCount = signUpMapper.searchCourseSignUpTotalCount(universityCode, schoolID, courseID);
            if (signUpTotalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            //取得课程教学计划
            List<CoursePlanEntity> coursePlanEntityList = coursePlanMapper.searchList(startIndex, pageSize, universityCode, schoolID, courseID);
            if (coursePlanEntityList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            //遍历教学计划，取得每节课、每个知识点学生的掌握情况
            for (CoursePlanEntity entity : coursePlanEntityList) {
                if (entity.getDataStatus().equals(DataStatusConstant.PENDING)) {
                    continue;
                }
                CourseKnowledgeLearnAnalyseVO model = new CourseKnowledgeLearnAnalyseVO();
                ObjectConvertUtils.toBean(entity, model);
                //region 取得对应节次作业状态为"未提交"的作业信息并从中取得总数（学生人数），该学生人数也是对应节次每个知识点"未提交"的学生人数
                List<StudentCourseExercisesEntity> pendingEntityList =
                        courseExercisesMapper.searchList4CourseClass(courseID, entity.getCourseClass(), ExercisesStatusConstant.Pending);
                model.setPendingCount(pendingEntityList.isEmpty() ? 0 : pendingEntityList.size());
                model.setPendingPercent(pendingEntityList.isEmpty() ? 0 : Float.parseFloat(df.format(pendingEntityList.size() / signUpTotalCount)));
                //endregion

                //region 取得对应节次作业状态为"已掌握"的作业信息并从中取得总数（学生人数），该学生人数也是对应节次每个知识点"已掌握"的学生人数
                List<StudentCourseExercisesEntity> learnedEntityList =
                        courseExercisesMapper.searchList4CourseClass(courseID, entity.getCourseClass(), ExercisesStatusConstant.Pass);
                model.setLearnedCount(learnedEntityList.isEmpty() ? 0 : learnedEntityList.size());
                model.setLearnedPercent(learnedEntityList.isEmpty() ? 0 : Float.parseFloat(df.format(learnedEntityList.size() / signUpTotalCount)));
                //endregion

                //region 取得对应节次作业状态为"批改中"的作业信息，从依此取得详细的作业内容，再循环计算每个知识点的掌握
                int correctingKnowledgeCount = 0;
                //取得"批改中"的作业
                List<StudentCourseExercisesEntity> correctingEntityList =
                        courseExercisesMapper.searchList4CourseClass(courseID, entity.getCourseClass(), ExercisesStatusConstant.Correcting);
                if (!correctingEntityList.isEmpty()) {
                    for (StudentCourseExercisesEntity correctingEntity : correctingEntityList) {

                        int correctingTotalCount = courseExercisesDetailMapper.searchKnowledgeCorrectingTotalCount(
                                correctingEntity.getCourseExercisesID(),
                                entity.getTechnologyID(),
                                entity.getKnowledgeID());
                        if (correctingTotalCount > 0) {
                            correctingKnowledgeCount++;
                        }
                    }
                    model.setCorrectingCount(correctingKnowledgeCount);
                    model.setCorrectingPercent(Float.parseFloat(df.format(correctingKnowledgeCount / signUpTotalCount)));
                }
                //endregion

                //region 取得对应节次每个知识点掌握情况为"较薄弱"的学生人数
                int weaknessKnowledgeCount = 0;
                //取得"待修改"的作业
                List<StudentCourseExercisesEntity> reviewedEntityList =
                        courseExercisesMapper.searchList4CourseClass(courseID, entity.getCourseClass(), ExercisesStatusConstant.Reviewed);
                if (!reviewedEntityList.isEmpty()) {
                    for (StudentCourseExercisesEntity reviewedEntity : reviewedEntityList) {
                        int incorrectTotalCount = courseExercisesDetailMapper.searchKnowledgeIncorrectTotalCount(
                                reviewedEntity.getCourseExercisesID(),
                                entity.getTechnologyID(),
                                entity.getKnowledgeID());
                        if (incorrectTotalCount > 0) {
                            weaknessKnowledgeCount++;
                        }
                    }
                    model.setWeaknessCount(weaknessKnowledgeCount);
                    model.setWeaknessPercent(Float.parseFloat(df.format(weaknessKnowledgeCount / signUpTotalCount)));
                }
                //endregion

                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(modelList.size(), modelList);

        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkCourseExist(int universityCode,
                                            int schoolID,
                                            String courseName,
                                            String courseTimeBegin,
                                            String courseTimeEnd) {
        try {
            int count = courseMapper.checkCourseExist(universityCode, schoolID, courseName, courseTimeBegin, courseTimeEnd);
            Boolean exist = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeCourseBaseInfo(CourseDTO dto) {
        try {
            int affectRow = 0;
            CourseEntity courseEntity = new CourseEntity();
            ObjectConvertUtils.toBean(dto, courseEntity);
            courseEntity.setCreateUser(dto.getLoginUser());
            courseEntity.setUpdateUser(dto.getLoginUser());
            affectRow += courseMapper.update(courseEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeCourseSchedule(CourseDTO dto) {
        try {
            int affectRow = 0;
            List<CourseScheduleEntity> courseScheduleEntityList = JsonUtils.deserializationToObject(dto.getCourseScheduleJson(), CourseScheduleEntity.class);
            if (courseScheduleEntityList != null) {
                affectRow += courseScheduleMapper.delete(dto.getUniversityCode(), dto.getSchoolID(), dto.getCourseID());
                for (CourseScheduleEntity courseScheduleEntity : courseScheduleEntityList) {
                    courseScheduleEntity.setUniversityCode(dto.getUniversityCode());
                    courseScheduleEntity.setSchoolID(dto.getSchoolID());
                    courseScheduleEntity.setCourseID(dto.getCourseID());
                    courseScheduleEntity.setCreateUser(dto.getLoginUser());
                    courseScheduleEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += courseScheduleMapper.insert(courseScheduleEntity);
                }
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeCoursePlan(CourseDTO dto) {
        try {
            int affectRow = 0;
            List<CoursePlanEntity> coursePlanEntityList = JsonUtils.deserializationToObject(dto.getCoursePlanJson(), CoursePlanEntity.class);
            if (coursePlanEntityList != null) {
                affectRow += coursePlanMapper.delete4Class(dto.getUniversityCode(), dto.getSchoolID(), dto.getCourseID());
                for (CoursePlanEntity coursePlanEntity : coursePlanEntityList) {
                    if (coursePlanEntity.getDataStatus().equals(CourseStatus.Finished)) {
                        continue;
                    }

                    coursePlanEntity.setUniversityCode(dto.getUniversityCode());
                    coursePlanEntity.setSchoolID(dto.getSchoolID());
                    coursePlanEntity.setCourseID(dto.getCourseID());
                    coursePlanEntity.setCreateUser(dto.getLoginUser());
                    coursePlanEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += coursePlanMapper.insert(coursePlanEntity);
                }
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse startCourse() {
        try {
            int affectRow = courseMapper.updateCourseToStart();
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(CourseDTO dto) {
        try {
            int affectRow = 0;
            CourseEntity courseEntity = new CourseEntity();
            List<CourseScheduleEntity> courseScheduleEntityList = JsonUtils.deserializationToObject(dto.getCourseScheduleJson(), CourseScheduleEntity.class);
            List<CoursePlanEntity> coursePlanEntityList = JsonUtils.deserializationToObject(dto.getCoursePlanJson(), CoursePlanEntity.class);
            ObjectConvertUtils.toBean(dto, courseEntity);
            courseEntity.setCreateUser(dto.getLoginUser());
            courseEntity.setUpdateUser(dto.getLoginUser());

            //设置课程状态
            String currentDateTime = DateUtils.getCurrentDateTime();
            String courseBeginTime = dto.getCourseTimeBegin();
            int compareResult = DateUtils.compare(currentDateTime, courseBeginTime + " 00:00:00");
            if (compareResult >= 0) {
                courseEntity.setDataStatus(CourseStatus.Active);
            } else {
                courseEntity.setDataStatus(CourseStatus.Pending);
            }

            //添加课程基本信息
            affectRow += courseMapper.insert(courseEntity);

            //添加课程表
            if (courseScheduleEntityList != null) {
                affectRow += courseScheduleMapper.delete(courseEntity.getUniversityCode(), courseEntity.getSchoolID(), courseEntity.getCourseID());
                for (CourseScheduleEntity courseScheduleEntity : courseScheduleEntityList) {
                    courseScheduleEntity.setUniversityCode(dto.getUniversityCode());
                    courseScheduleEntity.setSchoolID(dto.getSchoolID());
                    courseScheduleEntity.setCourseID(courseEntity.getCourseID());
                    courseScheduleEntity.setCreateUser(dto.getLoginUser());
                    courseScheduleEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += courseScheduleMapper.insert(courseScheduleEntity);
                }
            }

            //添加课程授课计划
            if (coursePlanEntityList != null) {
                affectRow += coursePlanMapper.delete(courseEntity.getUniversityCode(), courseEntity.getSchoolID(), courseEntity.getCourseID());
                for (CoursePlanEntity coursePlanEntity : coursePlanEntityList) {
                    coursePlanEntity.setUniversityCode(dto.getUniversityCode());
                    coursePlanEntity.setSchoolID(dto.getSchoolID());
                    coursePlanEntity.setCourseID(courseEntity.getCourseID());
                    coursePlanEntity.setCreateUser(dto.getLoginUser());
                    coursePlanEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += coursePlanMapper.insert(coursePlanEntity);
                }
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(CourseDTO dto) {
        try {
            int affectRow = 0;
            CourseEntity courseEntity = new CourseEntity();
            List<CourseScheduleEntity> courseScheduleEntityList = JsonUtils.deserializationToObject(dto.getCourseScheduleJson(), CourseScheduleEntity.class);
            List<CoursePlanEntity> coursePlanEntityList = JsonUtils.deserializationToObject(dto.getCoursePlanJson(), CoursePlanEntity.class);
            ObjectConvertUtils.toBean(dto, courseEntity);
            courseEntity.setCreateUser(dto.getLoginUser());
            courseEntity.setUpdateUser(dto.getLoginUser());

            //修改课程基本信息
            affectRow += courseMapper.update(courseEntity);

            //修改课程表
            if (courseScheduleEntityList != null) {
                affectRow += courseScheduleMapper.delete(courseEntity.getUniversityCode(), courseEntity.getSchoolID(), courseEntity.getCourseID());
                for (CourseScheduleEntity courseScheduleEntity : courseScheduleEntityList) {
                    affectRow += courseScheduleMapper.insert(courseScheduleEntity);
                }
            }

            //修改课程授课计划
            if (coursePlanEntityList != null) {
                affectRow += coursePlanMapper.delete(courseEntity.getUniversityCode(), courseEntity.getSchoolID(), courseEntity.getCourseID());
                for (CoursePlanEntity coursePlanEntity : coursePlanEntityList) {
                    affectRow += coursePlanMapper.insert(coursePlanEntity);
                }
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(CourseDTO dto) {
        try {
            CourseEntity entity = new CourseEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = courseMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    private List<CourseScheduleVO> findCourseScheduleList(int universityCode, int schoolID, int courseID) throws Exception {
        List<CourseScheduleVO> courseScheduleModelList = new ArrayList<>();

        List<CourseScheduleEntity> courseScheduleEntityList = courseScheduleMapper.searchList(universityCode, schoolID, courseID);
        if (courseScheduleEntityList.isEmpty()) {
            return null;
        }
        for (CourseScheduleEntity courseScheduleEntity : courseScheduleEntityList) {
            CourseScheduleVO courseScheduleModel = new CourseScheduleVO();
            ObjectConvertUtils.toBean(courseScheduleEntity, courseScheduleModel);
            courseScheduleModelList.add(courseScheduleModel);
        }
        return courseScheduleModelList;
    }

    private List<CoursePlanVO> findCoursePlanList(int universityCode, int schoolID, int courseID) throws Exception {
        List<CoursePlanVO> coursePlanModelList = new ArrayList<>();

        List<CoursePlanEntity> coursePlanEntityList = coursePlanMapper.searchAll(universityCode, schoolID, courseID);
        if (coursePlanEntityList.isEmpty()) {
            return null;
        }
        for (CoursePlanEntity coursePlanEntity : coursePlanEntityList) {
            CoursePlanVO coursePlanModel = new CoursePlanVO();
            ObjectConvertUtils.toBean(coursePlanEntity, coursePlanModel);
            coursePlanModelList.add(coursePlanModel);
        }
        return coursePlanModelList;
    }
}
