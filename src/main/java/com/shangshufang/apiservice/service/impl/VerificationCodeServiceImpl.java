package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.constant.ResponseDataConstant;
import com.shangshufang.apiservice.dto.VerificationCodeDTO;
import com.shangshufang.apiservice.entity.VerificationCodeEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.VerificationCodeMapper;
import com.shangshufang.apiservice.service.VerificationCodeService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import com.shangshufang.apiservice.vo.UniversityVO;
import com.shangshufang.apiservice.vo.VerificationCodeVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private VerificationCodeMapper myMapper;
    private Logger logger = LogManager.getLogger(VerificationCodeServiceImpl.class);

    @Override
    public UnifiedResponse find(String cellphone, String code) {
        try {
            VerificationCodeEntity entity =  myMapper.search(cellphone, code);
            if(entity == null){
                return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.NO_SEARCH_COUNT, ResponseDataConstant.NO_DATA);
            }
            VerificationCodeVO model = new VerificationCodeVO();
            ObjectConvertUtils.toBean(entity, model);
            return UnifiedResponseManager.buildSearchSuccessResponse(ResponseDataConstant.ONE_SEARCH_COUNT, model);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse add(VerificationCodeDTO dto) {
        try {
            VerificationCodeEntity entity = new VerificationCodeEntity();
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
    public UnifiedResponse change(VerificationCodeDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(VerificationCodeDTO dto) {
        return null;
    }
}
