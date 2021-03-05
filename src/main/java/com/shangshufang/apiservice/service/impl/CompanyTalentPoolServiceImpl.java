package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CompanyTalentPoolDTO;
import com.shangshufang.apiservice.entity.CompanyTalentPoolEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CompanyTalentPoolMapper;
import com.shangshufang.apiservice.service.CompanyTalentPoolService;
import com.shangshufang.apiservice.vo.CompanyTalentPoolVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyTalentPoolServiceImpl implements CompanyTalentPoolService {
    @Autowired
    private CompanyTalentPoolMapper companyTalentPoolMapper;

    private final Logger logger = LogManager.getLogger(CompanyTalentPoolServiceImpl.class);

    @Override
    public UnifiedResponse findListWithCompany(int pageNumber, int pageSize, int companyID, int technologyID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CompanyTalentPoolVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = companyTalentPoolMapper.searchTotalCount(companyID, technologyID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CompanyTalentPoolEntity> entityList =  companyTalentPoolMapper.searchList(startIndex, pageSize, companyID, technologyID, dataStatus);
            for (CompanyTalentPoolEntity entity : entityList) {
                CompanyTalentPoolVO model = new CompanyTalentPoolVO();
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
    public UnifiedResponse findListWithStudent(int pageNumber, int pageSize, int studentID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CompanyTalentPoolVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = companyTalentPoolMapper.searchTotalCountWithStudent(studentID, dataStatus);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CompanyTalentPoolEntity> entityList =  companyTalentPoolMapper.searchListWithStudent(startIndex, pageSize, studentID, dataStatus);
            for (CompanyTalentPoolEntity entity : entityList) {
                CompanyTalentPoolVO model = new CompanyTalentPoolVO();
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
    public UnifiedResponse find(int companyID, int studentID) {
        try {
            CompanyTalentPoolVO model = new CompanyTalentPoolVO();
            CompanyTalentPoolEntity entity = companyTalentPoolMapper.search(companyID, studentID);
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(CompanyTalentPoolDTO dto) {
        try {
            int affectRow = 0;
            dto.setInterviewTime(dto.getInterviewTime().equals(ParameterConstant.EMPTY) ? null : dto.getInterviewTime());
            CompanyTalentPoolEntity entity = new CompanyTalentPoolEntity();
            CompanyTalentPoolEntity entityExists = companyTalentPoolMapper.search(dto.getCompanyID(), dto.getStudentID());
            ObjectConvertUtils.toBean(dto, entity);
            entity.setCreateUser(dto.getLoginUser());
            entity.setUpdateUser(dto.getLoginUser());
            if (entityExists == null) {
                affectRow = companyTalentPoolMapper.insert(entity);
            } else {
                entity.setTalentID(entityExists.getTalentID());
                affectRow = companyTalentPoolMapper.update(entity);
            }
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(CompanyTalentPoolDTO dto) {
        try {
            CompanyTalentPoolEntity entity = new CompanyTalentPoolEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = companyTalentPoolMapper.update(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(CompanyTalentPoolDTO dto) {
        try {
            CompanyTalentPoolEntity entity = new CompanyTalentPoolEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = companyTalentPoolMapper.updateDataStatus(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
