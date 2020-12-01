package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.DataStatusConstant;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyDTO;
import com.shangshufang.apiservice.entity.StudentAbilityAnalysisEntity;
import com.shangshufang.apiservice.entity.TechnologyEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.*;
import com.shangshufang.apiservice.service.TechnologyService;
import com.shangshufang.apiservice.vo.StudentAbilityAnalysisVO;
import com.shangshufang.apiservice.vo.TechnologyVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    @Autowired
    private TechnologyMapper myMapper;
    @Autowired
    private TechnologyUsingMapper technologyUsingMapper;
    @Autowired
    private AnalysisAbilityMapper analysisAbilityMapper;

    private Logger logger = LogManager.getLogger(TechnologyServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int directionID, int categoryID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(directionID, categoryID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyEntity> entityList =  myMapper.searchList(startIndex, pageSize, directionID, categoryID, dataStatus);
            for (TechnologyEntity entity : entityList) {
                TechnologyVO model = new TechnologyVO();
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
    public UnifiedResponse findList4Client(int pageNumber, int pageSize, int directionID, int categoryID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(directionID, categoryID, DataStatusConstant.ACTIVE);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyEntity> entityList =  myMapper.searchList4Client(startIndex, pageSize, directionID, categoryID, DataStatusConstant.ACTIVE);

            for (TechnologyEntity entity : entityList) {
                TechnologyVO model = new TechnologyVO();
                List<StudentAbilityAnalysisVO> topStudentMainInfoModelList = new ArrayList<>();
                List<StudentAbilityAnalysisEntity> topStudentMainInfoEntityList = analysisAbilityMapper.searchTopStudentSummary(entity.getTechnologyID(), 4);
                if (!topStudentMainInfoEntityList.isEmpty()) {
                    for (StudentAbilityAnalysisEntity topStudentMainInfoEntity : topStudentMainInfoEntityList) {
                        StudentAbilityAnalysisVO topStudentMainInfoModel = new StudentAbilityAnalysisVO();
                        ObjectConvertUtils.toBean(topStudentMainInfoEntity, topStudentMainInfoModel);
                        topStudentMainInfoModelList.add(topStudentMainInfoModel);
                    }
                    model.setTopStudentMainInfoList(topStudentMainInfoModelList);
                }
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
    public UnifiedResponse findSimpleList(int directionID, int categoryID, String dataStatus) {
        try {
            List<TechnologyVO> modelList = new ArrayList<>();
            List<TechnologyEntity> entityList =  myMapper.searchSimpleList(directionID, categoryID, dataStatus);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (TechnologyEntity entity : entityList) {
                TechnologyVO model = new TechnologyVO();
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
    public UnifiedResponse find(int technologyID) {
        try {
            TechnologyEntity entity =  myMapper.searchByID(technologyID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            TechnologyVO model = new TechnologyVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findStudentLearning(int studentUniversityCode, int studentSchoolID, int studentID) {
        try {
            List<TechnologyVO> modelList = new ArrayList<>();
            List<TechnologyEntity> entityList =  myMapper.searchStudentLearning(studentUniversityCode, studentSchoolID, studentID);
            if(entityList.isEmpty()){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            for (TechnologyEntity entity : entityList) {
                TechnologyVO model = new TechnologyVO();
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
    public UnifiedResponse checkTechnologyNameExist(String technologyName) {
        try {
            int count =  myMapper.checkTechnologyNameExist(technologyName);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeThumbnail(TechnologyDTO dto) {
        try {
            TechnologyEntity entity = new TechnologyEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateThumbnail(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int technologyID) {
        try {
            int affectRow = myMapper.delete(technologyID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(TechnologyDTO dto) {
        try {
            TechnologyEntity entity = new TechnologyEntity();
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
    public UnifiedResponse change(TechnologyDTO dto) {
        try {
            TechnologyEntity entity = new TechnologyEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(TechnologyDTO dto) {
        try {
            TechnologyEntity entity = new TechnologyEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
