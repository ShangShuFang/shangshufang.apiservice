package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.CompanyAccountDTO;
import com.shangshufang.apiservice.entity.CompanyAccountEntity;
import com.shangshufang.apiservice.entity.CompanyCustomerEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.CompanyAccountMapper;
import com.shangshufang.apiservice.mapper.CompanyCustomerMapper;
import com.shangshufang.apiservice.service.CompanyAccountService;
import com.shangshufang.apiservice.vo.CompanyAccountVO;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyAccountServiceImpl implements CompanyAccountService {
    @Autowired
    private CompanyAccountMapper myMapper;
    @Autowired
    private CompanyCustomerMapper customerMapper;

    private Logger logger = LogManager.getLogger(CompanyAccountServiceImpl.class);

    @Override
    public UnifiedResponse findList(int pageNumber, int pageSize, int companyID) {
        try {
            int startIndex = (pageNumber - 1) * pageSize;
            List<CompanyAccountVO> modelList = new ArrayList<>();
            int totalCount = myMapper.searchTotalCount(companyID);
            if(totalCount == 0){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            List<CompanyAccountEntity> entityList =  myMapper.searchList(startIndex, pageSize, companyID);
            for (CompanyAccountEntity entity : entityList) {
                CompanyAccountVO model = new CompanyAccountVO();
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
    public UnifiedResponse login(String cellphone, String password) {
        try {
            CompanyAccountEntity entity =  myMapper.login(cellphone, password);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            CompanyAccountVO model = new CompanyAccountVO();
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
    public UnifiedResponse delete(int companyID, int customerID, int accountID) {
        try {
            int affectRow = myMapper.delete(companyID, accountID);
            affectRow += customerMapper.delete(companyID, customerID);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(CompanyAccountDTO dto) {
        try {
            CompanyAccountEntity accountEntity = new CompanyAccountEntity();
            CompanyCustomerEntity customerEntity = new CompanyCustomerEntity();

            ObjectConvertUtils.toBean(dto, accountEntity);
            accountEntity.setCreateUser(dto.getLoginUser());
            accountEntity.setUpdateUser(dto.getLoginUser());

            ObjectConvertUtils.toBean(dto, customerEntity);
            customerEntity.setCreateUser(dto.getLoginUser());
            customerEntity.setUpdateUser(dto.getLoginUser());
            customerEntity.setSex("");
            customerEntity.setBirth("");
            customerEntity.setEmail("");
            customerEntity.setPhoto("");

            int affectRow = myMapper.insert(accountEntity);
            affectRow += customerMapper.insert(customerEntity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(CompanyAccountDTO dto) {
        try {
            CompanyAccountEntity accountEntity = new CompanyAccountEntity();
            CompanyCustomerEntity customerEntity = new CompanyCustomerEntity();

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
    public UnifiedResponse changeDataStatus(CompanyAccountDTO dto) {
        try {
            CompanyAccountEntity entity = new CompanyAccountEntity();
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
