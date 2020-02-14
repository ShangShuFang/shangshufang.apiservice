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
    private ExercisesMapper exercisesMapper;
    @Autowired
    private ExercisesDocumentMapper exercisesDocumentMapper;
    @Autowired
    private ExercisesKnowledgeMapper exercisesKnowledgeMapper;
    @Autowired
    private CoursePlanMapper coursePlanMapper;
    @Autowired
    private CourseSignUpMapper courseSignUpMapper;
    @Autowired
    private UniversityStudentExercisesMapper universityStudentExercisesMapper;

    private Logger logger = LogManager.getLogger(UniversityStudentExercisesServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int courseUniversityCode, int courseSchoolID, int courseID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentExercisesVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = universityStudentExercisesMapper.searchTotalCount(courseUniversityCode, courseSchoolID, courseID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentExercisesEntity> entityList =  universityStudentExercisesMapper.searchList(startIndex, pageSize, courseUniversityCode, courseSchoolID, courseID, dataStatus);
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
            //取得当前课程所有报名的学生列表
            List<CourseSignUpEntity> courseSignUpEntityList = courseSignUpMapper.searchAllCourseSignUpList(
                    dto.getCourseUniversityCode(),
                    dto.getCourseSchoolID(),
                    dto.getCourseID());
            if(courseSignUpEntityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //取得当前课程、当前节次、每个知识点的习题列表
            List<CourseExercisesEntity> courseClassExercisesEntityList =  exercisesMapper.searchClassExercisesList(
                    dto.getCourseUniversityCode(),
                    dto.getCourseSchoolID(),
                    dto.getCourseID(),
                    dto.getCourseClass());
            if(courseClassExercisesEntityList == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }

            //循环习题列表，根据习题编号查询对应的练习题列表
            int affectRow = 0;
            for (CourseExercisesEntity courseExercisesEntity : courseClassExercisesEntityList) {
                if(courseExercisesEntity.getExercisesID() == 0){
                    continue;
                }

                //取得当前课程练习编号对应的练习题列表
                List<ExercisesDocumentEntity> taskEntityList = exercisesDocumentMapper.searchList(courseExercisesEntity.getExercisesID());

                //循环已报名的学生列表，给每个学生添加分配的练习题
                for (CourseSignUpEntity courseSignUpEntity : courseSignUpEntityList) {
                    //随机取得指定数量的练习题
                    List<ExercisesDocumentEntity> assignTaskList = getRandomAssignTaskList(dto.getAssignCount(), taskEntityList);

                    for (ExercisesDocumentEntity documentEntity : assignTaskList) {
                        UniversityStudentExercisesEntity entity = new UniversityStudentExercisesEntity();
                        entity.setStudentUniversityCode(courseSignUpEntity.getStudentUniversityCode());
                        entity.setStudentSchoolID(courseSignUpEntity.getStudentSchoolID());
                        entity.setStudentID(courseSignUpEntity.getStudentID());
                        entity.setCourseUniversityCode(dto.getCourseUniversityCode());
                        entity.setCourseSchoolID(dto.getCourseSchoolID());
                        entity.setCourseID(dto.getCourseID());
                        entity.setCourseClass(dto.getCourseClass());
                        entity.setExercisesID(courseExercisesEntity.getExercisesID());
                        entity.setTechnologyID(courseExercisesEntity.getTechnologyID());
                        entity.setLearningPhaseID(courseExercisesEntity.getLearningPhaseID());
                        entity.setKnowledgeID(courseExercisesEntity.getKnowledgeID());
                        entity.setExercisesDocumentID(documentEntity.getExercisesDocumentID());
                        entity.setCreateUser(dto.getLoginUser());
                        entity.setUpdateUser(dto.getLoginUser());
                        affectRow += universityStudentExercisesMapper.insert(entity);
                    }
                }
            }

            //更新当前课程对应的节次状态为"F"
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
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(UniversityStudentExercisesDTO dto) {
        return null;
    }

    private List<ExercisesDocumentEntity> getRandomAssignTaskList(int assignCount, List<ExercisesDocumentEntity> taskEntityList) {
        List<ExercisesDocumentEntity> assignTaskList = new ArrayList<>();
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
