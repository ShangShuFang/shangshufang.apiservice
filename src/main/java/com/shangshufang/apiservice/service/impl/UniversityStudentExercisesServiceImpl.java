package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.CourseStatus;
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

    private Logger logger = LogManager.getLogger(UniversityStudentExercisesServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID, String dataStatus, String studentName) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            studentName = studentName.equals(ParameterConstant.NO_PARAMETER) ? null : studentName;
            int totalCount = universityStudentExercisesMapper.searchTotalCount(courseUniversityCode, courseSchoolID, courseID, dataStatus, studentName);
            if(totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList =  universityStudentExercisesMapper.searchList(startIndex, pageSize, courseUniversityCode, courseSchoolID, courseID, dataStatus, studentName);
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
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList =  universityStudentExercisesMapper.searchList4Student(startIndex, pageSize, studentID, courseID, dataStatus, studentName, isSelf);
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
            if(totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList =  universityStudentExercisesMapper.searchList4Technology(startIndex, pageSize, universityCode, schoolID, studentID, technologyID, dataStatus);
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
    public UnifiedResponse assign(UniversityStudentExercisesDTO dto) {
        try{
            int affectRow = 0;
            //取得当前课程所有报名的学生列表
            List<CourseSignUpEntity> courseSignUpEntityList = courseSignUpMapper.searchAllCourseSignUpList(
                    dto.getCourseUniversityCode(),
                    dto.getCourseSchoolID(),
                    dto.getCourseID());
            if(courseSignUpEntityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //取得当前节次的所有知识点
            List<CoursePlanEntity> courseKnowledgeList = coursePlanMapper.searchKnowledgeList4CourseClass(dto.getCourseUniversityCode(), dto.getCourseSchoolID(), dto.getCourseID(), dto.getCourseClass());
            if(courseKnowledgeList.isEmpty()) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //循环已报名的学生列表，给每个学生添加分配的练习题
            for (CourseSignUpEntity courseSignUpEntity : courseSignUpEntityList) {
                //循环当节课每个知识点
                for (CoursePlanEntity coursePlanEntity : courseKnowledgeList) {
                    //取得当前课程当前节次每个知识点的习题列表
                    List<TechnologyKnowledgeExercisesEntity> knowledgeExercisesEntityList = knowledgeExercisesMapper.searchList4CourseKnowledge(dto.getCourseUniversityCode(), dto.getCourseSchoolID(), dto.getCourseID(), dto.getCourseClass(), coursePlanEntity.getKnowledgeID());
                    if(knowledgeExercisesEntityList.isEmpty()) {
                        continue;
                    }

                    //随机取得指定数量的练习题
                    List<TechnologyKnowledgeExercisesEntity> assignTaskList = getRandomAssignTaskList(dto.getAssignCount(), knowledgeExercisesEntityList);

                    for (TechnologyKnowledgeExercisesEntity assignEntity : assignTaskList) {
                        UniversityStudentExercisesEntity entity = new UniversityStudentExercisesEntity();
                        entity.setStudentUniversityCode(courseSignUpEntity.getStudentUniversityCode());
                        entity.setStudentSchoolID(courseSignUpEntity.getStudentSchoolID());
                        entity.setStudentID(courseSignUpEntity.getStudentID());
                        entity.setCourseUniversityCode(dto.getCourseUniversityCode());
                        entity.setCourseSchoolID(dto.getCourseSchoolID());
                        entity.setCourseID(dto.getCourseID());
                        entity.setCourseClass(dto.getCourseClass());
                        entity.setTechnologyID(assignEntity.getTechnologyID());
                        entity.setLearningPhaseID(assignEntity.getLearningPhaseID());
                        entity.setKnowledgeID(assignEntity.getKnowledgeID());
                        entity.setExercisesDocumentID(assignEntity.getExercisesID());
                        entity.setCreateUser(dto.getLoginUser());
                        entity.setUpdateUser(dto.getLoginUser());
                        affectRow += universityStudentExercisesMapper.insert(entity);
                    }
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
        }catch (Exception ex){
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

    private List<TechnologyKnowledgeExercisesEntity> getRandomAssignTaskList(int assignCount, List<TechnologyKnowledgeExercisesEntity> taskEntityList) {
        List<TechnologyKnowledgeExercisesEntity> assignTaskList = new ArrayList<>();
        if(assignCount >= taskEntityList.size()){
            assignTaskList.addAll(taskEntityList);
        }else{
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
    };
}
