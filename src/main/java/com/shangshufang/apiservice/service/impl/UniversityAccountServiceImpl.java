package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.CustomerRoleConstant;
import com.shangshufang.apiservice.constant.ParameterConstant;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.constant.UniversityAccountConstant;
import com.shangshufang.apiservice.dto.UniversityAccountDTO;
import com.shangshufang.apiservice.entity.UniversityAccountEntity;
import com.shangshufang.apiservice.entity.UniversityCustomerEntity;
import com.shangshufang.apiservice.entity.UniversityStudentEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UniversityAccountMapper;
import com.shangshufang.apiservice.mapper.UniversityCustomerMapper;
import com.shangshufang.apiservice.mapper.UniversityStudentMapper;
import com.shangshufang.apiservice.service.UniversityAccountService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityAccountVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityAccountServiceImpl implements UniversityAccountService {
    @Autowired
    private UniversityAccountMapper myMapper;
    @Autowired
    private UniversityCustomerMapper customerMapper;
    @Autowired
    private UniversityStudentMapper studentMapper;

    private Logger logger = LogManager.getLogger(UniversityAccountServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int universityCode, int schoolID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityAccountVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(universityCode, schoolID);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityAccountEntity> entityList = myMapper.searchList(startIndex, pageSize, universityCode, schoolID);
            for (UniversityAccountEntity entity : entityList) {
                UniversityAccountVO model = new UniversityAccountVO();
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
    public UnifiedResponse findWaitApproveTotalCount4Client(int universityCode, int schoolID, int teacherID) {
        try {
            int totalCount = myMapper.searchWaitApproveTotalCount4Client(universityCode, schoolID, teacherID);
            return UnifiedResponseManager.buildSearchSuccessResponse(totalCount, totalCount);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse findList4Client(int pageNumber, int pageSize, int universityCode, int schoolID, int accountID, String dataStatus) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<UniversityAccountVO> modelList = new ArrayList<>();
            dataStatus = dataStatus.equals(ParameterConstant.NO_PARAMETER) ? null : dataStatus;
            int totalCount = myMapper.searchTotalCount4Client(universityCode, schoolID, accountID, dataStatus);
            if (totalCount == 0) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<UniversityAccountEntity> entityList = myMapper.searchList4Client(startIndex, pageSize, universityCode, schoolID, accountID, dataStatus);
            for (UniversityAccountEntity entity : entityList) {
                UniversityAccountVO model = new UniversityAccountVO();
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
    public UnifiedResponse login(String cellphone, String password, String accountRole) {
        try {
            UniversityAccountEntity entity = null;
            switch (accountRole) {
                case UniversityAccountConstant.ADMIN:
                case UniversityAccountConstant.TEACHER:
                    entity = myMapper.teacherLogin(cellphone, password);
                    break;
                case UniversityAccountConstant.STUDENT:
                    entity = myMapper.studentLogin(cellphone, password);
                    break;
            }
            if (entity == null) {
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            UniversityAccountVO model = new UniversityAccountVO();
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
            int count = myMapper.checkCellphoneExist(cellphone);
            Boolean exist = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkCellphone4ChangePassword(String cellphone) {
        try {
            int count = myMapper.checkCellphone4ChangePassword(cellphone);
            Boolean exist = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse checkEmailExist(String email) {
        try {
            int count = myMapper.checkEmailExist(email);
            Boolean exist = count > 0;
            return UnifiedResponseManager.buildSearchSuccessResponse(count, exist);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changePassword(UniversityAccountDTO dto) {
        try {
            UniversityAccountEntity accountEntity = new UniversityAccountEntity();
            ObjectConvertUtils.toBean(dto, accountEntity);
            accountEntity.setUpdateUser(dto.getLoginUser());
            int affectRow = myMapper.updatePassword(accountEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse delete(int universityCode, int schoolID, int accountID, int customerID) {
        try {
            int affectRow = myMapper.delete(universityCode, schoolID, accountID);
            affectRow += customerMapper.delete(universityCode, schoolID, customerID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(UniversityAccountDTO dto) {
        try {
            UniversityAccountEntity accountEntity = new UniversityAccountEntity();
            UniversityCustomerEntity customerEntity = new UniversityCustomerEntity();

            ObjectConvertUtils.toBean(dto, accountEntity);
            ObjectConvertUtils.toBean(dto, customerEntity);
            accountEntity.setCreateUser(dto.getLoginUser());
            accountEntity.setUpdateUser(dto.getLoginUser());
            customerEntity.setCreateUser(dto.getLoginUser());
            customerEntity.setUpdateUser(dto.getLoginUser());
            customerEntity.setSex("");
            customerEntity.setBirth("");
            customerEntity.setEmail("");
            customerEntity.setPhoto("");
            customerEntity.setCustomerRole(dto.getAccountRole());

            int affectRow = 0;
            affectRow += customerMapper.deleteByCellphone(customerEntity.getCellphone());
            affectRow += myMapper.deleteByCellphone(accountEntity.getCellphone());
            affectRow += myMapper.insert(accountEntity);
            affectRow += customerMapper.insert(customerEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(UniversityAccountDTO dto) {
        try {
            UniversityAccountEntity accountEntity = new UniversityAccountEntity();
            UniversityCustomerEntity customerEntity = new UniversityCustomerEntity();

            ObjectConvertUtils.toBean(dto, accountEntity);
            ObjectConvertUtils.toBean(dto, customerEntity);

            accountEntity.setUpdateUser(dto.getLoginUser());
            customerEntity.setUpdateUser(dto.getLoginUser());

            int affectRow = myMapper.update(accountEntity);
            affectRow += customerMapper.updateCellphone(customerEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse changeDataStatus(UniversityAccountDTO dto) {
        try {
            UniversityAccountEntity accountEntity = new UniversityAccountEntity();
            UniversityCustomerEntity customerEntity = new UniversityCustomerEntity();
            UniversityStudentEntity studentEntity = new UniversityStudentEntity();
            int affectRow = 0;
            switch (dto.getAccountRole()) {
                case CustomerRoleConstant.UNIVERSITY_SCHOOL_ADMIN :
                case CustomerRoleConstant.UNIVERSITY_SCHOOL_TEACHER:
                    ObjectConvertUtils.toBean(dto, accountEntity);
                    ObjectConvertUtils.toBean(dto, customerEntity);
                    accountEntity.setUpdateUser(dto.getLoginUser());
                    customerEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += myMapper.updateDataStatus(accountEntity);
                    affectRow += customerMapper.updateDataStatus(customerEntity);
                    break;
                case CustomerRoleConstant.UNIVERSITY_SCHOOL_STUDENT:
                    ObjectConvertUtils.toBean(dto, studentEntity);
                    studentEntity.setUpdateUser(dto.getLoginUser());
                    affectRow += studentMapper.updateDataStatus(studentEntity);
                    break;
            }

            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
