package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.DataStatusConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.StudentCollectionDTO;
import com.shangshufang.apiservice.entity.StudentAbilityAnalysisEntity;
import com.shangshufang.apiservice.entity.AbilityLevelEntity;
import com.shangshufang.apiservice.entity.StudentCollectionEntity;
import com.shangshufang.apiservice.entity.TechnologyUsingEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.*;
import com.shangshufang.apiservice.service.StudentCollectionService;
import com.shangshufang.apiservice.vo.StudentCollectionVO;
import com.shangshufang.apiservice.vo.TechnologyGapAnalysisVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCollectionServiceImpl implements StudentCollectionService {
    @Autowired
    private StudentCollectionMapper myMapper;

    @Autowired
    private TechnologyUsingMapper technologyUsingMapper;

    @Autowired
    private AbilityLevelMapper abilityLevelMapper;

    @Autowired
    private AnalysisAbilityMapper analysisAbilityMapper;

    @Autowired
    private StudentComprehensiveExercisesMapper studentComprehensiveExercisesMapper;

    private final Logger logger = LogManager.getLogger(StudentCollectionServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int studentID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<StudentCollectionVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(studentID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            //取得学生关注的企业列表
            List<StudentCollectionEntity> entityList =  myMapper.searchList(startIndex, pageSize, studentID);
            for (StudentCollectionEntity entity : entityList) {
                StudentCollectionVO model = new StudentCollectionVO();
                List<TechnologyGapAnalysisVO> technologyGapAnalysisList = new ArrayList<>();
                //取得当前企业被关注的总数
                int collectTotalCount = myMapper.searchCompanyTotalCount(entity.getCompanyID());

                //取得每个企业使用的技术列表(包含技术编号、名称、缩略图、起步级别以对应级别的起步要求)
                List<TechnologyUsingEntity> companyUsingTechnologyEntityList = technologyUsingMapper.searchUsingTechnologyList(entity.getCompanyID());

                //遍历每个企业正在使用的技术列表，取得指定学生每个技术的能力分析
                for (TechnologyUsingEntity technologyUsingEntity : companyUsingTechnologyEntityList) {
                    TechnologyGapAnalysisVO gapAnalysisModel = new TechnologyGapAnalysisVO();
                    //取得当前使用技术所要求级别的详细信息(包含级别、已掌握知识点百分比、已完成的综合练习数量、参与的项目数量)
                    AbilityLevelEntity abilityLevelEntity = abilityLevelMapper.search(technologyUsingEntity.getRecruitLevel());
                    if (abilityLevelEntity == null) {
                        continue;
                    }
                    //取得当前学生对与每个技术此刻的能力信息（包含级别、已掌握知识点百分比）
                    StudentAbilityAnalysisEntity studentAbilityOfTechnologyEntity = analysisAbilityMapper.searchStudentAbility(studentID, technologyUsingEntity.getTechnologyID());
                    //取得当前学生已完成的综合练习数量
                    int finishComprehensiveExercisesCount = studentComprehensiveExercisesMapper.searchTotalCount(studentID, 0,0, technologyUsingEntity.getTechnologyID(), DataStatusConstant.ACTIVE);

                    //TODO: 取得当前学生参与的项目数量
                    int joinProjectExercisesCount = 0;

                    gapAnalysisModel.setTechnologyID(technologyUsingEntity.getTechnologyID());
                    gapAnalysisModel.setTechnologyName(technologyUsingEntity.getTechnologyName());
                    gapAnalysisModel.setTechnologyThumbnailRectangle(technologyUsingEntity.getTechnologyThumbnailRectangle());
                    gapAnalysisModel.setRecruitLevel(technologyUsingEntity.getRecruitLevel());
                    gapAnalysisModel.setRequiredKnowledge(abilityLevelEntity.getRequiredKnowledge());
                    gapAnalysisModel.setRequiredComprehensiveExercises(abilityLevelEntity.getRequiredComprehensiveExercises());
                    gapAnalysisModel.setRequiredProjectExercises(abilityLevelEntity.getRequiredProjectExercises());

                    gapAnalysisModel.setStudentLevel(studentAbilityOfTechnologyEntity == null ? "无" : studentAbilityOfTechnologyEntity.getAbilityLevel());
                    gapAnalysisModel.setFinishKnowledgePercent(studentAbilityOfTechnologyEntity == null ? 0 : studentAbilityOfTechnologyEntity.getFinishedKnowledgePercent());
                    gapAnalysisModel.setFinishComprehensiveExercisesCount(finishComprehensiveExercisesCount);
                    gapAnalysisModel.setJoinProjectExercisesCount(joinProjectExercisesCount);

                    technologyGapAnalysisList.add(gapAnalysisModel);
                }
                ObjectConvertUtils.toBean(entity, model);
                model.setCollectTotalCount(collectTotalCount);
                model.setTechnologyGapAnalysisList(technologyGapAnalysisList);
                modelList.add(model);
            }
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, modelList);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkCollected(int studentID, int companyID) {
        try {
            int totalCount = myMapper.checkCollected(studentID, companyID);
            boolean isCollection = totalCount > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, isCollection);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(StudentCollectionDTO dto) {
        try {
            StudentCollectionEntity entity = new StudentCollectionEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int studentID, int companyID) {
        try {
            int affectRow = myMapper.delete(studentID, companyID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
