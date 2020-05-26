package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.DataStatusConstant;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.UniversityStudentDTO;
import com.shangshufang.apiservice.entity.UniversityStudentEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityStudentMapper;
import com.shangshufang.apiservice.service.UniversityStudentService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityStudentVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityStudentServiceImpl implements UniversityStudentService {
    @Autowired
    private UniversityStudentMapper myMapper;
    private Logger logger = LogManager.getLogger(UniversityStudentServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int universityCode, int schoolID, String fullName) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityStudentVO> modelList = new ArrayList<>();
            fullName = fullName.equals(ParameterConstant.NO_PARAMETER) ? null : "%" + fullName + "%";
            int totalCount = myMapper.searchTotalCount(universityCode, schoolID, fullName);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityStudentEntity> entityList =  myMapper.searchList(startIndex, pageSize, universityCode, schoolID, fullName);
            for (UniversityStudentEntity entity : entityList) {
                UniversityStudentVO model = new UniversityStudentVO();
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
    public UnifiedResponse find(int universityCode, int schoolID, int studentID) {
        try {
            UniversityStudentEntity entity =  myMapper.search(universityCode, schoolID, studentID);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            UniversityStudentVO model = new UniversityStudentVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse login(String cellphone, String password) {
        try {
            UniversityStudentEntity entity =  myMapper.login(cellphone, password);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            UniversityStudentVO model = new UniversityStudentVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkCellphoneExist(String cellphone) {
        try {
            int count =  myMapper.checkCellphoneExist(cellphone);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkEmailExist(String email) {
        try {
            email = email.replace("%2E", ".");
            int count =  myMapper.checkEmailExist(email);
            Boolean exist =  count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changePassword(UniversityStudentDTO dto) {
        try {
            UniversityStudentEntity entity = new UniversityStudentEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updatePassword(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse resetPassword(UniversityStudentDTO dto) {
        try {
            UniversityStudentEntity entity = new UniversityStudentEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.resetPassword(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(UniversityStudentDTO dto) {
        try {
            UniversityStudentEntity entity = new UniversityStudentEntity();
            ObjectConvertUtils.toBean(dto, entity);
            entity.setDataStatus(DataStatusConstant.ACTIVE);
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
    public UnifiedResponse change(UniversityStudentDTO dto) {
        try {
            UniversityStudentEntity entity = new UniversityStudentEntity();
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
    public UnifiedResponse changeDataStatus(UniversityStudentDTO dto) {
        try {
            UniversityStudentEntity entity = new UniversityStudentEntity();
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
