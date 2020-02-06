package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.DateUtils;
import com.shangshufang.apiservice.common.JsonUtils;
import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.CourseStatus;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CourseDTO;
import com.shangshufang.apiservice.entity.CourseEntity;
import com.shangshufang.apiservice.entity.CoursePlanEntity;
import com.shangshufang.apiservice.entity.CourseScheduleEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CourseMapper;
import com.shangshufang.apiservice.mapper.CoursePlanMapper;
import com.shangshufang.apiservice.mapper.CourseScheduleMapper;
import com.shangshufang.apiservice.service.CourseService;
import com.shangshufang.apiservice.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private Logger logger = LogManager.getLogger(CourseServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber,
                                    int pageSize,
                                    int universityCode,
                                    int schoolID,
                                    int teacherID,
                                    int technologyID,
                                    String courseTimeBegin,
                                    String dataStatus,
                                    boolean isSelf) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CourseVO> modelList = new ArrayList<>();

            courseTimeBegin = courseTimeBegin.equals(ParameterConstant.NO_PARAMETER) ? null : courseTimeBegin;
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;

            int totalCount = courseMapper.searchTotalCount(
                    universityCode,
                    schoolID,
                    teacherID,
                    technologyID,
                    courseTimeBegin,
                    dataStatus,
                    isSelf);
            if(totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //取得课程基本信息
            List<CourseEntity> entityList =  courseMapper.searchList(
                    universityCode,
                    schoolID,
                    teacherID,
                    technologyID,
                    courseTimeBegin,
                    dataStatus,
                    startIndex,
                    pageSize,
                    isSelf);

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
    public UnifiedResponse find(int universityCode,
                                int schoolID,
                                int courseID,
                                String dataStatus) {
        try {
            CourseVO model = new CourseVO();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            //取得课程基本信息
            CourseEntity entity =  courseMapper.search(universityCode, schoolID, courseID, dataStatus);
            if(entity == null){
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
    public UnifiedResponse checkCourseExist(int universityCode,
                                            int schoolID,
                                            String courseName,
                                            String courseTimeBegin,
                                            String courseTimeEnd) {
        try {
            int count =  courseMapper.checkCourseExist(universityCode, schoolID, courseName, courseTimeBegin, courseTimeEnd);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeCourseBaseInfo(CourseDTO dto) {
        try{
            int affectRow = 0;
            CourseEntity courseEntity = new CourseEntity();
            ObjectConvertUtils.toBean(dto, courseEntity);
            courseEntity.setCreateUser(dto.getLoginUser());
            courseEntity.setUpdateUser(dto.getLoginUser());
            affectRow += courseMapper.update(courseEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        }catch (Exception ex){
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeCourseSchedule(CourseDTO dto) {
        try{
            int affectRow = 0;
            List<CourseScheduleEntity> courseScheduleEntityList = JsonUtils.deserializationToObject(dto.getCourseScheduleJson(), CourseScheduleEntity.class);
            if(courseScheduleEntityList != null) {
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
        }catch (Exception ex){
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeCoursePlan(CourseDTO dto) {
        try{
            int affectRow = 0;
            List<CoursePlanEntity> coursePlanEntityList = JsonUtils.deserializationToObject(dto.getCoursePlanJson(), CoursePlanEntity.class);
            if(coursePlanEntityList != null) {
                affectRow += coursePlanMapper.delete4Class(dto.getUniversityCode(), dto.getSchoolID(), dto.getCourseID());
                for (CoursePlanEntity coursePlanEntity : coursePlanEntityList) {
                    if(coursePlanEntity.getDataStatus().equals(CourseStatus.Finished)){
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
        }catch (Exception ex){
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
            if(compareResult >= 0){
                courseEntity.setDataStatus(CourseStatus.Active);
            }else{
                courseEntity.setDataStatus(CourseStatus.Pending);
            }

            //添加课程基本信息
            affectRow += courseMapper.insert(courseEntity);

            //添加课程表
            if(courseScheduleEntityList != null) {
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
            if(coursePlanEntityList != null) {
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
            if(courseScheduleEntityList != null) {
                affectRow += courseScheduleMapper.delete(courseEntity.getUniversityCode(), courseEntity.getSchoolID(), courseEntity.getCourseID());
                for (CourseScheduleEntity courseScheduleEntity : courseScheduleEntityList) {
                    affectRow += courseScheduleMapper.insert(courseScheduleEntity);
                }
            }

            //修改课程授课计划
            if(coursePlanEntityList != null) {
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
        if(courseScheduleEntityList.isEmpty()){
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

        List<CoursePlanEntity> coursePlanEntityList = coursePlanMapper.searchList(universityCode, schoolID, courseID);
        if(coursePlanEntityList.isEmpty()){
            return null;
        }
        for (CoursePlanEntity coursePlanEntity : coursePlanEntityList ) {
            CoursePlanVO coursePlanModel = new CoursePlanVO();
            ObjectConvertUtils.toBean(coursePlanEntity, coursePlanModel);
            coursePlanModelList.add(coursePlanModel);
        }
        return coursePlanModelList;
    }
}
