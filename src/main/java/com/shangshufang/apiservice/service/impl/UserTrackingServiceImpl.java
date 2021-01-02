package com.shangshufang.apiservice.service.impl;

import com.shangshufang.apiservice.common.ObjectConvertUtils;
import com.shangshufang.apiservice.dto.UserTrackingDTO;
import com.shangshufang.apiservice.entity.UserTrackingEntity;
import com.shangshufang.apiservice.manager.UnifiedResponseManager;
import com.shangshufang.apiservice.mapper.UserTrackingMapper;
import com.shangshufang.apiservice.service.UserTrackingService;
import com.shangshufang.apiservice.vo.UnifiedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTrackingServiceImpl implements UserTrackingService {
    @Autowired
    private UserTrackingMapper myMapper;
    private final Logger logger = LogManager.getLogger(UserTrackingServiceImpl.class);

    @Override
    public UnifiedResponse add(UserTrackingDTO dto) {
        try {
            UserTrackingEntity entity = new UserTrackingEntity();
            ObjectConvertUtils.toBean(dto, entity);
            int affectRow = myMapper.insert(entity);
            return UnifiedResponseManager.buildSubmitSuccessResponse(affectRow);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return UnifiedResponseManager.buildExceptionResponse();
        }
    }
}
