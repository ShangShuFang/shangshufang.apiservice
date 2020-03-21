package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.DataStatusConstant;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.TechnologyDTO;
import com.shangshufang.apiservice.entity.TechnologyDirectionEntity;
import com.shangshufang.apiservice.entity.TechnologyEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.TechnologyDirectionMapper;
import com.shangshufang.apiservice.mapper.TechnologyMapper;
import com.shangshufang.apiservice.service.TechnologyService;
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
    private TechnologyDirectionMapper technologyDirectionMapper;
    private Logger logger = LogManager.getLogger(TechnologyServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount(dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyEntity> entityList =  myMapper.searchList(startIndex, pageSize, dataStatus);
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
    public UnifiedResponse findList4Client(int pageNumber, int pageSize) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<TechnologyVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(DataStatusConstant.ACTIVE);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<TechnologyEntity> entityList =  myMapper.searchList4Client(startIndex, pageSize, DataStatusConstant.ACTIVE);
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
    public UnifiedResponse findSimpleList() {
        try {
            List<TechnologyVO> modelList = new ArrayList<>();
            List<TechnologyEntity> entityList =  myMapper.searchSimpleList();
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
            affectRow += technologyDirectionMapper.delete(technologyID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(TechnologyDTO dto) {
        try {
            String[] directions = dto.getDirections().split(",");
            TechnologyEntity entity = new TechnologyEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.insert(entity);

            for (String direction : directions) {
                TechnologyDirectionEntity technologyDirectionEntity = new TechnologyDirectionEntity();
                technologyDirectionEntity.setTechnologyID(entity.getTechnologyID());
                technologyDirectionEntity.setDirectionID(Integer.parseInt(direction));
                technologyDirectionEntity.setCreateUser(dto.getLoginUser());
                technologyDirectionEntity.setUpdateUser(dto.getLoginUser());
                affectRow += technologyDirectionMapper.insert(technologyDirectionEntity);
            }

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
            String[] directions = dto.getDirections().split(",");

            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.update(entity);

            affectRow += technologyDirectionMapper.delete(dto.getTechnologyID());
            for (String direction : directions) {
                TechnologyDirectionEntity technologyDirectionEntity = new TechnologyDirectionEntity();
                technologyDirectionEntity.setTechnologyID(entity.getTechnologyID());
                technologyDirectionEntity.setDirectionID(Integer.parseInt(direction));
                technologyDirectionEntity.setCreateUser(dto.getLoginUser());
                technologyDirectionEntity.setUpdateUser(dto.getLoginUser());
                affectRow += technologyDirectionMapper.insert(technologyDirectionEntity);
            }

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
