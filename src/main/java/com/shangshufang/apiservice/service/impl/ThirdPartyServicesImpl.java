package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.dto.ThirdPartyServicesDTO;
import com.shangshufang.apiservice.entity.ThirdPartyServicesEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.ThirdPartyServicesMapper;
import com.shangshufang.apiservice.service.BaseService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyServicesImpl implements BaseService<ThirdPartyServicesDTO> {
    @Autowired
    private ThirdPartyServicesMapper myMapper;
    private final Logger logger = LogManager.getLogger(ThirdPartyServicesImpl.class);

    @Override
    public UnifiedResponse add(ThirdPartyServicesDTO dto) {
        try {
            ThirdPartyServicesEntity entity = new ThirdPartyServicesEntity();
            ObjectConvertUtils.toBean(dto, entity);
            String SYS_ADMIN_ID = "0";
            entity.setCreateUser(SYS_ADMIN_ID);
            entity.setUpdateUser(SYS_ADMIN_ID);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }

    @Override
    public UnifiedResponse change(ThirdPartyServicesDTO dto) {
        return null;
    }

    @Override
    public UnifiedResponse changeDataStatus(ThirdPartyServicesDTO dto) {
        return null;
    }
}
